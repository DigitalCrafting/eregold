import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './components/dashboard.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

const routes: Routes = [
    {
        path: '',
        component: DashboardComponent
    }
];

@NgModule({
    imports: [
        HttpClientModule,
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule.forChild(routes),
    ],
    declarations: [
        DashboardComponent,
    ],
})
export class DashboardModule {
}
