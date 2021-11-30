import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class UserContext {
    private _isLoggedIn: boolean;

    set isLoggedIn(value: boolean) {
        this._isLoggedIn = value;
    }

    get isLoggedIn() {
        return this._isLoggedIn || (localStorage.getItem('token') != null && localStorage.getItem('token') !== undefined);
    }
}