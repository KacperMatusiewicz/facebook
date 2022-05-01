import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  @Output()
  userProfileClickEvent = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  emitUserProfileClick() {
    this.userProfileClickEvent.emit();
  }
}
