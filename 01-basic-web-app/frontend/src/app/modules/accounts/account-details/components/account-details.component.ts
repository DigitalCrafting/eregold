import {Component, EventEmitter, OnInit} from '@angular/core';

@Component({
    selector: 'account-details',
    templateUrl: './account-details.component.html',
    styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {

    backToDetailsEventEmitter: EventEmitter<any> = new EventEmitter<any>();
    makeOwnTransferEventEmitter: EventEmitter<any> = new EventEmitter<any>();

    private accountNumber: string;

    constructor() {
    }

    setContext(accountNumber: string) {
        this.accountNumber = accountNumber;
    }

    ngOnInit(): void {
    }

    onGoBackClicked() {
        this.backToDetailsEventEmitter.emit();
    }

    onMakeOwnTransferClicked() {
        this.makeOwnTransferEventEmitter.emit();
    }
}
