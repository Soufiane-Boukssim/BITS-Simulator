import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosAcquirerProfileComponent } from './pos-acquirer-profile.component';

describe('PosAcquirerProfileComponent', () => {
  let component: PosAcquirerProfileComponent;
  let fixture: ComponentFixture<PosAcquirerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosAcquirerProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosAcquirerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
