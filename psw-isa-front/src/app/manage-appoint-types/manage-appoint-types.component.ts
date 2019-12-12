import { Component, OnInit } from '@angular/core';
import { AppointmentTypeService } from '../services/appointment-type.service';

@Component({
  selector: 'app-manage-appoint-types',
  templateUrl: './manage-appoint-types.component.html',
  styleUrls: ['./manage-appoint-types.component.css']
})
export class ManageAppointTypesComponent implements OnInit {
  nazivnovog = "";
  types = [];

  constructor(private appTService: AppointmentTypeService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.appTService.getAll()
      .subscribe(
        (data) => {
          console.log(data);
          this.types = Object.assign([], (data));
        }
      )
  }

  onSubmit() {
    this.appTService.addType(this.nazivnovog)
      .subscribe(
        (user: any) => {
          this.getAll();
          //this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }
}
