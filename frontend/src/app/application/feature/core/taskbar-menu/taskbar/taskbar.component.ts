import {AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild, ViewContainerRef} from '@angular/core';
import {WindowManagementService} from "../../../../service/windowState/window-management.service";
import {WindowType} from "../../../../service/windowState/window-type";
import {SoundService} from "../../../../../core/sound/sound.service";
import {Sound} from "../../../../../core/sound/sound";

@Component({
  selector: 'taskbar',
  templateUrl: './taskbar.component.html',
  styleUrls: ['./taskbar.component.scss']
})
export class TaskbarComponent implements OnInit, AfterViewInit{

  menuVisibility: boolean;

  @Output()
  userProfileClickEvent = new EventEmitter<any>();
  @Output()
  logOffEvent = new EventEmitter<any>();

  @ViewChild("runningTasks", { read: ViewContainerRef })
  taskbar!: ViewContainerRef;

  constructor(
    private windowManagementService: WindowManagementService,
    private soundService: SoundService
  ) {
    this.menuVisibility = false;
  }

  ngOnInit(): void {
  }

  toggleMenuVisibility(){

    this.soundService.playSound(Sound.CLICK);

    let listener = (ev: MouseEvent) => {
      if(!(
        ev.clientY > window.innerHeight - 28 &&
        ev.clientX < 59
      )) {
        this.menuVisibility = false;
        document.removeEventListener("mouseup", listener);
      }
    }
    if (!this.menuVisibility) {
      this.menuVisibility = true;
      document.addEventListener("mouseup", listener);
      //kiedys mozna dodac zeby tylko klkniecie poza menu je wyłączało
    } else {
      this.menuVisibility = false;
      document.removeEventListener("mouseup", listener);
    }
  }


  emitLogOffEvent() {
    this.logOffEvent.emit();
  }

  emitUserProfileClickEvent() {
    this.userProfileClickEvent.emit();
  }

  ngAfterViewInit(){
    this.windowManagementService.setTaskbarContainerRef(this.taskbar);
  }

  openCreatePostPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.CreatePostPage
    });
  }
  openUserSearchPage(){
    this.windowManagementService.openWindow({
      windowType: WindowType.UserSearchPage
    })
  }
  openChangePasswordPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.ChangePasswordPage
    });
  }
  openChangeContactInfoPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.ChangeContactInfoPage
    });
  }
  openChangePersonalInfoPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.ChangePersonalInfoPage
    });
  }
  openDeleteAccountPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.DeleteAccountPage
    });
  }
}
