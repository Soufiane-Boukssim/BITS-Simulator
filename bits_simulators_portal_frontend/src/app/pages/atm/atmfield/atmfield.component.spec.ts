import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ATMfieldComponent } from './atmfield.component';

describe('ATMfieldComponent', () => {
  let component: ATMfieldComponent;
  let fixture: ComponentFixture<ATMfieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ATMfieldComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ATMfieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
