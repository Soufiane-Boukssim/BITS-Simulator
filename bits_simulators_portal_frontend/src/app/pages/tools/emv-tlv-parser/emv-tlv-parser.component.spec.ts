import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmvTlvParserComponent } from './emv-tlv-parser.component';

describe('EmvTlvParserComponent', () => {
  let component: EmvTlvParserComponent;
  let fixture: ComponentFixture<EmvTlvParserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmvTlvParserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmvTlvParserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
