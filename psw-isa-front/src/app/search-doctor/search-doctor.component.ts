import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient/patient.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search-doctor',
  templateUrl: './search-doctor.component.html',
  styleUrls: ['./search-doctor.component.css']
})
export class SearchDoctorComponent implements OnInit {
  doctors = [];
  ime = "";
  prezime = "";
  ocena=""
  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService) { }

  ngOnInit() {
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
}
