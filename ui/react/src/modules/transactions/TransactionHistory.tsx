import {TransactionModel} from "../../models/transaction.models";
import styles from "./TransactionHistory.module.css";
import dayjs from "dayjs";

export interface TransactionHistoryProps {
    transactionHistory: Array<TransactionModel>
}

export function TransactionHistory({transactionHistory}: TransactionHistoryProps) {
    if (!transactionHistory || transactionHistory.length === 0) {
        return (<></>);
    }

    const tableRows = transactionHistory.map(transaction => {
        return (<tr key={transaction?.id}>
            <td>{transaction?.id}</td>
            <td>{transaction?.foreignAccountNumber}</td>
            <td className={transaction?.amount < 0 ? styles.outgoingTransaction : styles.incomingTransaction}>
                {transaction?.amount}G
            </td>
            <td>{dayjs(transaction?.date).format('YYYY-MM-DD')}</td>
            <td>{transaction?.description}</td>
        </tr>);
    })

    return (
        <table className="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">To/from account</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Comment</th>
            </tr>
            </thead>
            <tbody>
            {tableRows}
            </tbody>
        </table>
    );
}