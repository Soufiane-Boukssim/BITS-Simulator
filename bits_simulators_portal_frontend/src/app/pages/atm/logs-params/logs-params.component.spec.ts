import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogsParamsComponent } from './logs-params.component';

describe('LogsParamsComponent', () => {
  let component: LogsParamsComponent;
  let fixture: ComponentFixture<LogsParamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogsParamsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogsParamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
