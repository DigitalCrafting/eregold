import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class UserContext {
    isLoggedIn: boolean;
}