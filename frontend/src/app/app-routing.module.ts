import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginGuard} from "./core/guard/login.guard";
import {AuthGuard} from "./core/guard/auth.guard";

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () =>
      import('./home/home.module').then(
        (m) => m.HomeModule
      ),
    canActivate: [LoginGuard]
  },
  {
    path: 'application',
    loadChildren: () =>
      import('./application/application.module').then(
        (m) => m.ApplicationModule
      ),
    canActivate: [AuthGuard]
  },
  {
    path: '',
    redirectTo: 'application',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
