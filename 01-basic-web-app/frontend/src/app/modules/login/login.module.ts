import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './components/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginService} from "./services/login.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [
    LoginService
  ]
})
export class LoginModule { }
