import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentTypeService {
  baseUrl = 'http://localhost:9090' + '/api/appointmenttype'

  constructor(private http: HttpClient) { }

  addType(type :String) {
    return this.http.post(`${this.baseUrl}/addAppointmentType`,{
      name: type
    }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err.text);
      })
    )
  }

  getAll() {
    return this.http.get(`${this.baseUrl}/getAll`)
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      );
  }
}
