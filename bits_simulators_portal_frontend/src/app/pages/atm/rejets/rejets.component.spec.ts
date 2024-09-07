import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejetsComponent } from './rejets.component';

describe('RejetsComponent', () => {
  let component: RejetsComponent;
  let fixture: ComponentFixture<RejetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejetsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RejetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
