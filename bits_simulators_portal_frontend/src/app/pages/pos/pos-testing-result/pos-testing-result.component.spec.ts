import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosTestingResultComponent } from './pos-testing-result.component';

describe('PosTestingResultComponent', () => {
  let component: PosTestingResultComponent;
  let fixture: ComponentFixture<PosTestingResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosTestingResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosTestingResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
