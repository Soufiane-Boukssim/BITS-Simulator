import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmvCardExplorerComponent } from './emv-card-explorer.component';

describe('EmvCardExplorerComponent', () => {
  let component: EmvCardExplorerComponent;
  let fixture: ComponentFixture<EmvCardExplorerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmvCardExplorerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmvCardExplorerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
