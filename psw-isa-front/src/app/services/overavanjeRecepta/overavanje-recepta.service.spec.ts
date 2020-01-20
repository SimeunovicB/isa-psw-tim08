import { TestBed } from '@angular/core/testing';

import { OveravanjeReceptaService } from './overavanje-recepta.service';

describe('OveravanjeReceptaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OveravanjeReceptaService = TestBed.get(OveravanjeReceptaService);
    expect(service).toBeTruthy();
  });
});
