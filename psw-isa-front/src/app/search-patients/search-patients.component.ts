import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient/patient.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-search-patients',
  templateUrl: './search-patients.component.html',
  styleUrls: ['./search-patients.component.css']
})
export class SearchPatientsComponent implements OnInit {
  patients = [];
  ime = "";
  prezime = "";
  jmbg = "";
  helper: any;

  constructor(private cookieService: CookieService,
    private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    /*this.patientService.searchPatients("", "", "")
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )*/

    this.findPatients("", "", "");

  }

  onSubmit(form: NgForm) {
    this.findPatients(this.ime, this.prezime, this.jmbg);
    //this.router.navigate(['/searchpatients'], { queryParams: { name: this.ime, lastname: this.prezime, jmbg: this.jmbg } });
  }

  findPatients(ime: string, prezime: string, jmbg: string) {
    this.patientService.searchPatients(this.ime, this.prezime, this.jmbg)
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }
  medicalRecord(id: any) {
    this.router.navigate(['/medicalrecord/' + id]);
  }
  startExamination(id: any) {
    this.router.navigate(['/medicalExamination/' + id]);
  }
}
