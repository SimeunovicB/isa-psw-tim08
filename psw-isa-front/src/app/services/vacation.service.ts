import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(private http: Http,
    private cookieService: CookieService,
    private router: Router) { }

  getPending() {
    return this.http.get("http://localhost:9090/api/patient")
    .pipe(
      map((response: Response) => {
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    );
  }
}
