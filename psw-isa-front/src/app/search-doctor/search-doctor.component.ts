import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient/patient.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentServiceService } from '../services/appointment-service/appointment-service.service';

@Component({
  selector: 'app-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls: ['./search-doctor.component.css']
})
export class SearchDoctorComponent implements OnInit {
  doctors = [];
  ime = "";
  prezime = "";
  ocena = ""
  helper: any;
  userId: any;
  appointment: any;
  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService,
    private cookieService: CookieService,
    private appointmentService: AppointmentServiceService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;

    this.patientService.searchDoctors("", "", "")
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }
  onSubmit(form: NgForm) {
    this.findDoctors(this.ime, this.prezime, this.ocena);
  }
  findDoctors(ime: string, prezime: string, jmbg: string) {
    this.patientService.searchDoctors(this.ime, this.prezime, this.ocena)
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }
  changeSelectedAppointment(filterVal: any) {
    this.appointment = filterVal;
    console.log(this.appointment);
  }
  zakazi() {
    this.appointmentService.reserve(this.appointment, this.userId)
      .subscribe(
        (data) => {
          console.log(data);

        }
      )

  }
}
