import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userType: any;
  helper: any;
  userPatient = false;
  userAdmin = false;
  userNurse = false;
  userDoctor = false;


  constructor(private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userType = this.helper.decodeToken(this.cookieService.get('token')).type;
    if (this.userType === "P")
      this.userPatient = true;
    if (this.userType === "D")
      this.userDoctor = true;
    if (this.userType === "N")
      this.userNurse = true;
    if (this.userType === "A")
      this.userAdmin = true;
  }

}
