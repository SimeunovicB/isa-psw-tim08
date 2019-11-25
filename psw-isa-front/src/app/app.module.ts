import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatTableModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { UserService } from './services/user.service';
import { HttpModule } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { PendingUsersComponent } from './pending-users/pending-users.component';
import { HomeComponent } from './home/home.component';
import { HomeNurseComponent } from './home/home-nurse/home-nurse.component';
import { HeaderComponent } from './header/header.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomePatientComponent } from './home/home-patient/home-patient.component';
import { HomeDoctorComponent } from './home/home-doctor/home-doctor.component';
import { HomeAdminComponent } from './home/home-admin/home-admin.component';
import { PatientsComponent } from './patients/patients.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FirstLoginComponent } from './first-login/first-login.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pending', component: PendingUsersComponent },
  { path: 'patients', component: PatientsComponent },
  { path: 'firstLogin',component : FirstLoginComponent},
  { path: '**', component: PageNotFoundComponent },
  
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    LoginComponent,
    RegisterComponent,
    PendingUsersComponent,
    HomeComponent,
    HomeNurseComponent,
    HomeDoctorComponent,
    HeaderComponent,
    WelcomeComponent,
    HomePatientComponent,
    HomeAdminComponent,
    PatientsComponent,
    PageNotFoundComponent,
    FirstLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
