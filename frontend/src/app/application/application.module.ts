import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ApplicationRoutingModule} from './application-routing.module';
import {HomePageComponent} from './feature/core/home-page/home-page.component';
import {GraphicComponentsModule} from "../graphic-components/graphic-components.module";
import {CoreModule} from "../core/core.module";
import {MenuItemComponent} from "./feature/core/taskbar-menu/menu-item/menu-item.component";
import {MenuComponent} from "./feature/core/taskbar-menu/menu/menu.component";
import {TrayButtonComponent} from "./feature/core/taskbar-menu/tray-button/tray-button.component";
import {TaskbarComponent} from "./feature/core/taskbar-menu/taskbar/taskbar.component";
import {SystemTrayComponent} from './feature/core/taskbar-menu/system-tray/system-tray.component';
import {
  StartMenuUserProfileComponent
} from './feature/core/taskbar-menu/start-menu-user-profile/start-menu-user-profile.component';
import {UserDetailsService} from "./service/user-details.service";
import {UserProfilePageComponent} from './feature/profile/user-profile-page/user-profile-page.component';
import {DesktopComponent} from "./feature/core/desktop/desktop.component";
import {DesktopIconComponent} from "./feature/core/desktop-icon/desktop-icon.component";
import {WindowManagementService} from "./service/windowState/window-management.service";
import {TaskbarItemComponent} from './feature/core/taskbar-menu/taskbar-item/taskbar-item.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ChangePasswordPageComponent} from "./feature/settings/change-password-page/change-password-page.component";
import {SettingsService} from "./feature/settings/settings.service";
import { DeleteAccountPageComponent } from './feature/settings/delete-account-page/delete-account-page.component';
import {DeleteAccountService} from "./feature/settings/delete-account-page/delete-account.service";
import { CreatePostPageComponent } from './feature/post/create-post-page/create-post-page.component';
import { ChangePersonalInfoPageComponent } from './feature/settings/change-personal-info-page/change-personal-info-page.component';
import { ChangeContactInfoPageComponent } from './feature/settings/change-contact-info-page/change-contact-info-page.component';
import { QuickButtonComponent } from './feature/core/taskbar-menu/quick-button/quick-button.component';
import { EditPostPageComponent } from './feature/post/edit-post-page/edit-post-page.component';
import { UserSearchComponent } from './feature/search/user-search/user-search.component';
import { SelfProfilePageComponent } from './feature/profile/self-profile-page/self-profile-page.component';


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
    TaskbarItemComponent,
    ChangePasswordPageComponent,
    DeleteAccountPageComponent,
    CreatePostPageComponent,
    ChangePersonalInfoPageComponent,
    ChangeContactInfoPageComponent,
    QuickButtonComponent,
    EditPostPageComponent,
    UserSearchComponent,
    SelfProfilePageComponent,
  ],
    imports: [
        CommonModule,
        ApplicationRoutingModule,
        GraphicComponentsModule,
        CoreModule,
        FormsModule,
        ReactiveFormsModule
    ],
  providers: [
    UserDetailsService,
    WindowManagementService,
    SettingsService,
    DeleteAccountService
  ]
})
export class ApplicationModule { }
