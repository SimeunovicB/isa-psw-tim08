import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule, Http } from '@angular/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-first-login',
  templateUrl: './first-login.component.html',
  styleUrls: ['./first-login.component.css']
})
export class FirstLoginComponent implements OnInit {
  password: string;

  constructor(private http: Http,
    private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService) { }

  ngOnInit() {

  }

  promeni() {
    this.userService.changePassword(this.password)
    .subscribe(
      (user: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    );
  }



}
