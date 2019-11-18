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
  password2: string;
  loggedUser: any;
  id: number;

  constructor(private http: Http,
    private router: Router,
    private cookieService: CookieService,
    private modalService: NgbModal,
    private userService: UserService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(this.cookieService.get('loggedUser'));
    this.id = this.loggedUser.id;
  }

  promeni() {
    if(this.password === this.password2){
      this.userService.UpdateUserPassword(this.password, this.id)
      .subscribe(
        (user: any) => {
          this.router.navigate(['/']);
        }, (error) => alert(error.text)
      );
    } else {
      alert('Morate uneti istu lozinku dva puta!');
    }
  }



}
