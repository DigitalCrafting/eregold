import {Component, EventEmitter, OnInit} from '@angular/core';
import {EregoldUserContext} from "../../../../context/eregold-user-context";
import {AccountModel} from "../../../../models/account.models";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TransferService} from "../../../../services/transfer.service";
import {CurrencyEnum} from "../../../../models/enums";

@Component({
    selector: 'own-transfer',
    templateUrl: './own-transfer.component.html',
    styleUrls: ['./own-transfer.component.scss']
})
export class OwnTransferComponent implements OnInit {

    backEventEmitter: EventEmitter<any> = new EventEmitter<any>();

    srcAccountList: Array<AccountModel>;
    dstAccountList: Array<AccountModel>;

    formGroup: FormGroup = new FormGroup({
        srcAccount: new FormControl('', Validators.required),
        dstAccount: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required),
        amount: new FormControl(null, Validators.required), // TODO regex to match 2 decimal places
        currency: new FormControl({
            value: CurrencyEnum.GLD,
            disabled: true
        })
    });

    private initialAccountNumber: string;
    private allAccountsList: Array<AccountModel>;

    constructor(private _userContext: EregoldUserContext,
                private _transfersService: TransferService) {
    }

    setContext(accountNumber: string) {
        this.initialAccountNumber = accountNumber;
    }

    async ngOnInit() {
        this.allAccountsList = await this._userContext.getAccounts();
        this.srcAccountList = this.allAccountsList;
        this.dstAccountList = this.allAccountsList;

        this.initFormGroupHandlers();
        if (this.initialAccountNumber) {
            this.formGroup.get('srcAccount').setValue(this.initialAccountNumber);
        }
    }

    onCancelClicked() {
        this.backEventEmitter.emit();
    }

    onTransferClicked() {
        if (this.formGroup.valid) {
            this._transfersService.transfer(this.formGroup.getRawValue()).subscribe(async () => {
                await this._userContext.getAccounts(true);
                this.backEventEmitter.emit();
            });
        }
    }

    private initFormGroupHandlers() {
        this.formGroup.get('srcAccount').valueChanges.subscribe(chosenAcc => {
            this.dstAccountList = this.allAccountsList.filter(account => account.accountNumber !== chosenAcc)
            if (this.dstAccountList.length > 0) {
                this.formGroup.get('dstAccount').setValue(this.dstAccountList[0].accountNumber);
            }
        });
    }
}
