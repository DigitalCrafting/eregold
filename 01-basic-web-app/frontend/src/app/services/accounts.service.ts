import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AccountsService {
    private baseUrl = "http://localhost:8080/v1/accounts";
    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        }),
        withCredentials: true
    };

    constructor(private _httpClient: HttpClient) {
    }

    // TODO change observable to Promise OR introduce dispatcher service that handles errors
    public getAccounts(): Observable<any> {
        return this._httpClient.get(this.baseUrl, this.httpOptions).pipe();
    }

    public createAccount(request: CreateAccountRequest) {
        return this._httpClient.post(this.baseUrl, request, this.httpOptions).pipe();
    }

}

export interface AccountModel {
    accountNumber: string;
    accountName: string;
    currentBalance: number;
    currency: CurrencyEnum;
    type: AccountTypeEnum;
}

export type AccountTypeEnum = 'DEBIT' | 'SAVING';
export const AccountTypeEnum = {
    DEBIT: 'DEBIT' as AccountTypeEnum,
    SAVING: 'SAVING' as AccountTypeEnum
};

export type CurrencyEnum = 'GLD';
export const CurrencyEnum = {
    GLD: 'GLD' as CurrencyEnum
};

export interface CreateAccountRequest {
    accountType: AccountTypeEnum;
    currency: CurrencyEnum;
}
