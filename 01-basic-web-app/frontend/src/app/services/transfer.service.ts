import {Injectable} from '@angular/core';
import {CurrencyEnum} from "../models/enums";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class TransferService {
    private baseUrl = "http://localhost:8080/v1/transfers";
    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        }),
        withCredentials: true
    };

    constructor(private _httpClient: HttpClient) {
    }

    public transfer(request: TransferRequest) {
        return this._httpClient.post(this.baseUrl, request, this.httpOptions).pipe();
    }
}

export interface TransferRequest {
    srcAccount?: string;
    dstAccount?: string;
    description?: string;
    amount?: number;
    currency?: CurrencyEnum;
}