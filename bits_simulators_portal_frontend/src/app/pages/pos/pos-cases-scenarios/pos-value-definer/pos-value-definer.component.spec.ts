import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosValueDefinerComponent } from './pos-value-definer.component';

describe('PosValueDefinerComponent', () => {
  let component: PosValueDefinerComponent;
  let fixture: ComponentFixture<PosValueDefinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosValueDefinerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosValueDefinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
