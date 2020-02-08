import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentServiceService } from '../services/appointment-service/appointment-service.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Component({
  selector: 'app-predef-appointments',
  templateUrl: './predef-appointments.component.html',
  styleUrls: ['./predef-appointments.component.css']
})
export class PredefAppointmentsComponent implements OnInit {

  predefAppointments = [];
  helper: any;
  userId: any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private appointmentService: AppointmentServiceService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.getPredefAppointments();

  }
  getPredefAppointments() {
    this.appointmentService.getPredefAppointment()
      .subscribe(
        (data) => {
          console.log(data);
          this.predefAppointments = Object.assign([], (data));
        }
      )
  }
  zakazi(appId: any) {
    this.appointmentService.reserve(appId, this.userId)
      .subscribe(
        (data) => {
          console.log(data);
          this.getPredefAppointments();
        }
      )

  }
}
