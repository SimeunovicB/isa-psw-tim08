import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {

  baseUrl = 'http://localhost:9090' + '/api/diagnosis'

  constructor(private http : HttpClient) { }
  
  addDiagnosis(diagnosis:String) {
    return this.http.post(`${this.baseUrl}/addDiagnosis`,{
      name: diagnosis
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
