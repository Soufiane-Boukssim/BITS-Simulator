import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EMVParametersComponent } from './emvparameters.component';

describe('EMVParametersComponent', () => {
  let component: EMVParametersComponent;
  let fixture: ComponentFixture<EMVParametersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EMVParametersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EMVParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
