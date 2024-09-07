import { TestBed } from '@angular/core/testing';

import { PosGlobalService } from './pos-global.service';

describe('PosGlobalService', () => {
  let service: PosGlobalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PosGlobalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
