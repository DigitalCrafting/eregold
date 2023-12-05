import {z} from "zod";
import {AccountTypeEnum, CurrencyEnum} from "../../models/enums";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";

const createAccountSchema = z.object({
    accountType: z.nativeEnum(AccountTypeEnum),
    currency: z.nativeEnum(CurrencyEnum),
    accountName: z.string().min(1)
});

type CreateAccountFormData = z.infer<typeof createAccountSchema>;

export function AccountCreate() {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm<CreateAccountFormData>({resolver: zodResolver(createAccountSchema)});

    const currencyOptions = [
        {
            value: CurrencyEnum.GLD,
            display: CurrencyEnum.GLD
        }
    ];

    const accountTypeOptions = [
        {
            value: AccountTypeEnum.DEBIT,
            display: 'Debit'
        },
        {
            value: AccountTypeEnum.SAVING,
            display: 'Saving'
        }
    ]

    const onCancelClicked = () => {

    }
    const onAddClicked = () => {

    }

    return (
        <form>
            <div className="container overflow-hidden">
                <div className="row gy-2">
                    <div className="col-4">
                        <div className="form-group">
                            <label htmlFor="accountTypeId">Account type</label>
                            <select id="accountTypeId"
                                    {...register('accountType')}
                                    className="form-select"
                                    aria-label="Account type"
                            >
                                {accountTypeOptions.map(option => <option
                                    value={option.value}>{option.display}</option>)
                                }
                                {errors.accountType && <p className="alert alert-danger">{errors.accountType.message}</p>}
                            </select>
                        </div>
                    </div>
                    <div className="col-4">
                        <div className="form-group">
                            <label htmlFor="currencyId">Currency</label>
                            <select
                                {...register('currency')}
                                id="currencyId" className="form-select" aria-label="Currency">
                                { currencyOptions.map(option => <option
                                    value={option.value}>{option.display}</option>)
                                }
                            </select>
                            {errors.currency && <p className="alert alert-danger">{errors.currency.message}</p>}
                        </div>
                    </div>
                    <div className="col-4">
                        <div className="form-group">
                            <label htmlFor="accountNameId">Account name</label>
                            <input
                                {...register('accountName')}
                                type="text"
                                className="form-control"
                                id="accountNameId"/>
                        </div>
                        {errors.accountName && <p className="alert alert-danger">{errors.accountName.message}</p>}
                    </div>
                    <div className="col-3">
                        <button type="button"
                                className="btn btn-secondary"
                                onClick={onCancelClicked}
                        >Cancel
                        </button>
                    </div>
                    <div className="col-6"></div>
                    <div className="col-3">
                        <button type="submit"
                                className="btn btn-primary float-right"
                                id="addAccountButton"
                                onClick={onAddClicked}
                        >Add
                        </button>
                    </div>
                </div>
            </div>
        </form>
    )
        ;
}