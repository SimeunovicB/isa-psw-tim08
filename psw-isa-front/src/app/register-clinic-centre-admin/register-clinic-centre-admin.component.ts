import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin/admin.service';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-register-clinic-centre-admin',
  templateUrl: './register-clinic-centre-admin.component.html',
  styleUrls: ['./register-clinic-centre-admin.component.css']
})
export class RegisterClinicCentreAdminComponent implements OnInit {

  firstName: string;
  lastName: string;
  username: string;
  password: string;
  address: string;
  helper: any;

  constructor(private http: HttpClient,
    private cookieService: CookieService,
    private router: Router,
    private adminService: AdminService) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
  }
  addAdmin() {
    this.adminService.registerClinicCentreAdmin(this.firstName, this.lastName, this.address, this.username)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/']);
        }, (error) => alert(error.text)
      )
  }

}
