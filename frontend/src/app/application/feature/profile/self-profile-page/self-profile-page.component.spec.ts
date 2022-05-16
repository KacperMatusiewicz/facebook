import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfProfilePageComponent } from './self-profile-page.component';

describe('SelfProfilePageComponent', () => {
  let component: SelfProfilePageComponent;
  let fixture: ComponentFixture<SelfProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelfProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
