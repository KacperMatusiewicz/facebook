import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ApplicationRoutingModule } from './application-routing.module';
import { HomePageComponent } from './feature/home-page/home-page.component';
import {GraphicComponentsModule} from "../graphic-components/graphic-components.module";
import {CoreModule} from "../core/core.module";
import {MenuItemComponent} from "./feature/taskbar-menu/menu-item/menu-item.component";
import {MenuComponent} from "./feature/taskbar-menu/menu/menu.component";
import {TrayButtonComponent} from "./feature/taskbar-menu/tray-button/tray-button.component";
import {TaskbarComponent} from "./feature/taskbar-menu/taskbar/taskbar.component";


@NgModule({
  declarations: [
    HomePageComponent,
    MenuItemComponent,
    MenuComponent,
    TrayButtonComponent,
    TaskbarComponent,
  ],
    imports: [
        CommonModule,
        ApplicationRoutingModule,
        GraphicComponentsModule,
        CoreModule
    ]
})
export class ApplicationModule { }
