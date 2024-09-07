import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseAndTesterComponent } from './analyse-and-tester.component';

describe('AnalyseAndTesterComponent', () => {
  let component: AnalyseAndTesterComponent;
  let fixture: ComponentFixture<AnalyseAndTesterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalyseAndTesterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalyseAndTesterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
