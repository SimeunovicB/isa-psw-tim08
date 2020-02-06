import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router) { }

  getPending() {
    return this.http.get("http://localhost:9090/api/vacation/getPending")
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
    return this.http.post("http://localhost:9090/api/vacation/create", {
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
    return this.http.post("http://localhost:9090/api/vacation/approve", {
      id: id,
    })
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }

  denyRequest(id:any, razlog:any) {
    return this.http.post("http://localhost:9090/api/vacation/decline", {
      id: id,
      razlog: razlog
    })
    .pipe(
      map((response: Response) => {
        const data = response;
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }
}
