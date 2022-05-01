import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'win-button',
  templateUrl: './win-button.component.html',
  styleUrls: ['./win-button.component.scss']
})
export class WinButtonComponent implements OnInit {

  @Input()
  formGroup: FormGroup | undefined;

  @Input()
  disabled: boolean | undefined;

  @ViewChild("formWrapper")
  formWrapper!: ElementRef<HTMLElement>;
  constructor() {
    if (this.formGroup !== undefined) {
        this.formWrapper.nativeElement.setAttribute("formGroup", this.formGroup.value);
    }
  }

  ngOnInit(): void {
  }



}
