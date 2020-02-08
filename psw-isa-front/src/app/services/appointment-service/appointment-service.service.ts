import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../../environments/environment'


@Injectable({
  providedIn: 'root'
})
export class AppointmentServiceService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  getAppointmentRequests() {
    return this.http.get(`${environment.baseUrl}/api/administrator/getAppointmentRequests`)
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )
  }
  getByDoctorIdForCalendar(doctorId : string) {
    
    return this.http.get(`${environment.baseUrl}/api/appointment/getAppointmentsForDoctor?doctorId=a` + doctorId)
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
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
    return this.http.post(`${environment.baseUrl}/api/administrator/makeAppointment`, {
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
          return throwError(err)
        })
      )
  }
  getPredefAppointment() {
    return this.http.get(`${environment.baseUrl}/api/appointment/getPredefAppointment`)
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }
  reserve(appId : any,currentUserId :any) {
    return this.http.post(`${environment.baseUrl}/api/appointment/reservePredef`,{
        patientId : currentUserId,
        appointmentId : appId

    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }
  createPredef(currentDoctor : any,
    currentRoom : any,
    currentType : any,
    cena : any,
    popust :any,
    dat :any){
    return this.http.post(`${environment.baseUrl}/api/appointment/createPredef`,{
      doctorId :currentDoctor,
      roomId : currentRoom,
      typeId : currentType,
      cena : cena,
      popust : popust,
      dat : dat 
      
    })
    .pipe(
      map((response: any) => {
        const data = response
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    );
  }
  getIncomingAppointments() {
    return this.http.get(`${environment.baseUrl}/api/appointment/getIncomingAppointmnents`)
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
    }
    cancle(appId : any) {
      return this.http.post(`${environment.baseUrl}/api/appointment/cancleAppointment`,{
          id : appId
  
      })
        .pipe(
          map((response: any) => {
            const data = response
            return data;
          }),
          catchError((err: any) => {
            return throwError(err);
          })
        );
    }
}
