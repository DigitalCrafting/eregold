import {Component, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountTypeEnum, CurrencyEnum} from "../../../../services/accounts.service";

@Component({
  selector: 'account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.scss']
})
export class AccountCreateComponent {
  accountCreateEventEmitter: EventEmitter<AccountCreateAction> = new EventEmitter<AccountCreateAction>();

  formGroup = new FormGroup({
    accountType: new FormControl(AccountTypeEnum.DEBIT, Validators.required),
    currency: new FormControl(CurrencyEnum.GLD, Validators.required)
  });

  currencyOptions = [
    {
      value: CurrencyEnum.GLD,
      display: CurrencyEnum.GLD
    }
  ];

  accountTypeOptions = [
    {
      value: AccountTypeEnum.DEBIT,
      display: 'Debit'
    },
    {
      value: AccountTypeEnum.SAVING,
      display: 'Saving'
    }
  ]

  onCancelClicked() {
    this.accountCreateEventEmitter.emit(AccountCreateAction.SHOW_LIST);
  }

  onAddClicked() {
    if (this.formGroup.valid) {
      // TODO Api call
      this.accountCreateEventEmitter.emit(AccountCreateAction.SHOW_LIST);
    }
  }
}

export type AccountCreateAction = 'SHOW_LIST';
export const AccountCreateAction = {
  SHOW_LIST: 'SHOW_LIST' as AccountCreateAction
};