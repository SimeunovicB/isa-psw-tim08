import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {environment} from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class AppointmentRequestService {

  constructor(private http: HttpClient) { }

  addRequest(
    doctor: any,
    patient: any,
    date: any,
    type: string
    ) {
      console.log(doctor,patient,date,type);
    return this.http.post(`${environment.baseUrl}/api/appointmentrequest/addAppointmentRequest`, {
      doctor: doctor,
      patient: patient,
      date: date,
      type: type
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }
}
