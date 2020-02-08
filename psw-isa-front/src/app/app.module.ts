import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';


import { MatTableModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { UserService } from './services/user/user.service';
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
import { ChangePassword } from './change-password/change-password.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from '../Interceptors/TokenInterceptor';
import { PendingVacationsComponent } from './pending-vacations/pending-vacations.component';
import { MedicalRecordComponent } from './medical-record/medical-record.component';
import { PatientProfileComponent } from './patient-profile/patient-profile.component';
import { HomeClinicCenterAdminComponent } from './home/home-clinic-center-admin/home-clinic-center-admin.component';
import { AddClinicComponent } from './add-clinic/add-clinic.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { RegisterClinicCentreAdminComponent } from './register-clinic-centre-admin/register-clinic-centre-admin.component';
import { CodebookComponent } from './codebook/codebook.component';
import { SearchPatientsComponent } from './search-patients/search-patients.component';
import { SearchClinicsComponent } from './search-clinics/search-clinics.component';
import { MedicalExaminationComponent } from './medical-examination/medical-examination.component';
import { AppointmentReservationComponent } from './appointment-reservation/appointment-reservation.component';
import { EditClinicComponent } from './edit-clinic/edit-clinic.component';
import { ManageAppointTypesComponent } from './manage-appoint-types/manage-appoint-types.component';
import { SearchDoctorComponent } from './search-doctor/search-doctor.component';
import { ManageRoomsComponent } from './manage-rooms/manage-rooms.component';
import { ManageDoctorsComponent } from './manage-doctors/manage-doctors.component';
import { NewAppointmentComponent } from './new-appointment/new-appointment.component';
import { BusinessReportComponent } from './business-report/business-report.component';
import { SearchRoomsComponent } from './search-rooms/search-rooms.component';
import { OveravanjeReceptaComponent } from './overavanje-recepta/overavanje-recepta.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { PredefAppointmentsComponent } from './predef-appointments/predef-appointments.component';
import { VacationRequestComponent } from './vacation-request/vacation-request.component';
import { MapComponent } from './map/map.component';
import { CreatePredefAppointmentComponent } from './create-predef-appointment/create-predef-appointment.component';
import { GradesComponent } from './grades/grades.component';
import { IncomingExaminationsComponent } from './incoming-examinations/incoming-examinations.component';
import { CancelAppointmentComponent } from './cancel-appointment/cancel-appointment.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent }, //more
  { path: 'businessreport', component: BusinessReportComponent }, //
  { path: 'profile', component: PatientProfileComponent }, //more
  { path: 'login', component: LoginComponent }, //more
  { path: 'register', component: RegisterComponent }, //more
  { path: 'pending', component: PendingUsersComponent }, //more
  { path: 'patients', component: PatientsComponent }, //more
  { path: 'searchDoctors', component:SearchDoctorComponent }, //more
  { path: 'changePassword', component: ChangePassword }, //more
  { path: 'editclinic', component: EditClinicComponent }, //
  { path: 'managetypes', component: ManageAppointTypesComponent }, //
  { path: 'managedoctors', component: ManageDoctorsComponent }, //
  { path: 'managerooms', component: ManageRoomsComponent }, //
  { path: 'mapa', component: MapComponent }, //
  { path: 'pendingvacationrequests', component: PendingVacationsComponent }, //
  { path: 'vacationrequest', component: VacationRequestComponent }, //
  { path: 'medicalrecord/:id', component: MedicalRecordComponent }, //more
  { path: 'medicalExamination/:id', component: MedicalExaminationComponent }, //more
  { path: 'appointmentReservation', component: AppointmentReservationComponent }, //PRAZNA
  { path: 'searchpatients', component: SearchPatientsComponent }, //
  { path: 'searchclinics', component: SearchClinicsComponent }, //pola
  { path: 'searchrooms', component: SearchRoomsComponent }, //
  { path: 'dodajKliniku', component: AddClinicComponent },  //more
  { path: 'dodajAdministratoraKlinike', component: RegisterAdminComponent }, //more
  { path: 'dodajAdministratoraKlinickogCentra', component: RegisterClinicCentreAdminComponent }, //more
  { path: 'dodavanjeUSifarnik', component: CodebookComponent }, //more
  { path: 'overavanjeRecepta', component: OveravanjeReceptaComponent }, //ne treba
  { path: 'radniKalendar', component: CalendarComponent }, //more
  { path: 'predefAppointments', component: PredefAppointmentsComponent }, //more
  { path: 'createPredefAppointment', component: CreatePredefAppointmentComponent },//more
  { path: 'makeGrades', component: GradesComponent },
  { path: 'cancleAppointent', component: CancelAppointmentComponent},
  { path: '**', component: PageNotFoundComponent }
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
    ChangePassword,
    PendingVacationsComponent,
    MedicalRecordComponent,
    PatientProfileComponent,
    HomeClinicCenterAdminComponent,
    AddClinicComponent,
    RegisterAdminComponent,
    RegisterClinicCentreAdminComponent,
    CodebookComponent,
    SearchPatientsComponent,
    SearchClinicsComponent,
    MedicalExaminationComponent,
    AppointmentReservationComponent,
    EditClinicComponent,
    ManageAppointTypesComponent,
    SearchDoctorComponent,
    ManageRoomsComponent,
    ManageDoctorsComponent,
    NewAppointmentComponent,
    BusinessReportComponent,
    SearchRoomsComponent,
    OveravanjeReceptaComponent,
    CalendarComponent,
    VacationRequestComponent,
    MapComponent,
    PredefAppointmentsComponent,
    VacationRequestComponent,
    CreatePredefAppointmentComponent,
    GradesComponent,
    IncomingExaminationsComponent,
    CancelAppointmentComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    BrowserAnimationsModule,
    MatTableModule,
    NgbModule,
    RouterModule.forRoot(appRoutes),
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    CookieService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
