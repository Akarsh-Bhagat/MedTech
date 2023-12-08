
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
const routes: Routes = [
  {
    path: 'admin/login',
    component: SuperloginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'home',
    component: AdminhomeComponent,
    canActivate:[RoleGuard]
  },
  {
    path: 'form',
    component:UserFormComponent
  },
  {
    path: 'edit/:id',
    component:EditComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'view/:id',
    component: ViewComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'patient',
    component: PatientHomeComponent,
    canActivate:[RoleGuard]
  },
  {
    path: 'patient/form',
    component:PatientFormComponent
  },
  {
    path: 'patient/view/:id',
    component: PatientViewComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'patient/edit/:id',
    component:PatientEditComponent,
    canActivate:[AuthGuard]
  },
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  },

  { path: 'doctor/homepage', component: HomepageComponent,canActivate:[AuthGuard] },
  { path: 'patient/homepage', component: PatientDashboardComponent,canActivate:[AuthGuard] },
  { path: 'denied', component: DeniedComponent },
  { path: '**', component: NotFoundComponent }
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }