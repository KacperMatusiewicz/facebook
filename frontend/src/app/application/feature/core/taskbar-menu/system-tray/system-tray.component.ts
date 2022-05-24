import {AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {min} from "rxjs/operators";

@Component({
  selector: 'app-system-tray',
  templateUrl: './system-tray.component.html',
  styleUrls: ['./system-tray.component.scss']
})
export class SystemTrayComponent implements OnInit, AfterViewInit {

  clockElement!: HTMLElement | null;

  constructor() {
  }

  ngAfterViewInit() {
    this.clockElement = document.getElementById("system-clock") as HTMLElement;

    let self = this;
    setInterval(function (){
      let now = new Date(Date.now());
      let sec = now.getSeconds();

      let minutes = now.getMinutes().toString();
      let hours = now.getHours().toString();

      if(minutes.length < 2)
        minutes = `0${minutes}`;

      if(hours.length < 2)
        hours = `0${hours}`;



      if (self.clockElement !== null){
        if (sec % 2 == 0)
          self.clockElement.innerHTML = `${hours}:${minutes}`;
        else
          self.clockElement.innerHTML = `${hours} ${minutes}`;
      }
    },1000);


  }

  ngOnInit(): void {
  }
}
