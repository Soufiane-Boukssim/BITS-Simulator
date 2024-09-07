import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UiMockupComponent } from './ui-mockup.component';

describe('UiMockupComponent', () => {
  let component: UiMockupComponent;
  let fixture: ComponentFixture<UiMockupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UiMockupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UiMockupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
