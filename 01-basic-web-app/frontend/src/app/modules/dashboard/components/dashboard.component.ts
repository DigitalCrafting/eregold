import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {UserContext} from "../../common/user.context";
import {Router} from "@angular/router";
import {DynamicComponentManager} from "../../../core/dynamic-component-manager/dynamic-component-manager";
import {AccountsListComponent} from "../../accounts/accounts-list/components/accounts-list.component";
import {Subscription} from "rxjs";
import {
    AccountCreateAction,
    AccountCreateComponent
} from "../../accounts/account-create/components/account-create.component";
import {EregoldRoutes} from "../../../utils/routes.enum";
import {AccountDetailsComponent} from "../../accounts/account-details/components/account-details.component";
import {OwnTransferComponent} from "../../transfer/own-transfer/components/own-transfer.component";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit, OnDestroy {

    @ViewChild('componentManager')
    componentManager: DynamicComponentManager;

    accountsListComponent: AccountsListComponent;
    createAccountSubscription: Subscription;
    showDetailsSubscription: Subscription;

    accountCreateComponent: AccountCreateComponent;
    accountCreateEventSubscription: Subscription;

    accountDetailsComponent: AccountDetailsComponent;
    backToListSubscription: Subscription;
    makeOwnTransferSubscription: Subscription;

    ownTransferComponent: OwnTransferComponent;
    ownTransferBackSubscription: Subscription;

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
        this.createAccountSubscription = this.accountsListComponent.createAccountEventEmitter.subscribe(() => {
            this.showAccountCreate();
        });
        this.showDetailsSubscription = this.accountsListComponent.showDetailsEventEmitter.subscribe(accountNumber => {
            this.showAccountDetails(accountNumber);
        })
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

    private showAccountDetails(accountNumber: string) {
        this.cleanUp();
        this.accountDetailsComponent = this.componentManager.show(AccountDetailsComponent, accountNumber);
        this.backToListSubscription = this.accountDetailsComponent.backToDetailsEventEmitter.subscribe(() => {
            this.showAccountsList();
        })
        this.makeOwnTransferSubscription = this.accountDetailsComponent.makeOwnTransferEventEmitter.subscribe(() => {
            this.showOwnTransfer(accountNumber);
        });
    }

    private showOwnTransfer(accountNumber: string) {
        this.cleanUp();
        this.ownTransferComponent = this.componentManager.show(OwnTransferComponent, accountNumber);
        this.ownTransferBackSubscription = this.ownTransferComponent.backEventEmitter.subscribe(() => {
            this.showAccountDetails(accountNumber);
        })
    }

    private cleanUp() {
        this.showDetailsSubscription?.unsubscribe();
        delete this.showDetailsSubscription;
        this.createAccountSubscription?.unsubscribe();
        delete this.createAccountSubscription;
        this.accountCreateEventSubscription?.unsubscribe();
        delete this.accountCreateEventSubscription;
        this.backToListSubscription?.unsubscribe();
        delete this.backToListSubscription;
        this.makeOwnTransferSubscription?.unsubscribe();
        delete this.makeOwnTransferSubscription;
        this.ownTransferBackSubscription?.unsubscribe();
        delete this.ownTransferBackSubscription;

        delete this.accountCreateComponent;
        delete this.accountsListComponent;
        delete this.accountDetailsComponent;
        delete this.ownTransferComponent;
    }
}
