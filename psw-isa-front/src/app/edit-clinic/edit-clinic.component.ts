import { Component, OnInit } from '@angular/core';
import { ClinicService } from '../services/clinic/clinic.service';

@Component({
  selector: 'app-edit-clinic',
  templateUrl: './edit-clinic.component.html',
  styleUrls: ['./edit-clinic.component.css']
})
export class EditClinicComponent implements OnInit {
  ime = "clinic1";
  adresa = "dr klinike 1";
  opis = "clinic desc";
  clinic: any;

  constructor(private clinicService: ClinicService) { }

  ngOnInit() {
    this.getClinics();
  }

  getClinic() {
    this.clinicService.getClinic()
      .subscribe(
        (data) => {
          console.log(data);
          this.clinic = data;
        }
      )
  }

  getClinics() {
    this.clinicService.getClinics()
      .subscribe(
        (data) => {
          console.log(data);
          this.clinic = data[0];
          this.ime = this.clinic.name;
          this.adresa = this.clinic.address;
          this.opis = this.clinic.description;
        }
      )
  }

  onSubmit() {
    this.clinicService.updateClinic(this.ime, this.adresa, this.opis, 1)
      .subscribe(
        (user: any) => {
          //this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }
}
