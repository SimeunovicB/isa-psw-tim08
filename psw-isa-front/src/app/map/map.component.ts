import { Component, AfterViewInit, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Geo } from '../../model/geo'
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../services/user/user.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})

export class MapComponent implements AfterViewInit, OnInit {
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
  helper: any;
  loggedUser: any;
  userMail: any;

  public constructor(private cookieService: CookieService,
    private userService: UserService,
    private router: Router) {
    this.query = "Tracy, CA";
    this.position = "37.7397,-121.4252";
  }

  coordinates = new google.maps.LatLng(this.lat, this.lng);

  mapOptions: google.maps.MapOptions = {
    center: this.coordinates,
    zoom: 8,
  };

  marker = new google.maps.Marker({
    position: this.coordinates,
    map: this.map,
  });

  ngAfterViewInit() {

  }

  ngOnInit() {
    this.helper = new JwtHelperService()
    if (this.helper.decodeToken(this.cookieService.get('token')) == null)
      this.router.navigate(['/login']);
    this.userMail = this.helper.decodeToken(this.cookieService.get('token')).sub;
    console.log(this.userMail);
    this.getUser();
  }

  getUser() {
    this.userService.getUserByMail(this.userMail)
      .subscribe(
        (data) => {
          console.log(data);
          this.loggedUser = Object.assign([], (data));
          this.lat = parseFloat(this.loggedUser.address.split('-')[0]);
          this.lng = parseFloat(this.loggedUser.address.split('-')[1]);
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

  codeAddress() {
    this.geocoder.geocode({ 'address': this.address }, function (results, status) {
      if (status == 'OK') {
        this.map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: this.map,
          position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }


}