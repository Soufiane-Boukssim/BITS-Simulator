import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LuhnAlgorithmComponent } from './luhn-algorithm.component';

describe('LuhnAlgorithmComponent', () => {
  let component: LuhnAlgorithmComponent;
  let fixture: ComponentFixture<LuhnAlgorithmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LuhnAlgorithmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LuhnAlgorithmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
