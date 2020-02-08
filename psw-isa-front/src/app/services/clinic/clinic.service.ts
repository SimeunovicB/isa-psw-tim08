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
export class ClinicService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  addClinic(
    name: string,
    address: string,
    description: string,
    ) {
    return this.http.post(`${environment.baseUrl}/api/clinic/addClinic`, {
      name: name,
      address: address,
      description: description,
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

  searchClinics(date:string,type:string) {
    return this.http.get(`${environment.baseUrl}/api/appointment/findClinic?date=`+date+"&type="+type)
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
  makeGradeClinic(clinicName:String,value:any,patientId:any){
    return this.http.post(`${environment.baseUrl}/api/appointment/makeGradeClinic`, {
      clinicName:clinicName,
      value:value,
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )  
  }
  makeGradeDoctor(doctorId:any,value:any,patientId:any){
    return this.http.post(`${environment.baseUrl}/api/appointment/makeGradeDoctor`, {
      doctorId:doctorId,
      value:value,
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )  
  }
  doctorGrade(patientId:any){
    return this.http.post(`${environment.baseUrl}/api/appointment/doctorGrade`, {
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )  
  }
  clinicGrade(patientId:any){
    return this.http.post(`${environment.baseUrl}/api/appointment/clinicGrade`, {
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )  
  }
  makeApp(id:any, date:string, type:string,patientId:any) {
    return this.http.post(`${environment.baseUrl}/api/appointment/makeApp`, {
      id: id,
      date: date,
      type: type,
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )  
}
  getDoctors(clinicname:string, date:string, type:string) {
    return this.http.get(`${environment.baseUrl}/api/appointment/findClinic/doctors?clinicName=`+clinicname+"&date="+date+"&type="+type)
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

  getClinic() {
    return this.http.get(`${environment.baseUrl}/api/clinic`)
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

  getClinics() {
    return this.http.get(`${environment.baseUrl}/api/clinic/getClinics`)
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

  getAdminsClinics(id:any) {
    return this.http.get(`${environment.baseUrl}/api/clinic/getAdminsClinic?id=`+id)
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

  updateClinic(
    name: string,
    address: string,
    description: string,
    id: number) {
    return this.http.post(`${environment.baseUrl}/api/clinic/update`, {
      name: name,
      address: address,
      description: description,
      id: id
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
