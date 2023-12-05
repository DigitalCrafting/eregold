import {CreateAccountRequest} from "../models/account.models";
import eregoldApiClient from "./eregold-api.client";

class AccountsService {
    public getAccounts() {
        const controller = new AbortController();
        const request = eregoldApiClient.get(
            "/v1/accounts",
            {
                signal: controller.signal
            }
            );
        return {request, cancel: () => {controller.abort()}};
    }

    public getAccountDetails(accountNumber: string) {
        return eregoldApiClient.get(`/v1/accounts/${accountNumber}`);
    }

    public createAccount(request: CreateAccountRequest) {
        return eregoldApiClient.post("/v1/accounts", request);
    }
}

export default new AccountsService();