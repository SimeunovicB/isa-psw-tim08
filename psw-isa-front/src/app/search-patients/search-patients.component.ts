import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient/patient.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

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

  constructor(private router: Router,
    private activeRoute: ActivatedRoute,
    private patientService: PatientService) { }

  ngOnInit() {
    /*this.activeRoute.queryParams.subscribe(queryParams => {
      if (this.router.url.includes('searchp')) {
        this.activeRoute.queryParams.pipe(
          filter(params => params.name)
          .subscribe(params => {
            console.log(params); // {order: "popular"}

            this.order = params.order;
            console.log(this.order); // popular
          }));
      }
    });*/
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
}
