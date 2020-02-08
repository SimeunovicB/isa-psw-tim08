import { Component, OnInit } from '@angular/core';
import { AppointmentTypeService } from '../services/appointment-type/appointment-type.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-appoint-types',
  templateUrl: './manage-appoint-types.component.html',
  styleUrls: ['./manage-appoint-types.component.css']
})
export class ManageAppointTypesComponent implements OnInit {
  nazivnovog = "";
  types = [];
  nazivi = [];
  helper: any;

  constructor(private cookieService: CookieService,
    private router: Router,
    private appTService: AppointmentTypeService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
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
    console.log(name, id);
    this.appTService.update(name, id)
      .subscribe(
        (data) => {
          this.getAll();
        }, (error) => (console.log(error))


      );
  }

  obrisi(id: any) {
    console.log(id);
    this.appTService.delete(id)
      .subscribe(
        (data) => {
          if (data === 0) {
            alert('Ne mozete obrisati tip jer postoji neodrzan pregled sa tim tipom!')
          } else {
            this.getAll();
          }
        }, (error) => (console.log(error))
      );
  }
}
