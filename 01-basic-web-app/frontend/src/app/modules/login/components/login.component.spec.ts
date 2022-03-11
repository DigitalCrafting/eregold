import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {LoginService} from "../../../services/login.service";
import {Router} from "@angular/router";

describe('LoginComponent', () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;

    let loginService: LoginService;

    beforeEach(async () => {
        loginService = new LoginService(null);
        await TestBed.configureTestingModule({
            declarations: [LoginComponent],
            providers: [
                {provide: LoginService, useValue: loginService},
                {provide: Router, useValue: {}},
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(LoginComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
