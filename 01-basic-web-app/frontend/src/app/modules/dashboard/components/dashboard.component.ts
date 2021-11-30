import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {UserContext} from "../../common/user.context";
import {Router} from "@angular/router";
import {DynamicComponentManager} from "../../../core/dynamic-component-manager/dynamic-component-manager";
import {AccountsListComponent} from "../../accounts/accounts-list/components/accounts-list.component";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit {

    @ViewChild('componentManager')
    componentManager: DynamicComponentManager;

    constructor(private _userContext: UserContext,
                private _router: Router) {
    }

    ngOnInit(): void {
        if (!this._userContext.isLoggedIn) {
            this._router.navigate(['login']);
        }
    }

    ngAfterViewInit() {
        this.componentManager.show(AccountsListComponent);
    }
}
