import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  loggedUser: any;

  constructor(    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(this.cookieService.get('loggedUser'));
  }

}
