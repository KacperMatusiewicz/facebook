import {AfterViewInit, Component, ElementRef, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {LogoutService} from 'src/app/core/logout/logout.service';
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {WindowType} from "../../../service/windowState/window-type";
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, AfterViewInit {

  userProfileWindowVisibility: boolean;

  @ViewChild("desktopWindowOutlet", {read: ViewContainerRef})
  desktopWindowOutlet!: ViewContainerRef;

  constructor(private logoutService: LogoutService, private windowManagementService: WindowManagementService) {
    this.userProfileWindowVisibility = false;
  }

  ngOnInit(): void {
  }

  logout() {
    this.logoutService.logout();
  }

  userDetailsWindow() {
    /*
    if(!this.userProfileWindowVisibility){
      this.userProfileWindowVisibility = true;
    }*/
    this.windowManagementService.openWindow({
      windowType: WindowType.ProfilePage,
    });
  }

  settingsWindow() {

  }

  ngAfterViewInit(): void {
    this.windowManagementService.setWindowContainerRef(this.desktopWindowOutlet);
  }
}
