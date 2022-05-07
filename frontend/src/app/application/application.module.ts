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
import { SystemTrayComponent } from './feature/taskbar-menu/system-tray/system-tray.component';
import { StartMenuUserProfileComponent } from './feature/taskbar-menu/start-menu-user-profile/start-menu-user-profile.component';
import {UserDetailsService} from "./service/user-details.service";
import { UserProfilePageComponent } from './feature/user-profile-page/user-profile-page.component';
import {DesktopComponent} from "./feature/home-page/desktop/desktop.component";
import {DesktopIconComponent} from "./feature/home-page/desktop-icon/desktop-icon.component";
import {WindowManagementService} from "./service/windowState/window-management.service";
import { TaskbarItemComponent } from './feature/taskbar-menu/taskbar-item/taskbar-item.component';


@NgModule({
  declarations: [
    HomePageComponent,
    MenuItemComponent,
    MenuComponent,
    TrayButtonComponent,
    TaskbarComponent,
    SystemTrayComponent,
    StartMenuUserProfileComponent,
    UserProfilePageComponent,
    DesktopComponent,
    DesktopIconComponent,
    TaskbarItemComponent
  ],
    imports: [
        CommonModule,
        ApplicationRoutingModule,
        GraphicComponentsModule,
        CoreModule
    ],
  providers: [
    UserDetailsService,
    WindowManagementService
  ]
})
export class ApplicationModule { }
