import {useNavigate, useParams} from "react-router-dom";
import {z} from "zod";
import {FieldValues, useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import EregoldUserContext from "../../context/eregold-user-context";
import {ChangeEvent, useContext, useState} from "react";
import {AccountModel} from "../../models/account.models";
import {CurrencyEnum} from "../../models/enums";
import TransactionsService from "../../services/transactions.service";

const ownTransferSchema = z.object({
    srcAccount: z.string().length(18),
    dstAccount: z.string().length(18),
    description: z.string().min(3),
    amount: z.string().refine((val) => !Number.isNaN(parseInt(val, 10)), {
        message: "Expected number, received a string"
    }),
    currency: z.string().min(1),
})

type TransferFormData = z.infer<typeof ownTransferSchema>;

interface OwnTransferFormConfig {
    srcAccountList: Array<AccountModel>;
    dstAccountList: Array<AccountModel>;
    initialSrcAccount: string;
    initialDstAccount: string;
}

export function OwnTransfer() {
    const navigate = useNavigate();
    const {accounts, reloadAccounts} = useContext(EregoldUserContext);
    const {accountNumber} = useParams();

    const initialFormConfig: OwnTransferFormConfig = {} as OwnTransferFormConfig;
    initialFormConfig.srcAccountList = [...accounts];

    if (accountNumber) {
        initialFormConfig.initialSrcAccount = accountNumber;
    } else {
        initialFormConfig.initialSrcAccount = accounts[0].accountNumber;
    }

    initialFormConfig.dstAccountList = accounts.filter(acc => acc.accountNumber !== initialFormConfig.initialSrcAccount);
    initialFormConfig.initialDstAccount = initialFormConfig.dstAccountList.length > 0 ? initialFormConfig.dstAccountList[0].accountNumber : "";

    const [formConfig, setFormConfig] = useState<OwnTransferFormConfig>(initialFormConfig);
    const currencyOption = CurrencyEnum.GLD; // Only single option right now

    /* TODO fix this validation */
    ownTransferSchema.refine((data) => {
            if (formConfig) {
                const selectedAccount = formConfig.srcAccountList.find(acc => acc.accountNumber === data.srcAccount);
                if (selectedAccount) {
                    return (selectedAccount.currentBalance - Number(data.amount)) >= 0;
                }
            }
            return true;
        },
        {
            message: "Amount exceeds available balance",
            path: ["amount"],
        });

    const {
        register,
        handleSubmit,
        control,
        setValue,
        formState: {errors}
    } = useForm<TransferFormData>({resolver: zodResolver(ownTransferSchema)});

    const onCancelClicked = () => {
        navigate(-1);
    };

    const onTransferClicked = (data: FieldValues) => {
        TransactionsService.transfer(data).then(resp => {
            reloadAccounts();
            navigate(-1);
        });
    }

    const onSrcAccountSelected = (event: ChangeEvent<HTMLSelectElement>) => {
        const accountNumber = event.target.value;
        const currentFormConfig = {...formConfig} as OwnTransferFormConfig;
        currentFormConfig.dstAccountList = accountNumber ? accounts.filter(acc => acc.accountNumber !== accountNumber) : [...accounts];
        currentFormConfig.initialSrcAccount = accountNumber;
        if (currentFormConfig.dstAccountList.length === 1) {
            setValue('dstAccount', currentFormConfig.dstAccountList[0].accountNumber);
        }
        setFormConfig(currentFormConfig);
    }

    return (
        <form onSubmit={handleSubmit(onTransferClicked)}>
            <div className="container overflow-hidden">
                <div className="row gy-2">
                    <h5 className="card-title">Own transfer</h5>
                </div>
                <div className="row gy-2">
                    <div className="col-7">
                        <div className="container">
                            <div className="row">
                                <div className="form-group">
                                    <label htmlFor="srcAccountId">From account:</label>
                                    <select id="srcAccountId"
                                            {...register('srcAccount')}
                                            className="form-select"
                                            aria-label="From account"
                                            onChange={onSrcAccountSelected}
                                            value={formConfig?.initialSrcAccount}
                                    >
                                        {
                                            formConfig?.srcAccountList.map(account =>
                                                <option key={account.accountNumber} value={account.accountNumber}>
                                                    {account.accountName + ' '}
                                                    {account.accountNumber + ' '}
                                                    {account.currentBalance}G
                                                </option>
                                            )
                                        }
                                    </select>
                                    {errors.srcAccount &&
                                        <p className="alert alert-danger">{errors.srcAccount.message}</p>}
                                </div>
                            </div>
                            <div className="row">
                                <div className="form-group">
                                    <label htmlFor="dstAccountId">To account:</label>
                                    <select id="dstAccountId"
                                            {...register('dstAccount')}
                                            className="form-select"
                                            aria-label="To account"
                                            value={
                                                formConfig?.initialDstAccount
                                            }
                                    >
                                        {
                                            formConfig?.dstAccountList.map(account =>
                                                <option key={account.accountNumber} value={account.accountNumber}>
                                                    {account.accountName + ' '}
                                                    {account.accountNumber + ' '}
                                                    {account.currentBalance}G
                                                </option>
                                            )
                                        }
                                    </select>
                                    {errors.dstAccount &&
                                        <p className="alert alert-danger">{errors.dstAccount.message}</p>}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-5">
                        <div className="container">
                            <div className="row">
                                <div className="col-8">
                                    <div className="form-group">
                                        <label htmlFor="amountId">Amount</label>
                                        <input
                                            {...register('amount')}
                                            type="number"
                                            step='0.01'
                                            placeholder='0.00'
                                            className="form-control"
                                            id="amountId"/>
                                        {errors.amount &&
                                            <p className="alert alert-danger">{errors.amount.message}</p>}
                                    </div>
                                </div>
                                <div className="col-4">
                                    <div className="form-group">
                                        <label htmlFor="currencyId">Currency</label>
                                        <input
                                            {...register('currency')}
                                            value={currencyOption}
                                            type="text"
                                            className="form-control"
                                            id="currencyId"/>
                                        {errors.currency &&
                                            <p className="alert alert-danger">{errors.currency.message}</p>}
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                    <div className="form-group">
                                        <label htmlFor="descriptionId">Comment</label>
                                        <input
                                            {...register('description')}
                                            type="text"
                                            className="form-control"
                                            id="descriptionId"/>
                                        {errors.description &&
                                            <p className="alert alert-danger">{errors.description.message}</p>}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-3">
                        <button id="transferCancelButton"
                                type="button"
                                className="btn btn-secondary"
                                onClick={onCancelClicked}
                        >Cancel
                        </button>
                    </div>
                    <div className="col-6"></div>
                    <div className="col-3">
                        <button type="submit"
                                className="btn btn-primary float-right"
                                id="transferButton"
                        >Transfer
                        </button>
                    </div>
                </div>
            </div>
        </form>
    );
}