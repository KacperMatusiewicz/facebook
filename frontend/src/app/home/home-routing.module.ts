import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginPageComponent} from "./feature/login-page/login-page.component";
import {RegisterPageComponent} from "./feature/register-page/register-page.component";
import {RegistrationResponseComponent} from "./ui/registration-response/registration-response.component";
import {LandingPageComponent} from "./feature/landing-page/landing-page.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent,
    outlet: 'landing-page-routing'
  },
  {
    path: 'register',
    component: RegisterPageComponent
  },
  {
    path: 'confirmation-send',
    component: RegistrationResponseComponent
  },
  {
    path: "**",
    component: LandingPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
