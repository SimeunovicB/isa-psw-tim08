import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpModule } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: Http,
    private cookieService: CookieService) { }

  postUser(email: string, password: string) {
    return this.http.post("http://localhost:9090/api/person/login", {
      email: email,
      password: password
    })
    .pipe(
      map((response: Response) => {
        //console.log(response);
        //this.cookieService.set('loggedUser', JSON.stringify(response.json()));
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    )
  }
  RegUser( 
    firstname:string,
    lastname : string,
    address :string,
    jmbg : string,
    email:string,
    password : string) {
    return this.http.post("http://localhost:9090/api/patient/register", {
      firstName:firstname,
      lastName:lastname,
      address:address,
      jmbg:jmbg,
      email: email,
      password: password
    })
    .pipe(
      map((response: Response) => {
        //console.log(response);
        //this.cookieService.set('loggedUser', JSON.stringify(response.json()));
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    )
  }

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
