import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import {AuthService} from "./auth/auth.service";
import {CookieService} from "ngx-cookie-service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CoreRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    CookieService
  ]
})
export class CoreModule { }
