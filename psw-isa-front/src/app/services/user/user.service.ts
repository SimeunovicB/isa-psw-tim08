import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})




export class UserService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  postUser(email: string, password: string) {
    return this.http.post(`${environment.baseUrl}/auth/login`, {
      username: email,
      password: password
    })
      .pipe(
        map((res: any) => {
          const helper = new JwtHelperService();
          const token = res.accessToken;
          console.log('token: ' + token)
          this.cookieService.set('token', token);


          const data = res;
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(err)
        })
      )
  }
  RegUser(
    firstname: string,
    lastname: string,
    address: string,
    jmbg: string,
    email: string,
    password: string) {
    return this.http.post(`${environment.baseUrl}/auth/register`, {
      firstName: firstname,
      lastName: lastname,
      address: address,
      jmbg: jmbg,
      username: email,
      password: password
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
  UpdateUserPassword(
    newPassword: string,
    oldPassword: string) {
    return this.http.post(`${environment.baseUrl}/api/person/changePassword`, {
      newPassword: newPassword,
      oldPassword: oldPassword
    })
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
  UpdateUser(
    firstname: string,
    lastname: string,
    address: string,
    id: number) {
    return this.http.post(`${environment.baseUrl}/api/person/update`, {
      firstName: firstname,
      lastName: lastname,
      address: address,
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

  getPending() {
    return this.http.get(`${environment.baseUrl}/api/patient`)
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

  getUserByMail(email: string) {
    return this.http.post(`${environment.baseUrl}/api/person/getByEmail`, {
      username: email
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

  getAdminByMail(email: string) {
    return this.http.get(`${environment.baseUrl}/api/person/getAdminByEmail?mail=`+email)
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

  getDocByMail(email: string) {
    return this.http.post(`${environment.baseUrl}/api/person/getDocByEmail`, {
      username: email
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

  getMedicalRecordInfo(id: Number) {
    return this.http.get(`${environment.baseUrl}/api/medicalRecord/getByPatientId?id=` + id)
      .pipe(
        map((response: any) => {
          console.log(response)
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }

  acceptUser(id) {
    return this.http.post(`${environment.baseUrl}/api/administrator/approveRegistration/` + id, {
      id: id,
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err.text);
        })
      );
  }

  denyUser(id) {
    return this.http.post(`${environment.baseUrl}/api/administrator/rejectRegistration/` + id, {
      id: id,
    })
      .pipe(
        map((response: any) => {
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err.text);
        })
      );
  }
  findDoctors() {
    return this.http.get(`${environment.baseUrl}/api/person/getAllDoctors`)
      .pipe(
        map((response: any) => {
          console.log(response)
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }

}
