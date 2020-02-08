import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt'

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePassword implements OnInit {
  password: string;
  password2: string;
  oldPassword: string;
  //loggedUser: any;
  //id: number;
  userMail: any;
  helper : any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
  }

  promeni() {
    if (this.password === this.password2) {
      this.userService.UpdateUserPassword(this.password, this.oldPassword)
        .subscribe(
          (user: any) => {
            this.router.navigate(['/login']);
            this.cookieService.set('loggedUser', '');
          }, (error) => alert(error.text)
        );
    } else {
      alert('Morate uneti istu lozinku dva puta!');
    }
  }
  /*getUser() {
    this.userService.getUserByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.id = this.loggedUser.id;
          
        }
      )
  }*/



}
