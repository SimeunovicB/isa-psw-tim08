import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {environment} from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  getAllPatients() {
    return this.http.get(`${environment.baseUrl}/api/patient/getAllPatients`)
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

  getDocPatients(id: any) {
    return this.http.post(`${environment.baseUrl}/api/patient/getDocPatients`, {
      id: id
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

  searchPatients(ime:string,prezime:string,jmbg:string) {
    return this.http.get(`${environment.baseUrl}/api/patient/findPatients?name=`+ime+"&lastname="+prezime+"&jmbg="+jmbg)
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
  searchDoctors(ime:string,prezime:string,ocena:string) {
    return this.http.get(`${environment.baseUrl}/api/patient/findDoctors?name=`+ime+
    "&lastname="+prezime+"&ocena="+ocena)
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
}
