import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin/admin.service';

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

  constructor(private http: HttpClient,
    private router: Router,
    private adminService: AdminService) { }

  ngOnInit() {
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
