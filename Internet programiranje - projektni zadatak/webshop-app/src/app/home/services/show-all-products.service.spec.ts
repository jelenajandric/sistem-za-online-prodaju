import { TestBed } from '@angular/core/testing';

import { ShowAllProductsService } from './show-all-products.service';

describe('ShowAllProductsService', () => {
  let service: ShowAllProductsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowAllProductsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
