import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

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
    return this.http.post("http://localhost:9090/api/clinic/addClinic", {
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
          return throwError(JSON.parse(err.text));
        })
      )
  }

  searchClinics(date:string,type:string) {
    return this.http.get("http://localhost:9090/api/appointment/findClinic?date="+date+"&type="+type)
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
  makeGradeClinic(clinicName:String,value:any,patientId:any){
    return this.http.post("http://localhost:9090/api/appointment/makeGradeClinic", {
      clinicName:clinicName,
      value:value,
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )  
  }
  makeGradeDoctor(doctorId:any,value:any,patientId:any){
    return this.http.post("http://localhost:9090/api/appointment/makeGradeDoctor", {
      doctorId:doctorId,
      value:value,
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )  
  }
  doctorGrade(patientId:any){
    return this.http.post("http://localhost:9090/api/appointment/doctorGrade", {
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )  
  }
  clinicGrade(patientId:any){
    return this.http.post("http://localhost:9090/api/appointment/clinicGrade", {
      patientId:patientId
  }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )  
  }
  makeApp(id:any, date:string, type:string,patientId:any) {
    return this.http.post("http://localhost:9090/api/appointment/makeApp", {
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
        return throwError(JSON.parse(err.text));
      })
    )  
}
  getDoctors(clinicname:string, date:string, type:string) {
    return this.http.get("http://localhost:9090/api/appointment/findClinic/doctors?clinicName="+clinicname+"&date="+date+"&type="+type)
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

  getClinic() {
    return this.http.get("http://localhost:9090/api/clinic")
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

  getClinics() {
    return this.http.get("http://localhost:9090/api/clinic/getClinics")
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

  getAdminsClinics(id:any) {
    return this.http.get("http://localhost:9090/api/clinic/getAdminsClinic?id="+id)
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

  updateClinic(
    name: string,
    address: string,
    description: string,
    id: number) {
    return this.http.post("http://localhost:9090/api/clinic/update", {
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
          return throwError(JSON.parse(err.text));
        })
      )
  }
}
