import { Component, OnInit } from '@angular/core';
import { AppointmentTypeService } from '../services/appointment-type/appointment-type.service';

@Component({
  selector: 'app-manage-appoint-types',
  templateUrl: './manage-appoint-types.component.html',
  styleUrls: ['./manage-appoint-types.component.css']
})
export class ManageAppointTypesComponent implements OnInit {
  nazivnovog = "";
  types = [];
  nazivi = [];

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
          for (let i = 0; i < this.types.length; i++) {
            this.nazivi[i] = this.types[i].name;
          }
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

  izmeni(name: any, id: any) {
    console.log(name,id);
    this.appTService.update(name, id)
      .subscribe(
        (data) => {
          this.getAll();
        },(error) => (console.log(error))
          
        
      );
  }
}
