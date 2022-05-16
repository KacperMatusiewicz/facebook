import {Component, ElementRef, Input, OnInit} from '@angular/core';
import {WindowManagementService} from "../../../service/windowState/window-management.service";
import {WindowType} from "../../../service/windowState/window-type";
import {DesktopWindow} from "../../../service/windowState/desktop-window";
import {UserSearchService} from "../user-search.service";
import {UserSearchQuery} from "../user-search-query";
import {SearchedUserResult} from "../searched-user-result";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.scss']
})
export class UserSearchComponent implements OnInit, DesktopWindow {
  @Input()
  userId: number | undefined;
  windowId: number;
  icon: string;
  style: CSSStyleDeclaration;
  title: string;

  queryResults: SearchedUserResult[];

  form: FormGroup;
  searchInput = new FormControl('', Validators.required);

  constructor(
    private fb: FormBuilder,
    private windowManagementService: WindowManagementService,
    private elementRef: ElementRef,
    private userSearchService: UserSearchService
  ) {
    this.queryResults = [];
    this.title = "Find People";
    this.style = elementRef.nativeElement.style;
    this.icon = "assets/icons/user-search-panel.png";
    this.windowId = windowManagementService.getId();
    this.form = fb.group({
      searchInput: this.searchInput
    });
    this.searchInput.valueChanges.subscribe(
      () =>{ this.search(); }
    );
  }

  ngOnInit(): void {
  }

  search() {
    this.userSearchService
      .search(new UserSearchQuery(this.searchInput.value))
      .subscribe(
        (response: SearchedUserResult[]) => this.queryResults = response,
        error => window.alert(error.error)
      );
  }

  focus(): void {
    this.windowManagementService.focusWindow(this.windowId);
  }

  maximize(): void {
    this.windowManagementService.maximizeWindow(this.windowId);
  }

  minimize(): void {
    this.windowManagementService.minimizeWindow(this.windowId);
  }

  setId(windowId: number): void {
    this.windowId = windowId;
  }

  getIcon(): string | null {
    return this.icon;
  }

  close(): void {
    this.windowManagementService.closeWindow(this.windowId);
  }

  getTitle(): string{
    return this.title;
  }

  openProfile(userId: number) {
    this.windowManagementService.openWindow({
      windowType: WindowType.UserProfilePage,
      content: {
        userId: userId
      }
    });
  }
}
