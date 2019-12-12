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
  opis = "very clininac clinic hoho";
  clinic: any;

  constructor(private clinicService: ClinicService) { }

  ngOnInit() {

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

  onSubmit() {
    this.clinicService.updateClinic(this.ime, this.adresa, this.opis, 1)
      .subscribe(
        (user: any) => {
          //this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }
}
