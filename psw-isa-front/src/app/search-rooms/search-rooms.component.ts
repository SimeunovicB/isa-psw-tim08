import { Component, OnInit } from '@angular/core';
import { RoomService } from '../services/room.service';

@Component({
  selector: 'app-search-rooms',
  templateUrl: './search-rooms.component.html',
  styleUrls: ['./search-rooms.component.css']
})
export class SearchRoomsComponent implements OnInit {
  rooms = [];
  ime = "";

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  findRoom(ime: string, prezime: string, jmbg: string) {
    this.roomService.searchRooms(this.ime)
      .subscribe(
        (data) => {
          console.log(data);
          this.rooms = Object.assign([], (data));
        }
      )
  }

}
