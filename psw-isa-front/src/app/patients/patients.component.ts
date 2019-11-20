import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients = [];

  constructor(private patientService: PatientService) { }

  ngOnInit() {
    this.ucitajPacijente();
  }

  ucitajPacijente() {
    this.patientService.getAllPatients()
      .subscribe(
        (data) => {
          console.log(data);
          this.patients = Object.assign([], (data));
        }
      )
  }
}
