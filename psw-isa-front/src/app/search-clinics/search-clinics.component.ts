import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ClinicService } from '../services/clinic/clinic.service';
import { NgForm } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppointmentTypeService } from '../services/appointment-type/appointment-type.service';
import { Router } from '@angular/router';
import { AppointmentRequestService } from '../services/appointment-request.service';

@Component({
  selector: 'app-search-clinics',
  templateUrl: './search-clinics.component.html',
  styleUrls: ['./search-clinics.component.css']
})
export class SearchClinicsComponent implements OnInit {
  model: any;
  tip = "";
  helper: any;
  userId: any;
  clinics = [];
  modelmem: any;
  tipmem = "";
  doctors = [];
  times=[];
  currentType : any;
  appointmentTypes : any;

  constructor(private clinicService: ClinicService,
    private router: Router,
    private appointement:AppointmentRequestService,
    private cookieService: CookieService,
    private appTypeService : AppointmentTypeService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.appTypeService.getAll().subscribe(
      (data: any) => {
        this.appointmentTypes = data;
      }, (error) => alert(error.text)
    );
    
    this.findClinics("", "");
  }
  changeSelectedType(filterVal: any) {
    this.currentType = filterVal;
  }

  onSubmit(form: NgForm) {
    var dat = ""
    try {
      dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day;
    } catch (error) {
      console.log(dat);
    }
    this.modelmem = this.model;
    this.tipmem = this.tip;
    this.findClinics(dat, this.tip);
  }

  findClinics(date: string, type: string) {
    console.log(date, type);
    this.clinicService.searchClinics(date, type)
      .subscribe(
        (data) => {
          console.log(data);
          this.clinics = Object.assign([], (data));
        }
      )
  }
  zakazi(id: any) {
    console.log(id);
    var dat = "" + this.modelmem.year + "-" + this.modelmem.month + "-" + this.modelmem.day ;
    this.appointement.addRequest(id,this.userId, dat, this.tip)
    .subscribe(
      (data) => {
        this.doctors=[];
      }
    )
  }
  doktori(clinicname:string) {
    console.log(clinicname);
    var dat = "" + this.modelmem.year + "-" + this.modelmem.month + "-" + this.modelmem.day ;
    this.clinicService.getDoctors(clinicname, dat, this.tip)
    .subscribe(
      (data) => {
        console.log(data);
        this.doctors = Object.assign([], (data));
        
      }
    )
  }
}
