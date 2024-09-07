import { TestBed } from '@angular/core/testing';

import { FieldProcessingService } from './field-processing.service';

describe('FieldProcessingService', () => {
  let service: FieldProcessingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FieldProcessingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
