
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './Doctors/view/view.component';
import { AuthGuard } from './shared/auth.guard';
import { NotFoundComponent } from './404 Error/not-found/not-found.component';
import { DeniedComponent } from './404 Error/denied/denied.component';
import { RoleGuard } from './shared/role.guard';
import { AdminhomeComponent } from './Admin/adminhome/adminhome.component';
import { HomepageComponent } from './Doctors/doctorhome/homepage.component';
import { EditComponent } from './Doctors/edit/edit.component';
import { UserFormComponent } from './Doctors/user-form/user-form.component';
import { PatientHomeComponent } from './Patients/patient-home/patient-home.component';
import { PatientEditComponent } from './Patients/patient-edit/patient-edit.component';
import { PatientFormComponent } from './Patients/patient-form/patient-form.component';
import { PatientViewComponent } from './Patients/patient-view/patient-view.component';
import { LoginComponent } from './Login and Signup/login/login.component';
import { SignupComponent } from './Login and Signup/signup/signup.component';
import { SuperloginComponent } from './Login and Signup/superlogin/superlogin.component';
import { PatientDashboardComponent } from './Patients/patient-dashboard/patient-dashboard.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './Doctors/home/home.component';
const routes: Routes = [
  {
    path: 'admin/login',
    component: SuperloginComponent
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'signup',
    component: SignupComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'about',
    component: AboutComponent
  },
  {
    path: 'home',
    component: AdminhomeComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN'] }
  },
  {
    path: 'doctor',
    component: HomeComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','PATIENT'] }
  },
  {
    path: 'form',
    component:UserFormComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','DOCTOR'] }
  },
  {
    path: 'edit/:id',
    component:EditComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN'] }
  },
  {
    path: 'view/:id',
    component: ViewComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','PATIENT'] }
  },
  {
    path: 'patient',
    component: PatientHomeComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','DOCTOR'] }
  },
  {
    path: 'patient/form',
    component:PatientFormComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','PATIENT'] }
  },
  {
    path: 'patient/view/:id',
    component: PatientViewComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN','DOCTOR'] }
  },
  {
    path: 'patient/edit/:id',
    component:PatientEditComponent,
    canActivate:[RoleGuard],
    data: { requiredRoles: ['ADMIN'] }
  },
  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  },

  { path: 'doctor/homepage', component: HomepageComponent , canActivate:[RoleGuard],
  data: { requiredRoles: ['DOCTOR'] }},
  { path: 'patient/homepage', component: PatientDashboardComponent , canActivate:[RoleGuard],
  data: { requiredRoles: ['PATIENT'] } },
  { path: 'denied', component: DeniedComponent },
  { path: '**', component: NotFoundComponent }
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }