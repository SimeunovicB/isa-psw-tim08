import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpModule } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt'
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: Http,
    private cookieService: CookieService,
    private router: Router
    
) { }

  postUser(email: string, password: string) {
    return this.http.post("http://localhost:9090/auth/login", {
      username: email,
      password: password
    })
    .pipe(
      map((response: Response) => {
        //console.log(response);
        //this.cookieService.set('loggedUser', JSON.stringify(response.json()));
        const helper = new JwtHelperService();
        const token = JSON.stringify(response);
        console.log(token)
        this.cookieService.set('token', token);
        

        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        console.log(err)
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
    return this.http.post("http://localhost:9090/auth/register", {
      firstName:firstname,
      lastName:lastname,
      address:address,
      jmbg:jmbg,
      username: email,
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
  UpdateUserPassword( 
    password :string,
    id:number) {
    return this.http.post("http://localhost:9090/api/person/changePassword", {
      password:password,
      id:id
    })
    .pipe(
      map((response: Response) => {
        const data = response.json();
        return data;
      }),
      catchError((err: Response) => {
        return throwError(JSON.parse(err.text()));
      })
    )
  }
  UpdateUser( 
    firstname:string,
    lastname : string,
    address :string,
    id:number) {
    return this.http.put("http://localhost:9090/api/person/update", {
      firstName:firstname,
      lastName:lastname,
      address:address,
      id:id
    })
    .pipe(
      map((response: Response) => {
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

  getUserByMail(email : string) {
    return this.http.post("http://localhost:9090/api/person/getByEmail",{
      username : email
    })
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

  acceptUser(id) {
    return this.http.put("http://localhost:9090/api/administrator/approveRegistration/"+id, {
      id: id,
    })
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

  denyUser(id) {
    return this.http.put("http://localhost:9090/api/administrator/rejectRegistration/"+id, {
      id: id,
    })
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
