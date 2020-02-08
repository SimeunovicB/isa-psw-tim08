import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {environment} from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  searchRooms(name: string, number: any, date: any) {
    return this.http.post(`${environment.baseUrl}/api/room/findRooms`, {
      name: name,
      number: number,
      date: date
    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }

  findAll() {
    return this.http.get(`${environment.baseUrl}/api/room/findAll`)
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }

  getAdminRooms(id: any) {
    return this.http.post(`${environment.baseUrl}/api/room/getAdminRooms`, {
      id: id
    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }

  update(name: any, number: any, id: any) {
    return this.http.post(`${environment.baseUrl}/api/room/update`, {
      name:name,
      number:number,
      id:id
    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }

  create(name: any, number: any, id: any) {
    return this.http.post(`${environment.baseUrl}/api/room/create`, {
      name:name,
      number:number,
      id:id
    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }
}
