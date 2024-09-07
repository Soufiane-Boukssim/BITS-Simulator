import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesScenariosComponent } from './cases-scenarios.component';

describe('CasesScenariosComponent', () => {
  let component: CasesScenariosComponent;
  let fixture: ComponentFixture<CasesScenariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasesScenariosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CasesScenariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
