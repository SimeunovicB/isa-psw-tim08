import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentServiceService } from '../services/appointment-service/appointment-service.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-incoming-examinations',
  templateUrl: './incoming-examinations.component.html',
  styleUrls: ['./incoming-examinations.component.css']
})
export class IncomingExaminationsComponent implements OnInit {

  predefAppointments = [];
  helper: any;
  userId : any;

  constructor(private router: Router,
    private cookieService: CookieService,
    private appointmentService : AppointmentServiceService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.getIncomingAppointments();

  }
  getIncomingAppointments() {
    this.appointmentService.getIncomingAppointments()
      .subscribe(
        (data) => {
          console.log(data);
          this.predefAppointments = Object.assign([], (data));
          this.predefAppointments = this.predefAppointments.filter(app => new Date(app.date) > new Date())
          console.log(this.predefAppointments);
        }
      )
  }
  otkazi(app : any){
    console.log(app)
    let d = new Date();
    let appDate = new Date(app.date);
    if(appDate.getDay()>= d.getDay()){
      this.appointmentService.cancle(app.appointmentId)
      .subscribe(
        (data) => {
          console.log(data);
          this.getIncomingAppointments();
        }
      )
    }else {
      alert('Ne mozete otkazati ovaj termin jer je previse blizu');
    }
    

  }

}
