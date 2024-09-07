import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalParametersComponent } from './global-parameters.component';

describe('GlobalParametersComponent', () => {
  let component: GlobalParametersComponent;
  let fixture: ComponentFixture<GlobalParametersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GlobalParametersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GlobalParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
