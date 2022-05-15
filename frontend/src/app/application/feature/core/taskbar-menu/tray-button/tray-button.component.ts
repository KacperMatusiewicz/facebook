import {AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';

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
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.wrapper !== undefined) {
      if(changes.active.currentValue === true) {
        this.wrapper.nativeElement.classList.add("wrapper-active");
        this.button.classList.add("button-active");
      } else {
        this.wrapper.nativeElement.classList.remove("wrapper-active");
        this.button.classList.remove("button-active");
      }
    }
  }

  ngOnInit(): void {
  }
}
