import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmvCpaAnalyserComponent } from './emv-cpa-analyser.component';

describe('EmvCpaAnalyserComponent', () => {
  let component: EmvCpaAnalyserComponent;
  let fixture: ComponentFixture<EmvCpaAnalyserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmvCpaAnalyserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmvCpaAnalyserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
