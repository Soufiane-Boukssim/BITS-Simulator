import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckboxSquareComponent } from './checkbox-square.component';

describe('CheckboxSquareComponent', () => {
  let component: CheckboxSquareComponent;
  let fixture: ComponentFixture<CheckboxSquareComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckboxSquareComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckboxSquareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
