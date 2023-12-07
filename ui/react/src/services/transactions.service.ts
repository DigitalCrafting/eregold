import eregoldApiClient from "./eregold-api.client";
import {MakeTransactionModel} from "../models/transaction.models";

class TransactionsService {
    public transfer(request: MakeTransactionModel) {
        return eregoldApiClient.post('/v1/transactions/transfer', request);
    }

    public deposit(request: MakeTransactionModel) {
        return eregoldApiClient.post('/v1/transactions/deposit', request);
    }
}

export default new TransactionsService();