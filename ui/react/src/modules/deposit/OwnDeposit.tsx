import {AccountModel} from "../../models/account.models";
import {useNavigate, useParams} from "react-router-dom";
import {z} from "zod";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {useContext} from "react";
import EregoldUserContext from "../../context/eregold-user-context";
import {CurrencyEnum} from "../../models/enums";

const ownDepositSchema = z.object({
    dstAccount: z.string().length(18),
    description: z.string().min(3),
    amount: z.number().min(0.01),
    currency: z.string().min(1),
});

type DepositFormData = z.infer<typeof ownDepositSchema>;

export function OwnDeposit() {
    const navigate = useNavigate();
    const {accountNumber} = useParams();
    const { accounts } = useContext(EregoldUserContext);
    const currencyOption = CurrencyEnum.GLD; // Only single option right now

    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm<DepositFormData>({resolver: zodResolver(ownDepositSchema)});

    const onCancelClicked = () => {
        navigate(-1);
    }

    const onDepositClicked = () => {
        /* TODO deposit */
    }

    return (
        <form onSubmit={handleSubmit(onDepositClicked)}>
            <div className="container overflow-hidden">
                <div className="row gy-2">
                    <h5 className="card-title">Own deposit</h5>
                </div>
                <div className="row gy-2">
                    <div className="col-7">
                        <div className="container">
                            <div className="row">
                                <div className="form-group">
                                    <label htmlFor="dstAccountId">To account:</label>
                                    <select id="dstAccountId"
                                            {...register('dstAccount')}
                                            className="form-select"
                                            aria-label="To account"
                                            value={accountNumber}
                                    >
                                        {accounts.map(account =>
                                            <option
                                                value={account.accountNumber}>
                                                {account.accountName}
                                                {account.accountNumber}
                                                {account.currentBalance}G
                                            </option>)}
                                    </select>
                                    {errors.dstAccount && <p className="alert alert-danger">{errors.dstAccount.message}</p>}
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
                                        {errors.amount && <p className="alert alert-danger">{errors.amount.message}</p>}
                                    </div>

                                </div>
                                <div className="col-4">
                                    <div className="form-group">
                                        <label htmlFor="currencyId">Currency</label>
                                        <input
                                            {...register('currency')}
                                            disabled={true}
                                            value={currencyOption}
                                            type="text"
                                            className="form-control"
                                            id="currencyId"/>
                                        {errors.currency && <p className="alert alert-danger">{errors.currency.message}</p>}
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
                                        {errors.description && <p className="alert alert-danger">{errors.description.message}</p>}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-3">
                        <button id="depositCancelButton"
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
                                id="depositButton"
                                onClick={onDepositClicked}
                        >Deposit
                        </button>
                    </div>
                </div>
            </div>
        </form>
    );
}