import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AppointmentServiceService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  getAppointmentRequests() {
    return this.http.get("http://localhost:9090/api/administrator/getAppointmentRequests")
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )
  }

  makeNewAppointment(
      appointmentRequestId : any,
      doctor : any,
      patient : any,
      room : any,
      date : any,
      type : any,
    ) {
    return this.http.post("http://localhost:9090/api/administrator/makeAppointment", {
      appointmentRequestId : appointmentRequestId,
      doctor : doctor,
      patient : patient,
      room : room,
      date : date,
      type : type,
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(JSON.parse(err.text));
        })
      )
  }
}
