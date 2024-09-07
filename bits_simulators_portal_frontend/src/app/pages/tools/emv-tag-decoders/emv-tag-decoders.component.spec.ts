import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmvTagDecodersComponent } from './emv-tag-decoders.component';

describe('EmvTagDecodersComponent', () => {
  let component: EmvTagDecodersComponent;
  let fixture: ComponentFixture<EmvTagDecodersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmvTagDecodersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmvTagDecodersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
