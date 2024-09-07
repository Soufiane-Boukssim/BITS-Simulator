import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CpaResultComponent } from './cpa-result.component';

describe('CpaResultComponent', () => {
  let component: CpaResultComponent;
  let fixture: ComponentFixture<CpaResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CpaResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CpaResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
