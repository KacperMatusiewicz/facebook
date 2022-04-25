import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ApplicationRoutingModule } from './application-routing.module';
import { HomePageComponent } from './feature/home-page/home-page.component';
import {GraphicComponentsModule} from "../graphic-components/graphic-components.module";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [
    HomePageComponent
  ],
    imports: [
        CommonModule,
        ApplicationRoutingModule,
        GraphicComponentsModule,
        CoreModule
    ]
})
export class ApplicationModule { }
