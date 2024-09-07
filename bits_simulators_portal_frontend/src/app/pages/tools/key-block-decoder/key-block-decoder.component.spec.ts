import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeyBlockDecoderComponent } from './key-block-decoder.component';

describe('KeyBlockDecoderComponent', () => {
  let component: KeyBlockDecoderComponent;
  let fixture: ComponentFixture<KeyBlockDecoderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KeyBlockDecoderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KeyBlockDecoderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
