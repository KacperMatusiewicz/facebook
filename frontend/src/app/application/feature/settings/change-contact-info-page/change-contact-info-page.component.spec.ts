import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeContactInfoPageComponent } from './change-contact-info-page.component';

describe('ChangeContactInfoPageComponent', () => {
  let component: ChangeContactInfoPageComponent;
  let fixture: ComponentFixture<ChangeContactInfoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeContactInfoPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeContactInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
