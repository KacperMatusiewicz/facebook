import {AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild, ViewContainerRef} from '@angular/core';
import {WindowManagementService} from "../../../../service/windowState/window-management.service";
import {WindowDto} from "../../../../service/windowState/window-dto";
import {WindowType} from "../../../../service/windowState/window-type";

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
    private windowManagementService: WindowManagementService) {
    this.menuVisibility = false;
  }

  ngOnInit(): void {
  }

  toggleMenuVisibility(){
    let audio = document.createElement("audio");
    audio.src = "assets/sounds/click.mp3";
    audio.volume = 0.5;
    audio.play();
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

  openChangePasswordPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.ChangePasswordPage
    });
  }
  openDeleteAccountPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.DeleteAccountPage
    });
  }
  openCreatePostPage() {
    this.windowManagementService.openWindow({
      windowType: WindowType.CreatePostPage
    });
  }
}
