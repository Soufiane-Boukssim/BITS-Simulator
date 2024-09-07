import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SenariosComponent } from './senarios.component';

describe('SenariosComponent', () => {
  let component: SenariosComponent;
  let fixture: ComponentFixture<SenariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SenariosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SenariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
