import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  baseUrl = 'http://localhost:9090' + '/api/medicine'

  constructor(private http: HttpClient) { }

  addMedicine(medicine :String) {
    return this.http.post(`${this.baseUrl}/addMedicine`,{
      name: medicine
    }).pipe(
      map((response: any) => {
        const data = response;
        return data;
      }),
      catchError((err: any) => {
        return throwError(err.text);
      })
    )
  }
}
