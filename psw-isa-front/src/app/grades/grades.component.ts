import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ClinicService } from '../services/clinic/clinic.service';
import { NgForm } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';


@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css']
})
export class GradesComponent implements OnInit {
  helper: any;
  userId: any;
  doctors = [];
  clinics = [];
  selectedType: any;
  selectedClinic: any;

  constructor(private clinicService: ClinicService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    this.userId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.findClinics();
    this.findDoctors();
  }
  onItemChange(value){
    console.log(" Value is : ", value );
    this.selectedClinic=value;
 }
  onDoctorChange(filterVal) {
    this.selectedType = filterVal;
    console.log(this.selectedType);
  }
  
  doktori(doctorId:any,value:any) {
    console.log(value,doctorId);
    this.clinicService.makeGradeDoctor(doctorId, this.selectedType, this.userId)
      .subscribe(
        (data) => {
          console.log(data);
        }
      )
  }
  
  klinike(clinicname:String,value:any) {
    console.log(this.selectedClinic,clinicname);
    this.clinicService.makeGradeClinic(clinicname, this.selectedClinic, this.userId)
      .subscribe(
        (data) => {
          console.log(data);
        }
      )
  }

  findClinics() {
    console.log(this.userId);
    this.clinicService.clinicGrade(this.userId)
      .subscribe(
        (data) => {
          console.log(data);
          this.clinics = Object.assign([], (data));
        }
      )
  }
  findDoctors() {
    console.log(this.userId);
    this.clinicService.doctorGrade(this.userId)
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }

}
