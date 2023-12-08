import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SignupComponent } from './signup/signup.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
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
import { UserFormComponent } from './user-form/user-form.component';
import { EditComponent } from './edit/edit.component';
import { ViewComponent } from './view/view.component';
import { DeleteComponent } from './delete/delete.component';
import { PatientHomeComponent } from './patient-home/patient-home.component';
import { PatientEditComponent } from './patient-edit/patient-edit.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientViewComponent } from './patient-view/patient-view.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { ToastrModule } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { MatChipsModule } from '@angular/material/chips';
import { FooterComponent } from './footer/footer.component';
import { CustomInterceptor } from './services/custom.interceptor';
import { NotFoundComponent } from './not-found/not-found.component';
import { DeniedComponent } from './denied/denied.component';
import { HomepageComponent } from './doctorhome/homepage.component';
import { SuperloginComponent } from './superlogin/superlogin.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { AppointmentReqComponent } from './appointment-req/appointment-req.component';


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
    DeleteComponent,
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
 
    AppointmentReqComponent,  
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
