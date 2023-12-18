import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './Doctors/home/home.component';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { UserFormComponent } from './Doctors/user-form/user-form.component';
import { EditComponent } from './Doctors/edit/edit.component';
import { ViewComponent } from './Doctors/view/view.component';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { ToastrModule } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { MatChipsModule } from '@angular/material/chips';
import { FooterComponent } from './Nav and Sidebars/footer/footer.component';
import { CustomInterceptor } from './services/custom.interceptor';
import { NotFoundComponent } from './404 Error/not-found/not-found.component';
import { DeniedComponent } from './404 Error/denied/denied.component';
import { HomepageComponent } from './Doctors/doctorhome/homepage.component';
import { AdminhomeComponent } from './Admin/adminhome/adminhome.component';
import { SidebarComponent } from './Nav and Sidebars/sidebar/sidebar.component';
import { PatientHomeComponent } from './Patients/patient-home/patient-home.component';
import { PatientEditComponent } from './Patients/patient-edit/patient-edit.component';
import { PatientFormComponent } from './Patients/patient-form/patient-form.component';
import { PatientViewComponent } from './Patients/patient-view/patient-view.component';
import { LoginComponent } from './Login and Signup/login/login.component';
import { SignupComponent } from './Login and Signup/signup/signup.component';
import { SuperloginComponent } from './Login and Signup/superlogin/superlogin.component';
import { NavbarComponent } from './Nav and Sidebars/navbar/navbar.component';
import { PatientDashboardComponent } from './Patients/patient-dashboard/patient-dashboard.component';
import { AboutComponent } from './about/about.component';
import { PatientAppointmentComponent } from './Patients/patient-appointment/patient-appointment.component';
import { SearchDoctorsComponent } from './Appointments/search-doctors/search-doctors.component';
import { AvailableDoctorsComponent } from './Appointments/available-doctors/available-doctors.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    NavbarComponent,
    HomeComponent,
    UserFormComponent,
    EditComponent,
    ViewComponent,
    PatientHomeComponent,
    PatientEditComponent,
    PatientFormComponent,
    PatientViewComponent,
    FooterComponent,
    NotFoundComponent,
    DeniedComponent,
    HomepageComponent,
    SuperloginComponent,
    AdminhomeComponent,
    SidebarComponent,
      PatientDashboardComponent,
      AboutComponent,
      PatientAppointmentComponent,
      SearchDoctorsComponent,
      AvailableDoctorsComponent,  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CardModule,
    InputTextModule,
    TypeaheadModule.forRoot(),
    ToastrModule.forRoot(),
    ReactiveFormsModule,
    ButtonModule,
    MenubarModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatChipsModule,
    MatButtonModule,
    MatDialogModule,
    MatDialogModule,
    RouterModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatAutocompleteModule,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:CustomInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
