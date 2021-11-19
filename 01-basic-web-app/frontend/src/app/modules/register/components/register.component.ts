import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EregoldRegisterValidators} from "../utils/register.validators";
import {RegisterService} from "../service/register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  formGroup: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    passwordCheck: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required)
  }, EregoldRegisterValidators.PasswordGroupValidator);

  constructor(private _registerService: RegisterService) { }

  onCancelClicked() {
    // TODO event emitter
  }

  onRegisterClicked() {
    if (this.formGroup.valid) {
      // TODO call _registerService.register
    }
  }
}
