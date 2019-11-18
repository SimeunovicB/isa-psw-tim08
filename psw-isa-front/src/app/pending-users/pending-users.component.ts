import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-pending-users',
  templateUrl: './pending-users.component.html',
  styleUrls: ['./pending-users.component.css']
})
export class PendingUsersComponent implements OnInit {
  pendingUsers = [];

  constructor(private router: Router,
    private cookieService: CookieService,
    private userService: UserService) { }

  ngOnInit() {
    this.getPendingUsers();
  }

  getPendingUsers() {
    this.userService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingUsers = data;
        }
      )
  }

  prihvati(id) {
    this.userService.acceptUser(id)
      .subscribe(
        () => {
          
        }
      )
  }

  odbij(id) {
    this.userService.denyUser(id)
    .subscribe(
      () => {
        
      }
    )
  }

}
