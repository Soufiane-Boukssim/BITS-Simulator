import { TestBed } from '@angular/core/testing';

import { NdcDisplayLayoutService } from './ndc-display-layout.service';

describe('NdcDisplayLayoutService', () => {
  let service: NdcDisplayLayoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NdcDisplayLayoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
