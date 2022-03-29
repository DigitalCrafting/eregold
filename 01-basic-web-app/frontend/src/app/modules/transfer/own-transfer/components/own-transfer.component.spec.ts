import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OwnTransferComponent} from './own-transfer.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

describe('OwnTransferComponent', () => {
    let component: OwnTransferComponent;
    let fixture: ComponentFixture<OwnTransferComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                HttpClientModule,
                FormsModule,
                ReactiveFormsModule,
                CommonModule
            ],
            declarations: [OwnTransferComponent]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(OwnTransferComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
