import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AdminService } from '../services/admin/admin.service';
import { UserService } from '../services/user/user.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgForm } from '@angular/forms';
import { NgbDate, NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-business-report',
  templateUrl: './business-report.component.html',
  styleUrls: ['./business-report.component.css']
})
export class BusinessReportComponent implements OnInit {
  doctors = [];
  userMail: string;
  helper: any;
  loggedUser: any;
  grade = 0.0;
  pare: any;
  poslato = false;
  ucitao = false;

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

  constructor(private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter,
    private cookieService: CookieService,
    private adminService: AdminService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    //this.fromDate = this.calendar.getToday();
    this.fromDate = new NgbDate(2020, 1, 1);
    this.toDate = this.calendar.getToday();
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
  }

  getUser() {
    this.userService.getAdminByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.getClinicGrade();
          this.getDoctors();
        }
      )
  }

  getClinicGrade() {
    this.adminService.averageClinicGrade(this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.grade = data;
        }
      )
  }

  getDoctors() {
    this.adminService.getDoctorGrades(this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.doctors = Object.assign([], (data));
        }
      )
  }

  onSubmit(form: NgForm) {
    this.poslato = true;
    this.adminService.financialReport("" + this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day, "" + this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day, this.loggedUser.clinic_id)
      .subscribe(
        (data) => {
          console.log(data);
          this.pare = data;
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
