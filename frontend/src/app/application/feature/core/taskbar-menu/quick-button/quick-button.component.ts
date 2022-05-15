import { Component, OnInit, Input } from '@angular/core';
import {WindowManagementService} from "../../../../service/windowState/window-management.service";

@Component({
  selector: 'app-quick-button',
  templateUrl: './quick-button.component.html',
  styleUrls: ['./quick-button.component.scss']
})
export class QuickButtonComponent implements OnInit {

  @Input()
  icon!: string;

  constructor() { }

  ngOnInit(): void {
  }

}
