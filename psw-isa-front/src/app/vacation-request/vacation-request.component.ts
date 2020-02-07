import { Component, OnInit } from '@angular/core';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from '../services/user/user.service';
import { NgForm } from '@angular/forms';
import { VacationService } from '../services/vacation/vacation.service';

@Component({
  selector: 'app-vacation-request',
  templateUrl: './vacation-request.component.html',
  styleUrls: ['./vacation-request.component.css']
})

export class VacationRequestComponent implements OnInit {

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

  helper: any;
  userMail: any;
  loggedUser: any;

  constructor(private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter,
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService,
    private vacationService: VacationService) { }

  ngOnInit() {
    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 3);
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getUser();
  }

  getUser() {
    this.userService.getUserByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
        }
      )
  }

  onSubmit(form: NgForm) {
    console.log("" + this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day);
    this.vacationService.createRequest("" + this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day, "" + this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day, this.loggedUser.id)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
        }
      )
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  validateInput(currentValue: NgbDate, input: string): NgbDate {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }
}
