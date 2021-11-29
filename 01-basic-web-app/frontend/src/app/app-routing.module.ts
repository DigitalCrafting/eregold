import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule) },
    {path: 'register', loadChildren: () => import('./modules/register/register.module').then(m => m.RegisterModule)},
    {path: 'ui', loadChildren: () => import('./modules/dashboard/dashboard.module').then(m => m.DashboardModule)},
    {path: '**', redirectTo: 'login'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
