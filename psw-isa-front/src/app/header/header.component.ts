import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  helper: any;

  constructor(
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService

  ) { }

  ngOnInit() {
  }
  homeClick() {
    this.router.navigate(['/']);
  }
  profClick() {
    this.router.navigate(['/profile'])
  }
  logoutClick() {
    this.cookieService.delete('token');
    //this.helper = new JwtHelperService();
    //console.log(this.helper.decodeToken(this.cookieService.get('token')));
    this.router.navigate(['/login'])
  }

}
