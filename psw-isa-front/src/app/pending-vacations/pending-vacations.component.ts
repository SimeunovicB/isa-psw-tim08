import { Component, OnInit } from '@angular/core';
import { VacationService } from '../services/vacation/vacation.service';

@Component({
  selector: 'app-pending-vacations',
  templateUrl: './pending-vacations.component.html',
  styleUrls: ['./pending-vacations.component.css']
})
export class PendingVacationsComponent implements OnInit {
  pendingRequests = [];
  loaded = false;
  idzabrisanje: any;
  brisanje = false;
  razlog: any;

  constructor(private vacationService: VacationService) { }

  ngOnInit() {
    this.getRequests();
  }

  getRequests() {
    this.vacationService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingRequests = Object.assign([], (data));
          this.loaded = true;
        }
      )
  }

  prihvati(id) {
    this.vacationService.acceptRequest(id)
      .subscribe(
        () => {
          this.getRequests();
        }
      )
  }

  odbij(id) {
    this.idzabrisanje = id;
    this.brisanje = true;
  }

  odbij2() {
    this.vacationService.denyRequest(this.idzabrisanje, this.razlog)
      .subscribe(
        () => {
          this.getRequests();
          this.brisanje = false;
        }
      )
  }

  odustani() {
    this.brisanje = false;
  }
}
