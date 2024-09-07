import { TestBed } from '@angular/core/testing';

import { CaseSenaService } from './case-sena.service';

describe('CaseSenaService', () => {
  let service: CaseSenaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CaseSenaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
