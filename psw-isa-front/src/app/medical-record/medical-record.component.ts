import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { MedicalRecordService } from '../services/medical-record/medical-record.service';

@Component({
  selector: 'app-medical-record',
  templateUrl: './medical-record.component.html',
  styleUrls: ['./medical-record.component.css']
})
export class MedicalRecordComponent implements OnInit {
  id : any;
  medicalRecordId : any;
  firstName : string;
  lastName : string;
  jmbg : string;
  height : number;
  weight : number;
  alergies : string;
  bloodType : string;
  diopter : number;
  disabledp=true;

  constructor(private activatedRoute: ActivatedRoute,
    private userService : UserService,
    private medicalRecordService : MedicalRecordService) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.url[1].path;
    this.getUser();
  }
  getUser() {
    this.userService.getMedicalRecordInfo(this.id)
      .subscribe(
        (data) => {
          console.log(data);
         this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.jmbg = data.jmbg;
          this.height = data.height;
          this.weight = data.weight;
          this.bloodType = data.bloodType;
          this.diopter = data.diopter;
          this.alergies = data.alergies;
          this.medicalRecordId = data.id;
        }
      )
  }

  izmeni() {
    this.disabledp=false;
  }

  sacuvaj(){
    this.medicalRecordService.updateMedicalRecord(this.id,this.height,this.weight,this.bloodType,this.diopter,this.alergies).subscribe(
      (data) => {
        alert(`Zdrastveni karton je uspesno azuriran!`);
      }
    )
  }

}
