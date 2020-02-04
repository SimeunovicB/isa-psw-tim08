import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { ClinicService } from '../services/clinic/clinic.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-edit-clinic',
  templateUrl: './edit-clinic.component.html',
  styleUrls: ['./edit-clinic.component.css']
})
export class EditClinicComponent implements AfterViewInit, OnInit {
  loggedadmin: any;
  ime = "clinic";
  adresa = "dr klinike 1";
  opis = "clinic desc";
  clinic: any;
  helper: any;
  userMail: any;

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  lat = 0;
  lng = 0;
  geocoder: any;
  address: any;
  public query: string;
  public position: string;
  public locations: Array<any>;


  constructor(private clinicService: ClinicService,
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService) { }

  coordinates = new google.maps.LatLng(this.lat, this.lng);

  mapOptions: google.maps.MapOptions = {
    center: this.coordinates,
    zoom: 10,
  };

  marker = new google.maps.Marker({
    position: this.coordinates,
    map: this.map,
  });

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);

    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    this.getAdmin();
  }

  ngAfterViewInit() { }

  getAdmin() {
    this.userService.getAdminByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedadmin = data;
          this.getClinic(this.loggedadmin.clinic_id);
        }
      )
  }

  getClinic(id: any) {
    this.clinicService.getAdminsClinics(id)
      .subscribe(
        (data) => {
          console.log(data);
          this.clinic = data;
          this.ime = this.clinic.name;
          this.adresa = this.clinic.address;
          this.opis = this.clinic.description;

          this.lat = parseFloat(this.adresa.split('-')[0]);
          this.lng = parseFloat(this.adresa.split('-')[1]);
          console.log(this.lat, this.lng);
          this.coordinates = new google.maps.LatLng(this.lat, this.lng);

          this.mapOptions.center = this.coordinates;

          this.marker.setPosition(this.coordinates);
          this.marker.setMap(this.map);

          this.mapInitializer();
        }
      )
  }

  mapInitializer() {
    this.geocoder = new google.maps.Geocoder();
    this.map = new google.maps.Map(this.gmap.nativeElement,
      this.mapOptions);
    this.marker.setMap(this.map);
  }

  getClinics() {
    this.clinicService.getClinics()
      .subscribe(
        (data) => {
          console.log(data);
          this.clinic = data[0];
          this.ime = this.clinic.name;
          this.adresa = this.clinic.address;
          this.opis = this.clinic.description;
        }
      )
  }

  onSubmit() {
    this.clinicService.updateClinic(this.ime, this.adresa, this.opis, this.clinic.id)
      .subscribe(
        (user: any) => {
          //this.router.navigate(['/profile']);
        }, (error) => alert(error.text)
      );
  }

  eventHandler() {
    this.lat = parseFloat(this.adresa.split('-')[0]);
    this.lng = parseFloat(this.adresa.split('-')[1]);
    console.log(this.lat, this.lng);
    this.coordinates = new google.maps.LatLng(this.lat, this.lng);

    this.mapOptions.center = this.coordinates;

    this.marker.setPosition(this.coordinates);
    this.marker.setMap(this.map);

    this.mapInitializer();
  }
}
