import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTvrComponent } from './show-tvr.component';

describe('ShowTvrComponent', () => {
  let component: ShowTvrComponent;
  let fixture: ComponentFixture<ShowTvrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowTvrComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowTvrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
