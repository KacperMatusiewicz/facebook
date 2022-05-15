import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuickButtonComponent } from './quick-button.component';

describe('QuickButtonComponent', () => {
  let component: QuickButtonComponent;
  let fixture: ComponentFixture<QuickButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuickButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuickButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
