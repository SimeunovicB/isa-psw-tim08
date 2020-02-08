import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import {environment} from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  getPending() {
    return this.http.get(`${environment.baseUrl}/api/vacation/getPending`)
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    );
  }

  createRequest(startDate:any, endDate:any, id:any) {
    return this.http.post(`${environment.baseUrl}/api/vacation/create`, {
      startDate: startDate,
      endDate: endDate,
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
    );
  }

  acceptRequest(id) {
    return this.http.post(`${environment.baseUrl}/api/vacation/approve`, {
      id: id,
    })
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: Response) => {
        return throwError(err);
      })
    );
  }

  denyRequest(id:any, razlog:any) {
    return this.http.post(`${environment.baseUrl}/api/vacation/decline`, {
      id: id,
      razlog: razlog
    })
    .pipe(
      map((response: Response) => {
        const data = response;
        return data;
      }),
      catchError((err: Response) => {
        return throwError(err);
      })
    );
  }
}
