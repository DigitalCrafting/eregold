import {Component, OnInit} from '@angular/core';
import {UserContext} from "../../common/user.context";
import {Router} from "@angular/router";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    constructor(private _userContext: UserContext,
                private _router: Router) {
    }

    ngOnInit(): void {
        if (!this._userContext.isLoggedIn) {
            this._router.navigate(['login']);
        }
    }

}
