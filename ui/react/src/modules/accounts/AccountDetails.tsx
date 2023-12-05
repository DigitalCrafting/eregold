import {AccountDetailsModel} from "../../models/account.models";
import {TransactionHistory} from "../transactions/TransactionHistory";

export function AccountDetails() {

    const accountDetails: AccountDetailsModel = {} as AccountDetailsModel;

    const onGoBackClicked = () => {

    }
    const onMakeOwnDepositClicked = () => {

    }
    const onMakeOwnTransferClicked = () => {

    }

    return (
        <>
            <div className="container overflow-hidden">
                <div className="row gy-2">
                    <div className="col-6">
                        <div className="form-group">
                            <label htmlFor="accountNameId" className="fw-bold">Account name</label>
                            <p id="accountNameId">
                                {accountDetails?.accountName}
                            </p>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="form-group">
                            <label htmlFor="accountNumberId" className="fw-bold">Account number</label>
                            <p id="accountNumberId">
                                {accountDetails?.accountNumber}
                            </p>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="form-group">
                            <label htmlFor="accountTypeId" className="fw-bold">Account type</label>
                            <p id="accountTypeId">
                                {accountDetails?.type}
                            </p>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="form-group">
                            <label htmlFor="balanceId" className="fw-bold">Balance</label>
                            <p id="balanceId">{accountDetails?.currentBalance}G</p>
                        </div>
                    </div>
                </div>
                <div className="row gy-4 margin-bottom-2em">
                    <div className="col-3">
                        <button id="backButton"
                                type="button"
                                className="btn btn-secondary"
                                onClick={onGoBackClicked}
                        >Back to list
                        </button>
                    </div>
                    <div className="col-3"></div>
                    <div className="col-3">
                        <button id="ownDepositButton"
                                type="button"
                                className="btn btn-primary float-right"
                                onClick={onMakeOwnDepositClicked}
                        >Own deposit
                        </button>
                    </div>
                    <div className="col-3">
                        <button id="ownTransferButton"
                                type="button"
                                className="btn btn-primary float-right"
                                onClick={onMakeOwnTransferClicked}
                        >Own transfer
                        </button>
                    </div>
                </div>
                <div className="row gy-4">
                    <div className="col-12">
                        <TransactionHistory/>
                    </div>
                </div>
            </div>
        </>
    );
}