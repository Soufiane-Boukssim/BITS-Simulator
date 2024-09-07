import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PinBlockCalculatorsComponent } from './pin-block-calculators.component';

describe('PinBlockCalculatorsComponent', () => {
  let component: PinBlockCalculatorsComponent;
  let fixture: ComponentFixture<PinBlockCalculatorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PinBlockCalculatorsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PinBlockCalculatorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
