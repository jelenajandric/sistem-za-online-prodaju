import { TestBed } from '@angular/core/testing';

import { ShowClientServiceService } from './show-client-service.service';

describe('ShowClientServiceService', () => {
  let service: ShowClientServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowClientServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
