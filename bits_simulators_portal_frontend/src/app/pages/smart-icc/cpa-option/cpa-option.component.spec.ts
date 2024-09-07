import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CpaOptionComponent } from './cpa-option.component';

describe('CpaOptionComponent', () => {
  let component: CpaOptionComponent;
  let fixture: ComponentFixture<CpaOptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CpaOptionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CpaOptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
