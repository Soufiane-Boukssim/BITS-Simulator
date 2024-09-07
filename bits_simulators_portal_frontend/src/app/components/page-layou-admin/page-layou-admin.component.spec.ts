import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageLayouAdminComponent } from './page-layou-admin.component';

describe('PageLayouAdminComponent', () => {
  let component: PageLayouAdminComponent;
  let fixture: ComponentFixture<PageLayouAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageLayouAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PageLayouAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
