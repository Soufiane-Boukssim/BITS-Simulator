import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosParametreOptionComponent } from './pos-parametre-option.component';

describe('PosParametreOptionComponent', () => {
  let component: PosParametreOptionComponent;
  let fixture: ComponentFixture<PosParametreOptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosParametreOptionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosParametreOptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
