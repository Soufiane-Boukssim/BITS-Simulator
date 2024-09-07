import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PinExtractComponent } from './pin-extract.component';

describe('PinExtractComponent', () => {
  let component: PinExtractComponent;
  let fixture: ComponentFixture<PinExtractComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PinExtractComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PinExtractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
