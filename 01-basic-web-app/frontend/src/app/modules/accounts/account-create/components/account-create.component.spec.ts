import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountCreateComponent } from './account-create.component';
import {AccountsService} from "../../../../services/accounts.service";

describe('AccountCreateComponent', () => {
  let component: AccountCreateComponent;
  let fixture: ComponentFixture<AccountCreateComponent>;

  let accountsService: AccountsService;

  beforeEach(async () => {
    accountsService = new AccountsService(null);
    await TestBed.configureTestingModule({
      declarations: [ AccountCreateComponent ],
      providers: [
        {provide: AccountsService, useValue: accountsService}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
