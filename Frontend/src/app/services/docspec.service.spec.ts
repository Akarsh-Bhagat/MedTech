import { TestBed } from '@angular/core/testing';

import { DocspecService } from './docspec.service';

describe('DocspecService', () => {
  let service: DocspecService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DocspecService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
