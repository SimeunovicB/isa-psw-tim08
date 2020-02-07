import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { PatientService } from 'src/app/services/patient/patient.service';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from 'src/app/services/user/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-doctor',
  templateUrl: './home-doctor.component.html',
  styleUrls: ['./home-doctor.component.css']
})
export class HomeDoctorComponent implements OnInit {
  //displayedColumns: string[] = ['ime', 'prezime', 'jmbg'];
  patients = [];
  doctor: any;
  userMail: string;
  helper: any;
  loggedUser: any;
  //dataSource = new MatTableDataSource<Pacijent>(this.patients);

  //@ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private cookieService: CookieService,
    private patientService: PatientService,
    private userService: UserService,
    private router: Router) { }




  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
    //this.ucitajPacijente();
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

  getUser() {
    this.userService.getDocByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
        }
      )
  }

  prikaziPacijente() {

  }

}