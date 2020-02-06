import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient/patient.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients = [];
  helper: any;
  userMail: any;
  loggedUser: any;

  constructor(private router: Router,
    private userService: UserService,
    private patientService: PatientService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
    this.ucitajPacijente();
  }

  getUser() {
    this.userService.getDocByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.ucitajPacijente();
        }
      )
  }

  ucitajPacijente() {
    this.patientService.getAllPatients()
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }
}
