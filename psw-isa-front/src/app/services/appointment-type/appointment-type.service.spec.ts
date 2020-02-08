import { TestBed } from '@angular/core/testing';

import { AppointmentTypeService } from './appointment-type.service';
import {environment} from '../../../environments/environment'

describe('AppointmentTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppointmentTypeService = TestBed.get(AppointmentTypeService);
    expect(service).toBeTruthy();
  });
});
