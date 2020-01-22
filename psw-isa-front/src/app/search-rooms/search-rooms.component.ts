import { Component, OnInit } from '@angular/core';
import { RoomService } from '../services/room.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-search-rooms',
  templateUrl: './search-rooms.component.html',
  styleUrls: ['./search-rooms.component.css']
})
export class SearchRoomsComponent implements OnInit {
  rooms = [];
  model: any;
  ime = "";
  broj: any;

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    var dat = "" + this.model.year + "-" + this.model.month + "-" + this.model.day;
    console.log(dat);
    this.findRoom(this.ime, this.broj, dat);
  }

  findRoom(ime: any, broj:any, dat:any) {
    this.roomService.searchRooms(ime, broj, dat)
      .subscribe(
        (data) => {
          //console.log(data);
          this.rooms = Object.assign([], (data));
          console.log(this.rooms);
        }
      )
  }

  zakazi() {
    
  }

}
