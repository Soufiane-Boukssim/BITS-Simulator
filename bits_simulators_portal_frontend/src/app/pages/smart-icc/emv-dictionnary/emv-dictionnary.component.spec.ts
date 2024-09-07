import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmvDictionnaryComponent } from './emv-dictionnary.component';

describe('EmvDictionnaryComponent', () => {
  let component: EmvDictionnaryComponent;
  let fixture: ComponentFixture<EmvDictionnaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmvDictionnaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmvDictionnaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
