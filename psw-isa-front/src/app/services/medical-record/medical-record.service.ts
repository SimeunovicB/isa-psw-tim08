import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {environment} from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class MedicalRecordService {

  constructor(private http : HttpClient) { }

  baseUrl = `${environment.baseUrl}` + '/api/medicalRecord'

  updateMedicalRecord(
    id : number,
    height : number,
    weight : number,
    bloodType : string,
    diopter : number,
    alergies : string) {
    return this.http.post(`${this.baseUrl}/update`,{
      id : id,
      height : height,
      weight : weight,
      bloodType : bloodType,
      diopter : diopter,
      alergies : alergies
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
