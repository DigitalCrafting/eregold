import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccountListAction, AccountsListComponent} from './accounts-list.component';
import {AccountsService, AccountTypeEnum, CurrencyEnum} from "../../../../services/accounts.service";
import {of} from "rxjs";
import {By} from "@angular/platform-browser";

describe('AccountsListComponent', () => {
    let component: AccountsListComponent;
    let fixture: ComponentFixture<AccountsListComponent>;

    let accountsService: AccountsService;

    beforeEach(async () => {
        accountsService = {
            getAccounts() {
                return of([
                    {
                        accountNumber: '12ERGD12345',
                        currentBalance: 12.0,
                        currency: CurrencyEnum.GLD,
                        type: AccountTypeEnum.DEBIT
                    },
                    {
                        accountNumber: '12ERGD67890',
                        currentBalance: 90.0,
                        currency: CurrencyEnum.GLD,
                        type: AccountTypeEnum.SAVING
                    }
                ])
            }
        } as AccountsService;
        await TestBed.configureTestingModule({
            declarations: [AccountsListComponent],
            providers: [
                {provide: AccountsService, useValue: accountsService}
            ]
        })
        .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(AccountsListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should show 2 accounts', () => {
       const rows = fixture.debugElement.queryAll(By.css('table > tbody > tr'));
       expect(rows.length).toEqual(2);
    });

    it('should emit event on \"add account\" click', () => {
        const spy = spyOn(component.accountsListEventEmitter, 'emit');
        const addAccountButton = fixture.debugElement.query(By.css('#addAccountButton'));

        addAccountButton.nativeElement.click();
        expect(spy).toHaveBeenCalledWith(AccountListAction.ADD_ACCOUNT);
    });
});
