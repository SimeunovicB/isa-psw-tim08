import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
@Component({
  selector: 'app-home-patient',
  templateUrl: './home-patient.component.html',
  styleUrls: ['./home-patient.component.css']
})
export class HomePatientComponent implements OnInit {

  helper: any;
  userId : any;
  constructor(private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    
  }

}
