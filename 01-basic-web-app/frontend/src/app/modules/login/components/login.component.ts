import {Component, EventEmitter} from '@angular/core';
import {LoginStatusEnum} from "../utils/login.enums";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginFinishedEventEmitter: EventEmitter<LoginStatusEnum> = new EventEmitter<LoginStatusEnum>();

  formGroup: FormGroup = new FormGroup({
    customerId: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(private _loginService: LoginService) { }

  onLoginClicked() {
    if (this.formGroup.valid) {

    }
  }
}
