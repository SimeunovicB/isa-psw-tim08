import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  registerAdmin(
    firstname: string,
    lastname: string,
    address: string,
    username: string,
    clinic: string) {
    return this.http.post("http://localhost:9090/api/administrator/registerAdmin", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      username: username,
      clinic_id: clinic
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
  registerClinicCentreAdmin(
    firstname: string,
    lastname: string,
    address: string,
    username: string,
  ) {
    return this.http.post("http://localhost:9090/api/administrator/registerClinicCentreAdmin", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      username: username,
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

  createDoctor(
    firstname: string,
    lastname: string,
    address: string,
    username: string,
    clinic_id: any
  ) {
    return this.http.post("http://localhost:9090/api/administrator/createDoctor", {
      firstName: firstname,
      lastName: lastname,
      address: address,
      password: "123",
      username: username,
      status: "PENDING",
      clinic_id: clinic_id
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

  getAdminsDoctors(id:any) {
    return this.http.post("http://localhost:9090/api/administrator/getAdminDoctors", {
      id:id
    })
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

  deleteDoctor(id:any) {
    return this.http.post("http://localhost:9090/api/administrator/deleteDoctor", {
      id:id
    })
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

  getDoctorGrades(id:any) {
    return this.http.post("http://localhost:9090/api/administrator/getAdminDoctorsGrades", {
      id:id
    })
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

  financialReport(beginDate:any, endDate:any, id:any) {
    return this.http.post("http://localhost:9090/api/administrator/financialReport", {
      beginDate:beginDate,
      endDate:endDate,
      id:id
    })
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

  averageClinicGrade(id:any) {
    return this.http.post("http://localhost:9090/api/administrator/averageClinicGrade", {
      id:id
    })
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
