import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Iso8583BitmapComponent } from './iso8583-bitmap.component';

describe('Iso8583BitmapComponent', () => {
  let component: Iso8583BitmapComponent;
  let fixture: ComponentFixture<Iso8583BitmapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Iso8583BitmapComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Iso8583BitmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
