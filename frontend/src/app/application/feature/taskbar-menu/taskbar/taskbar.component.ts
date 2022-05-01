import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'taskbar',
  templateUrl: './taskbar.component.html',
  styleUrls: ['./taskbar.component.scss']
})
export class TaskbarComponent implements OnInit {

  menuVisibility: boolean;

  @Output()
  userProfileClickEvent = new EventEmitter<any>();
  @Output()
  logOffEvent = new EventEmitter<any>();

  constructor() {
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
}
