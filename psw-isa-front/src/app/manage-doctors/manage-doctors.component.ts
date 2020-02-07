import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AdminService } from '../services/admin/admin.service';

@Component({
  selector: 'app-manage-doctors',
  templateUrl: './manage-doctors.component.html',
  styleUrls: ['./manage-doctors.component.css']
})
export class ManageDoctorsComponent implements OnInit {
  doctors = [];
  userMail: string;
  helper: any;
  loggedUser: any;
  imenovog: any;
  prznovog: any;
  adresanovog: any;
  sifranovog: any;
  mailnovog: any;

  constructor(private cookieService: CookieService,
    private adminService: AdminService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
  }

  getUser() {
    this.userService.getAdminByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.getDoctors();
        }
      )
  }

  getDoctors() {
    this.adminService.getAdminsDoctors(this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }

  onSubmit() {
    this.adminService.createDoctor(this.imenovog, this.prznovog, this.adresanovog, this.mailnovog, this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.getDoctors();
        }
      )
  }

  obrisi(id: any) {
    this.adminService.deleteDoctor(id)
      .subscribe(
        (data) => {
          console.log(data);
          if (data === 0) {
            alert('Nije moguce obrisati doktora jer ima zakazane preglede!');
          }
          this.getDoctors();
        }
      )
  }
}
