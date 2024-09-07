import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvertersComponent } from './converters.component';

describe('ConvertersComponent', () => {
  let component: ConvertersComponent;
  let fixture: ComponentFixture<ConvertersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConvertersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConvertersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
