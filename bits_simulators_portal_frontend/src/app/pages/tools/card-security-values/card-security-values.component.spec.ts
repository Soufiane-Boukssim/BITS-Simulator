import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardSecurityValuesComponent } from './card-security-values.component';

describe('CardSecurityValuesComponent', () => {
  let component: CardSecurityValuesComponent;
  let fixture: ComponentFixture<CardSecurityValuesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardSecurityValuesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardSecurityValuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
