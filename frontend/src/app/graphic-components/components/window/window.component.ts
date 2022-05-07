import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {TitleBarClick} from "./title-bar-click";

@Component({
  selector: 'window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.scss']
})
export class WindowComponent implements OnInit, AfterViewInit {

  @Input()
  windowName: string | undefined;
  @Input()
  icon: string | undefined;
  @Input()
  minimizeBtnShow: string | undefined;
  @Input()
  maximizeBtnShow: string | undefined;
  @Input()
  closeBtnShow: string | undefined;

  @Input()
  resizable: string | undefined;

  @Output()
  onClose : EventEmitter<any> = new EventEmitter<any>();
  @Output()
  onMinimize : EventEmitter<any> = new EventEmitter<any>();
  @Output()
  onMaximize : EventEmitter<any> = new EventEmitter<any>();
  @Output()
  onTitleBarButtonClick: EventEmitter<TitleBarClick> = new EventEmitter<TitleBarClick>();
  @Output()
  onWindowClick = new EventEmitter<any>();

  @ViewChild(`buttonMinimize`)
  minimizeButton!: ElementRef<HTMLElement>;
  @ViewChild(`buttonMaximize`)
  maximizeButton!: ElementRef<HTMLElement>;
  @ViewChild(`buttonClose`)
  closeButton!: ElementRef<HTMLElement>;
  @ViewChild(`windowIcon`)
  windowIcon!: ElementRef<HTMLElement>;

  constructor() {
  }

  ngAfterViewInit(): void {
    if (this.maximizeBtnShow === 'false') {
      this.maximizeButton.nativeElement.style.display = "none";
    }
    if (this.minimizeBtnShow === 'false') {
      this.minimizeButton.nativeElement.style.display = "none";
    }
    if (this.closeBtnShow === 'false') {
      this.closeButton.nativeElement.style.display = "none";
    }
    if (this.icon !== undefined || this.icon === "") {
      this.windowIcon.nativeElement.style.backgroundImage = `url(${this.icon})`;
    } else {
      this.windowIcon.nativeElement.style.display = "none";
    }
  }

  ngOnInit(): void {}


  emitClosingEvent(): void {
    this.onClose.emit();
    this.onTitleBarButtonClick.emit(new TitleBarClick("close"));
  }
  emitMaximizingEvent(): void {
    this.onMaximize.emit();
    this.onTitleBarButtonClick.emit(new TitleBarClick("maximize"));
  }
  emitMinimizingEvent(): void {
    this.onMinimize.emit();
    this.onTitleBarButtonClick.emit(new TitleBarClick("minimize"));
  }

  emitOnWindowClickEvent($event : Event): void {
    let eventElement: HTMLElement = $event.target as HTMLElement;
    if(
      !eventElement.classList.contains("title-bar-button-inner") &&
      !eventElement.classList.contains("title-bar-button")
    ){
      this.onWindowClick.emit();
    }
  }
}
