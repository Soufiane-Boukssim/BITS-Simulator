import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NdcStateComponent } from './ndc-state.component';

describe('NdcStateComponent', () => {
  let component: NdcStateComponent;
  let fixture: ComponentFixture<NdcStateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NdcStateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NdcStateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
