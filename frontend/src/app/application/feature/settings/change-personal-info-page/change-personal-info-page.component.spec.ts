import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePersonalInfoPageComponent } from './change-personal-info-page.component';

describe('ChangePersonalInfoPageComponent', () => {
  let component: ChangePersonalInfoPageComponent;
  let fixture: ComponentFixture<ChangePersonalInfoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangePersonalInfoPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePersonalInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
