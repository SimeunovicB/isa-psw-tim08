import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-doctors',
  templateUrl: './manage-doctors.component.html',
  styleUrls: ['./manage-doctors.component.css']
})
export class ManageDoctorsComponent implements OnInit {
  doctors = [];

  constructor() { }

  ngOnInit() {
  }

}
