import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-medical-record',
  templateUrl: './medical-record.component.html',
  styleUrls: ['./medical-record.component.css']
})
export class MedicalRecordComponent implements OnInit {
  ime="ime";
  prezime="prezime";
  jmbg="000000000000";
  visina=180;
  tezina=75;
  alergije="Polen";
  disabledp=true;

  constructor() { }

  ngOnInit() {
  }

  izmeni() {
    this.disabledp=false;
  }

  sacuvaj(){
    this.disabledp=true;
  }

}
