import {AfterViewInit, Component, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {LogoutService} from 'src/app/core/logout/logout.service';
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {WindowType} from "../../../service/windowState/window-type";
import {UserRelationsStoreService} from "../../../service/relation/store/user-relations-store.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, AfterViewInit {

  userProfileWindowVisibility: boolean;

  @ViewChild("desktopWindowOutlet", {read: ViewContainerRef})
  desktopWindowOutlet!: ViewContainerRef;

  constructor(
    private logoutService: LogoutService,
    private windowManagementService: WindowManagementService,
    private userRelationsStore: UserRelationsStoreService
  ) {
    this.userProfileWindowVisibility = false;
  }

  ngOnInit(): void {
  }

  logout() {
    this.logoutService.logout();
  }

  userDetailsWindow() {
    this.windowManagementService.openWindow({
      windowType: WindowType.SelftProfilePage,
    });
  }

  settingsWindow() {

  }

  ngAfterViewInit(): void {
    this.windowManagementService.setWindowContainerRef(this.desktopWindowOutlet);
  }
}
