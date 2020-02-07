import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ClinicService } from '../services/clinic/clinic.service';
import { NgForm } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-search-clinics',
  templateUrl: './search-clinics.component.html',
  styleUrls: ['./search-clinics.component.css']
})
export class SearchClinicsComponent implements OnInit {
  model: any;
  tip = "";
  helper: any;
  userId : any;
  clinics = [];
  modelmem:any;
  tipmem = "";
  doctors = [];
  times=[];
  
  

  constructor(private clinicService: ClinicService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    
    this.findClinics("", "");
  }

  onSubmit(form: NgForm) {
    var dat =""
    try {
      dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day ;      
    } catch (error) {
      console.log(dat);      
    }
    this.modelmem = this.model;
    this.tipmem = this.tip;
    this.findClinics(dat, this.tip);
  }

  findClinics(date:string, type:string) {
    console.log(date,type);
    this.clinicService.searchClinics(date, type)
    .subscribe(
      (data) => {
        console.log(data);
        this.clinics = Object.assign([], (data));
      }
    )
  }
  zakazi(id:any) {
    console.log(id);
    var dat = "" + this.modelmem.year + "-" + this.modelmem.month + "-" + this.modelmem.day ;
    this.clinicService.makeApp(id, dat, this.tipmem,this.userId)
    .subscribe(
      (data) => {
        this.doctors=[];
      }
    )
  }
  doktori(clinicname:string) {
    var dat = "" + this.modelmem.year + "-" + this.modelmem.month + "-" + this.modelmem.day ;
    this.clinicService.getDoctors(clinicname, dat, this.tipmem)
    .subscribe(
      (data) => {
        console.log(data);
        this.doctors = Object.assign([], (data));
        
      }
    )
  }
}
