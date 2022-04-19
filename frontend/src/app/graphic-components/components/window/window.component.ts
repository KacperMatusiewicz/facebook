import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TitleBarClick} from "./title-bar-click";

@Component({
  selector: 'window',
  templateUrl: './window.component.html',
  styleUrls: ['./window.component.scss']
})
export class WindowComponent implements OnInit {

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

  constructor() {
  }

  ngOnInit(): void {
  }

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

  //TODO:
  // resizable
  // onToOnClick
}
