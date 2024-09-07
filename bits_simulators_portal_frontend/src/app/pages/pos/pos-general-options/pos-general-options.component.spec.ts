import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosGeneralOptionsComponent } from './pos-general-options.component';

describe('PosGeneralOptionsComponent', () => {
  let component: PosGeneralOptionsComponent;
  let fixture: ComponentFixture<PosGeneralOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosGeneralOptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosGeneralOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
