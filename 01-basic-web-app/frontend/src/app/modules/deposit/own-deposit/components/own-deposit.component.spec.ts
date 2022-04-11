import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnDepositComponent } from './own-deposit.component';

describe('OwnDepositComponent', () => {
  let component: OwnDepositComponent;
  let fixture: ComponentFixture<OwnDepositComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnDepositComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnDepositComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
