import {Component, EventEmitter} from '@angular/core';
import {AccountModel, AccountsService} from "../../../../services/accounts.service";

@Component({
    selector: 'accounts-list',
    templateUrl: './accounts-list.component.html',
    styleUrls: ['./accounts-list.component.scss']
})
export class AccountsListComponent {

    accountsListEventEmitter: EventEmitter<AccountListAction> = new EventEmitter<AccountListAction>();
    accountsList: Array<AccountModel>;

    constructor(private _accountsService: AccountsService) {
        this._accountsService.getAccounts()
            .subscribe((list: Array<AccountModel>) => {
                    this.accountsList = list;
                }
            )
    }

    onAddAccountClicked() {
        this.accountsListEventEmitter.emit(AccountListAction.ADD_ACCOUNT);
    }
}

export type AccountListAction = 'ADD_ACCOUNT';
export const AccountListAction = {
    ADD_ACCOUNT: 'ADD_ACCOUNT' as AccountListAction
};
