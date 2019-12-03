import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt'
import { MedicineService } from '../services/medicine/medicine.service';
import { DiagnosisService } from '../services/diagnosis/diagnosis.service';

@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.css']
})
export class CodebookComponent implements OnInit {
  medicine: string;
  diagnosis: string;

  constructor(private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private medicneService: MedicineService,
    private diagnosisService: DiagnosisService) { }

  ngOnInit() {
  }
  addMedicine() {
    if (this.medicine.trim() != '') {
      this.medicneService.addMedicine(this.medicine).subscribe(
        (medicine: any) => {
          alert('Lek dodat');
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti naziv leka');
    }
  }
  addDiagnosis() {
    if (this.diagnosis.trim() != '') {
      this.diagnosisService.addDiagnosis(this.diagnosis).subscribe(
        (diagnosis: any) => {
          alert('Dijagnoza dodata');
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti naziv dijagnoze');
    }
  }

}


