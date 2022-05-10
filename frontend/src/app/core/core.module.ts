import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import {AuthService} from "./auth/auth.service";
import {CookieService} from "ngx-cookie-service";
import {HttpClientModule} from "@angular/common/http";
import {SoundService} from "./sound/sound.service";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CoreRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    CookieService,
    SoundService
  ]
})
export class CoreModule { }
