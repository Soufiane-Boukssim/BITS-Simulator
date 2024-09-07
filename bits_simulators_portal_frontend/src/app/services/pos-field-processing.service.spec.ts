import { TestBed } from '@angular/core/testing';

import { PosFieldProcessingService } from './pos-field-processing.service';

describe('PosFieldProcessingService', () => {
  let service: PosFieldProcessingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PosFieldProcessingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
