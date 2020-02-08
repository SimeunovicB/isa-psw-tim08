import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MedicineService } from '../services/medicine/medicine.service';
import { DiagnosisService } from '../services/diagnosis/diagnosis.service';
import { MedicalExaminationService } from '../services/medical-examination/medical-examination.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { AppointmentTypeService } from '../services/appointment-type/appointment-type.service';
import { AppointmentRequestService } from '../services/appointment-request.service';


@Component({
  selector: 'app-medical-examination',
  templateUrl: './medical-examination.component.html',
  styleUrls: ['./medical-examination.component.css']
})
export class MedicalExaminationComponent implements OnInit {

  patientId: any;
  medicines: object;
  diagnosises: object;
  medicine: any;
  diagnosis: any;
  helper: any;
  doctorId: any;
  description: any;
  appointmentTypes = [];
  model: any;
  selectedType: any;

  constructor(private router: Router,
    private medicineService: MedicineService,
    private diagnosisService: DiagnosisService,
    private medicalExaminationService: MedicalExaminationService,
    private appRequestService: AppointmentRequestService,
    private appTypeService: AppointmentTypeService,
    private activatedRoute: ActivatedRoute,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.patientId = this.activatedRoute.snapshot.url[1].path;
    this.helper = new JwtHelperService();
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.doctorId = this.helper.decodeToken(this.cookieService.get('token')).id;
    this.init();
    this.getTypes();
  }

  getTypes() {
    this.appTypeService.getAll().subscribe(
      (data: any) => {
        this.appointmentTypes = data;
      }, (error) => alert(error.text)
    );
  }

  init() {
    this.medicineService.getAll().subscribe(
      (data: any) => {
        this.medicines = data;
      }, (error) => alert(error.text)
    );
    this.diagnosisService.getAll().subscribe(
      (data: any) => {
        this.diagnosises = data;
      }, (error) => alert(error.text)
    );
  }
  initialized() {
    if (this.medicines != undefined && this.diagnosises != undefined) {
      return true;
    }
    else
      return false;
  }
  changeSelectedMedicine(filterVal: any) {
    this.medicine = filterVal;
  }

  changeSelectedDiagnosis(filterVal: any) {
    this.diagnosis = filterVal;
  }

  changeSelectedAppType(filterVal: any) {
    this.selectedType = filterVal;
    console.log(this.selectedType);
  }

  promeni() {
    this.medicalExaminationService.newExamination(this.patientId, this.doctorId, this.description, this.medicine, this.diagnosis).subscribe(
      (data: any) => {
        console.log(data)
      }, (error) => {
        alert(error.text);
      }
    )
  }

  zakazi() {
    var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day;
    this.appRequestService.addRequest(this.doctorId, this.patientId, dat, this.selectedType).subscribe(
      (data: any) => {
        console.log(data)
      }, (error) => {
        alert(error.text);
      }
    )
  }

}
