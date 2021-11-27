import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginModule} from "./modules/login/login.module";
import {RegisterModule} from "./modules/register/register.module";
import {DashboardModule} from "./modules/dashboard/dashboard.module";
import {AuthInterceptor} from "./modules/common/auth.interceptor";
import {HTTP_INTERCEPTORS} from "@angular/common/http";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        LoginModule,
        RegisterModule,
        DashboardModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
