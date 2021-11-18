import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegisterComponent} from './components/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
    declarations: [
        RegisterComponent
    ],
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule
    ]
})
export class RegisterModule {
}
