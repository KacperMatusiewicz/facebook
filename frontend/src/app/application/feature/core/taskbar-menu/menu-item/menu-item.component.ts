import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.scss']
})
export class MenuItemComponent implements OnInit, AfterViewInit {

  @Input()
  hasChildren: boolean | undefined;
  @Input()
  displayValue: string | undefined;
  @Input()
  isChild: boolean | undefined;
  expanded: boolean;
  @Input()
  icon: string | undefined;
  @ViewChild("icon")
  iconPlaceholder!: ElementRef<HTMLElement>

  constructor() {
    this.expanded = false;
    this.hasChildren = this.hasChildren !== undefined;
  }

  ngAfterViewInit(): void {
    if(this.icon !== undefined) {
      this.iconPlaceholder.nativeElement.style.backgroundImage = `url("${this.icon}")`;
    } else {
      this.iconPlaceholder.nativeElement.style.backgroundImage = `url("assets/icons/fb-logo.png")`;
    }
  }

  ngOnInit(): void {
  }

  onElementHover() {
    if(this.hasChildren === true) {
      this.expanded = true;
    }
  }

  onElementStopHover() {
    if(this.hasChildren === true) {
      this.expanded = false;
    }
  }
}
