import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {UserContext} from "../../common/user.context";
import {Router} from "@angular/router";
import {DynamicComponentManager} from "../../../core/dynamic-component-manager/dynamic-component-manager";
import {
    AccountListAction,
    AccountsListComponent
} from "../../accounts/accounts-list/components/accounts-list.component";
import {Subscription} from "rxjs";
import {
    AccountCreateAction,
    AccountCreateComponent
} from "../../accounts/account-create/components/account-create.component";
import {EregoldRoutes} from "../../../utils/routes.enum";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit, OnDestroy {

    @ViewChild('componentManager')
    componentManager: DynamicComponentManager;

    accountsListComponent: AccountsListComponent;
    accountsListEventSubscription: Subscription;

    accountCreateComponent: AccountCreateComponent;
    accountCreateEventSubscription: Subscription;

    constructor(private _userContext: UserContext,
                private _router: Router) {
    }

    ngOnInit(): void {
        if (!this._userContext.isLoggedIn) {
            this._router.navigate([EregoldRoutes.LOGIN]);
        }
    }

    ngAfterViewInit() {
        this.showAccountsList();
    }

    ngOnDestroy() {
        this.cleanUp();
    }

    private showAccountsList() {
        this.cleanUp();
        this.accountsListComponent = this.componentManager.show(AccountsListComponent);
        this.accountsListEventSubscription = this.accountsListComponent.accountsListEventEmitter.subscribe((action: AccountListAction) => {
            if (action === AccountListAction.ADD_ACCOUNT) {
                this.showAccountCreate();
            }
        });
    }

    private showAccountCreate() {
        this.cleanUp();
        this.accountCreateComponent = this.componentManager.show(AccountCreateComponent);
        this.accountCreateEventSubscription = this.accountCreateComponent.accountCreateEventEmitter.subscribe((action: AccountCreateAction) => {
            if (action === AccountCreateAction.SHOW_LIST) {
                this.showAccountsList();
            }
        });
    }

    private cleanUp() {
        if (this.accountsListEventSubscription) {
            this.accountsListEventSubscription.unsubscribe();
        }
        if (this.accountCreateEventSubscription) {
            this.accountCreateEventSubscription.unsubscribe();
        }

        delete this.accountsListEventSubscription;
        delete this.accountsListComponent;

        delete this.accountCreateEventSubscription;
        delete this.accountCreateComponent;
    }
}
