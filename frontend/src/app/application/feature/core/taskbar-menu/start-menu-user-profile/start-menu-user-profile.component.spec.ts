import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartMenuUserProfileComponent } from './start-menu-user-profile.component';

describe('StartMenuUserProfileComponent', () => {
  let component: StartMenuUserProfileComponent;
  let fixture: ComponentFixture<StartMenuUserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StartMenuUserProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StartMenuUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
