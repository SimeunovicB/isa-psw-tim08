import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  searchRooms(name:string, number:any, date:any) {
    return this.http.post("http://localhost:9090/api/room/findRooms",{
      name:name,
      number:number,
      date:date
    })
    .pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(JSON.parse(err.text));
      })
    )
  }
}
