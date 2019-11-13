import { Component, OnInit } from '@angular/core';
import { NgForm, FormsModule, EmailValidator } from '@angular/forms';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { jsonpFactory } from '@angular/http/src/http_module';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  firstName:string;
  lastName : string;
  address :string;
  password:string;
  l :any;
  

  constructor(private http: Http,
    private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    this.l=JSON.parse(this.cookieService.get('loggedUser'));
    console.log(this.l);
    this.firstName=this.l.firstName;
    this.lastName=this.l.lastName;
    this.address=this.l.address;
  }
  onSubmit(form: NgForm) {
   
    this.userService.UpdateUser(this.firstName,this.lastName,this.address,this.l.id)
    .subscribe(
      (user: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    );
  }
  onSubmitPassword(form: NgForm) {
   
    this.userService.UpdateUserPassword(this.password,this.l.id)
    .subscribe(
      (user: any) => {
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    );
  }

}