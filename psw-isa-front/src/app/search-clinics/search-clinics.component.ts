import { Component, OnInit } from '@angular/core';
import { ClinicService } from '../services/clinic/clinic.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-search-clinics',
  templateUrl: './search-clinics.component.html',
  styleUrls: ['./search-clinics.component.css']
})
export class SearchClinicsComponent implements OnInit {
  model: any;
  tip = "";
  clinics = [];
  modelmem:any;
  tipmem = "";
  doctors = [];

  constructor(private clinicService: ClinicService) { }

  ngOnInit() {
    this.findClinics("", "");
  }

  onSubmit(form: NgForm) {
    var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day + " 00:00:00";
    console.log(dat);
    this.modelmem = this.model;
    this.tipmem = this.tip;
    this.findClinics(dat, this.tip);
  }

  findClinics(date:string, type:string) {
    this.clinicService.searchClinics(date, type)
    .subscribe(
      (data) => {
        console.log(data);
        this.clinics = Object.assign([], (data));
      }
    )
  }

  doktori(clinicname:string) {
    var dat = "" + this.modelmem.year + "-" + this.modelmem.month + "-" + this.modelmem.day + " 00:00:00";
    this.clinicService.getDoctors(clinicname, dat, this.tipmem)
    .subscribe(
      (data) => {
        console.log(data);
        this.doctors = Object.assign([], (data));
      }
    )
  }
}
