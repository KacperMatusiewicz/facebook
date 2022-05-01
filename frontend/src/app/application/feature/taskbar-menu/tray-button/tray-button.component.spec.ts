import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrayButtonComponent } from './tray-button.component';

describe('TrayButtonComponent', () => {
  let component: TrayButtonComponent;
  let fixture: ComponentFixture<TrayButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrayButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrayButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
