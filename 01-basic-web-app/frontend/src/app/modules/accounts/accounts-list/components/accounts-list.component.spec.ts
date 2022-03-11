import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccountsListComponent} from './accounts-list.component';
import {AccountsService} from "../../../../services/accounts.service";
import {of} from "rxjs";

describe('AccountsListComponent', () => {
    let component: AccountsListComponent;
    let fixture: ComponentFixture<AccountsListComponent>;

    let accountsService: AccountsService;

    beforeEach(async () => {
        accountsService = {
            getAccounts() {
                return of([])
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
});
