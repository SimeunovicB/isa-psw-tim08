import { Component, OnInit } from '@angular/core';
import { ClinicService } from '../services/clinic/clinic.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-add-clinic',
  templateUrl: './add-clinic.component.html',
  styleUrls: ['./add-clinic.component.css']
})
export class AddClinicComponent implements OnInit {
  clinicName: string;
  clinicAddress: string;
  clinicDescription: string;
  helper: any;

  constructor(
    private cookieService: CookieService,
    private clinicService: ClinicService,
    private router: Router
  ) { }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
  }

  addClinic() {
    this.clinicService.addClinic(this.clinicName, this.clinicAddress, this.clinicDescription).subscribe(
      (clinic: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    )

  }

}
