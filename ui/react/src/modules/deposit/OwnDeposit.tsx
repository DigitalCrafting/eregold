import {AccountModel} from "../../models/account.models";

export interface OwnDepositProps {
    accountNumber: string
}

export function OwnDeposit({accountNumber}: OwnDepositProps) {

    let dstAccountList: Array<AccountModel> = [];

    const onCancelClicked = () => {

    }
    const onDepositClicked = () => {

    }

    return (
        <form>
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
                                            className="form-select"
                                            aria-label="To account"
                                    >
                                        {dstAccountList.map(account =>
                                            <option
                                                selected={accountNumber === account.accountNumber}
                                                value={account.accountNumber}>
                                                {account.accountName}
                                                {account.accountNumber}
                                                {account.currentBalance}G
                                            </option>)}
                                    </select>
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
                                            type="number"
                                            step='0.01'
                                            placeholder='0.00'
                                            className="form-control"
                                            id="amountId"/>
                                    </div>

                                </div>
                                <div className="col-4">
                                    <div className="form-group">
                                        <label htmlFor="currencyId">Currency</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="currencyId"/>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                    <div className="form-group">
                                        <label htmlFor="descriptionId">Comment</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="descriptionId"/>
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