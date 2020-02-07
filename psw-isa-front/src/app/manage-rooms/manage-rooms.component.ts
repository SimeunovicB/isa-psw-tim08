import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';
import { AdminService } from '../services/admin/admin.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RoomService } from '../services/room.service';

@Component({
  selector: 'app-manage-rooms',
  templateUrl: './manage-rooms.component.html',
  styleUrls: ['./manage-rooms.component.css']
})
export class ManageRoomsComponent implements OnInit {
  nazivnove: any;
  brnove: any;
  rooms = [];
  nazivi = [];
  userMail: string;
  helper: any;
  loggedUser: any;

  constructor(private cookieService: CookieService,
    private adminService: AdminService,
    private userService: UserService,
    private roomService: RoomService,
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
          this.getRooms();
        }
      )
  }

  getRooms() {
    this.roomService.getAdminRooms(this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.rooms = Object.assign([], (data));
          for (let i = 0; i < this.rooms.length; i++) {
            this.nazivi[i] = "" + this.rooms[i].name + " " + this.rooms[i].number;
          }
          console.log(this.nazivi);
        }
      )
  }

  izmeni(id: any, naziv: any) {
    var parts = naziv.split(' ');
    console.log(parts);
    var name = "";
    for (let i = 0; i < parts.length - 1; i++) {
      name = name + "" + parts[i] + " ";
    }
    name = name.substring(0, name.length - 1);
    var number = parseInt(parts[parts.length - 1]);

    this.roomService.update(name, number, id)
      .subscribe(
        (data) => {
          this.getRooms();
        }
      )
  }

  onSubmit() {
    this.roomService.create(this.nazivnove, this.brnove, this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          this.getRooms();
        }
      )
  }

  obrisi(id: any) {
    console.log(id);
    this.adminService.deleteRoom(id)
      .subscribe(
        (data) => {
          if (data === 0) {
            alert('Ne moze se obrisati sala jer ima zakazanih pregleda u njoj!');
          }
          this.getRooms();
        }
      )
  }
}
