import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosValueBuilderComponent } from './pos-value-builder.component';

describe('PosValueBuilderComponent', () => {
  let component: PosValueBuilderComponent;
  let fixture: ComponentFixture<PosValueBuilderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosValueBuilderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosValueBuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
