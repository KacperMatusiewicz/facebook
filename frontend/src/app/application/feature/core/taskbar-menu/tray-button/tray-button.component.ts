import {AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'tray-button',
  templateUrl: './tray-button.component.html',
  styleUrls: ['./tray-button.component.scss']
})
export class TrayButtonComponent implements OnInit, OnChanges, AfterViewInit {

  @Input()
  disabled: boolean | undefined;

  @ViewChild("formWrapper")
  formWrapper!: ElementRef<HTMLElement>;

  @Input()
  active: boolean;

  @ViewChild("wrapper")
  wrapper!: ElementRef<HTMLElement>

  button!: HTMLElement


  constructor() {
    this.active = false;
  }

  ngAfterViewInit(): void {
    this.button = this.wrapper.nativeElement.getElementsByTagName("button").item(0) as HTMLElement;
    //console.log(this.button);
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if(this.wrapper !== undefined) {
      if(changes.active.currentValue === true) {
        this.wrapper.nativeElement.classList.add("wrapper-active");
        this.button.classList.add("button-active");
        console.log(this.button);
      } else {
        this.wrapper.nativeElement.classList.remove("wrapper-active");
        this.button.classList.remove("button-active");
        console.log(this.button);
      }
    }
  }

  ngOnInit(): void {
  }
}
