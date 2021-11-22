import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../services/login.service";
import {UserContext} from "../../common/user.context";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    formGroup: FormGroup = new FormGroup({
        userId: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
    });

    constructor(private _loginService: LoginService,
                private _userContext: UserContext,
                private _router: Router) {
    }

    ngOnInit() {
        if (this._userContext.isLoggedIn) {
            this._router.navigate(['ui'])
        }
    }

    async onLoginClicked() {
        if (this.formGroup.valid) {
            let pass = this.formGroup.get('password').value;
            let userId = this.formGroup.get('userId').value;
            // TODO change to getContext => authentication is done in filter anyways
            this._loginService.login(userId, pass).subscribe(
                (resp) => {
                    this._userContext.isLoggedIn = true;
                    this._router.navigate(['ui']);
                },
                (error) => {
                    // TODO message for user
                    console.log("Invalid credentials")
                });
        }
    }

    onRegisterClicked() {
        this._router.navigate(['register']);
    }
}
