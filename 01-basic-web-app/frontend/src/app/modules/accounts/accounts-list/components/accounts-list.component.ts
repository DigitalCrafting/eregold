import {Component} from '@angular/core';
import {AccountModel, AccountsService} from "../../../../services/accounts.service";

@Component({
    selector: 'accounts-list',
    templateUrl: './accounts-list.component.html',
    styleUrls: ['./accounts-list.component.scss']
})
export class AccountsListComponent {

    accountsList: Array<AccountModel>;

    constructor(private _accountsService: AccountsService) {
        this._accountsService.getAccounts()
            .subscribe((list: Array<AccountModel>) => {
                    this.accountsList = list;
                }
            )
    }
}
