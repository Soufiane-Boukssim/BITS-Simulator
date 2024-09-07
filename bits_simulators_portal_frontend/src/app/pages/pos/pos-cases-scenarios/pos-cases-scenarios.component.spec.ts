import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosCasesScenariosComponent } from './pos-cases-scenarios.component';

describe('PosCasesScenariosComponent', () => {
  let component: PosCasesScenariosComponent;
  let fixture: ComponentFixture<PosCasesScenariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosCasesScenariosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosCasesScenariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
