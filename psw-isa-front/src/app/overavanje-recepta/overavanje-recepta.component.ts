import { Component, OnInit } from '@angular/core';
import { OveravanjeReceptaService } from '../services/overavanjeRecepta/overavanje-recepta.service';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-overavanje-recepta',
  templateUrl: './overavanje-recepta.component.html',
  styleUrls: ['./overavanje-recepta.component.css']
})
export class OveravanjeReceptaComponent implements OnInit {

  constructor(private recipeService: OveravanjeReceptaService,
    private cookieService: CookieService) { }
  recipes: any;
  nurseId: any;
  helper: any;

  ngOnInit() {
    this.getAllPending();
    this.helper = new JwtHelperService();
    this.nurseId = this.helper.decodeToken(this.cookieService.get('token')).id;
  }
  getAllPending() {
    this.recipeService.getAllPendingRecipes().subscribe(
      (data) => {
        this.recipes = data;
        console.log(this.recipes)
      }
    )
  }
  approveRecipe(id: any) {
    this.recipeService.approveRecipe(id, this.nurseId).subscribe(
      (data) => {
        console.log('overio hehe');
        this.getAllPending();
      }
    )
  }

}
