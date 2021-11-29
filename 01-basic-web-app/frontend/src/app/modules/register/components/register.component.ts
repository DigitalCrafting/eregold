import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EregoldRegisterValidators} from "../utils/register.validators";
import {RegisterRequest, RegisterService} from "../service/register.service";
import {Router} from "@angular/router";

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

    // TODO more validators
    formGroup: FormGroup = new FormGroup({
        email: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        passwordCheck: new FormControl('', Validators.required),
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required)
    }, EregoldRegisterValidators.PasswordGroupValidator);

    constructor(private _registerService: RegisterService,
                private _router: Router) {
    }

    onCancelClicked() {
        this._router.navigate(['login']);
    }

    onRegisterClicked() {
        // TODO restApiDispatcher that will handle common errors and show popup
        if (this.formGroup.valid) {
            let request: RegisterRequest = <RegisterRequest>{
                email: this.formGroup.get('email').value,
                password: [...this.formGroup.get('password').value],
                firstName: this.formGroup.get('firstName').value,
                lastName: this.formGroup.get('lastName').value,
            };


            this._registerService.register(this.formGroup.value).subscribe(
                (resp) => {
                    // TODO show successful message and redirect to login
                },
                (error) => {
                    // TODO show error
                }
            )
        }
    }
}
