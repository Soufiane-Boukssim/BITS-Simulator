import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosGlobalParametersComponent } from './pos-global-parameters.component';

describe('PosGlobalParametersComponent', () => {
  let component: PosGlobalParametersComponent;
  let fixture: ComponentFixture<PosGlobalParametersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosGlobalParametersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosGlobalParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
