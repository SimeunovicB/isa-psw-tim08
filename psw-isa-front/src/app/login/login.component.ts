import { Component, OnInit, NgModule } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { Http } from '@angular/http';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  helper : any;

  constructor(private http: Http,
    private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    this.userService.postUser(this.email, this.password)
    .subscribe(
      (user: any) => {
        this.helper = new JwtHelperService();
        this.cookieService.set('loggedUser', JSON.stringify(user));
       

        let status = this.helper.decodeToken(this.cookieService.get('token')).status;
        let userType = this.helper.decodeToken(this.cookieService.get('token')).type;
        console.log(status + userType)
        if(userType === "P" && status !== "ACTIVE"){
          console.log('dosao da rerutiram')
          this.router.navigate(['/login']);
        }else{
          this.router.navigate(['/']);
        }
        
      }, (error) => alert(error.text)
    );
  }

}
