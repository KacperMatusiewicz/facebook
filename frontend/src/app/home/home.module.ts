import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { LoginPageComponent } from './feature/login-page/login-page.component';
import { RegisterPageComponent } from './feature/register-page/register-page.component';
import { RegistrationFormComponent } from './ui/registration-form/registration-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegistrationService} from "./service/registration.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { RegistrationResponseComponent } from './ui/registration-response/registration-response.component';
import {GraphicComponentsModule} from "../graphic-components/graphic-components.module";
import { LandingPageComponent } from './feature/landing-page/landing-page.component';
import { LoginFormComponent } from './ui/login-form/login-form.component';
import {LoginService} from "./service/login.service";


@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterPageComponent,
    RegistrationFormComponent,
    RegistrationResponseComponent,
    LandingPageComponent,
    LoginFormComponent
  ],
    imports: [
      CommonModule,
      GraphicComponentsModule,
      HomeRoutingModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule
    ],
  providers: [
    RegistrationService,
    LoginService
  ]
})
export class HomeModule { }
