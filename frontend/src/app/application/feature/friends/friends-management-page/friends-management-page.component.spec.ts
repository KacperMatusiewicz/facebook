import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendsManagementPageComponent } from './friends-management-page.component';

describe('FriendsManagementPageComponent', () => {
  let component: FriendsManagementPageComponent;
  let fixture: ComponentFixture<FriendsManagementPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FriendsManagementPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendsManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
