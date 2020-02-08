import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {environment} from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class OveravanjeReceptaService {

  constructor(private http: HttpClient) { }

  getAllPendingRecipes() {
    return this.http.get(`${environment.baseUrl}/api/recipe/getAllPending`)
    .pipe(
      map((response: any) => {
        const data = response;
        console.log("preuzimam")
        return data;
      }),
      catchError((err: any) => {
        return throwError(err);
      })
    )
  }
  approveRecipe(recipeId : any,
                nurseId  : any){
      return this.http.post(`${environment.baseUrl}/api/recipe/updateRecipe`,{
        nurse : nurseId,
        recipeId : recipeId
      }).pipe(
        map((response: any) => {
          const data = response;
          console.log("preuzimam")
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }
}
