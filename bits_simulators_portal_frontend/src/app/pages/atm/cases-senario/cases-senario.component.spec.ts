import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesSenarioComponent } from './cases-senario.component';

describe('CasesSenarioComponent', () => {
  let component: CasesSenarioComponent;
  let fixture: ComponentFixture<CasesSenarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasesSenarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CasesSenarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
