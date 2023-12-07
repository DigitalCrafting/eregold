import {AccountModel} from "../../models/account.models";
import {useNavigate} from "react-router-dom";
import EregoldUserContext from "../../context/eregold-user-context";
import {useContext} from "react";
import useAccounts from "../../hooks/useAccounts";

export function AccountsList() {
    const navigate = useNavigate();

    const { accounts } = useContext(EregoldUserContext);

    const onDetailsClicked = (accountNumber: string) => {
        navigate('/ui/account/' + accountNumber);
    }

    const onMakeOwnTransferClicked = () => {
        navigate('/ui/transfer')
    }

    const onMakeOwnDepositClicked = () => {
        navigate('/ui/deposit');
    }

    const onAddAccountClicked = () => {
        navigate('/ui/account/new')
    }

    const accountsList = accounts.map((account: AccountModel) =>
        <tr key={account.accountNumber}>
            <td>{account?.accountName}</td>
            <td>{account?.accountNumber}</td>
            <td>{account?.currentBalance}G</td>
            <td>{account?.type}</td>
            <td>
                <button type="button" className="btn btn-outline-secondary"
                        onClick={() => onDetailsClicked(account?.accountNumber)}>
                    <i className="bi bi-box-arrow-right"></i>
                </button>
            </td>
        </tr>
    )

    return (
        <>
            <table className="table">
                <thead>
                <tr>
                    <th scope="col">Account name</th>
                    <th scope="col">Account number</th>
                    <th scope="col">Current balance</th>
                    <th scope="col">Type</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                {accountsList}
                </tbody>
            </table>

            <button type="button"
                    className="btn btn-secondary me-2"
                    onClick={onMakeOwnTransferClicked}
            >Own transfer
            </button>
            <button type="button"
                    className="btn btn-secondary"
                    onClick={onMakeOwnDepositClicked}
            >Own deposit
            </button>
            <button type="button"
                    className="btn btn-primary float-end"
                    id="addAccountButton"
                    onClick={onAddAccountClicked}
            >Add account
            </button>

        </>
    );
}