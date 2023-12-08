
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { UserFormComponent } from './user-form/user-form.component';
import { EditComponent } from './edit/edit.component';
import { ViewComponent } from './view/view.component';
import { PatientHomeComponent } from './patient-home/patient-home.component';
import { PatientEditComponent } from './patient-edit/patient-edit.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientViewComponent } from './patient-view/patient-view.component';
import { AuthGuard } from './shared/auth.guard';
import { NotFoundComponent } from './not-found/not-found.component';
import { DeniedComponent } from './denied/denied.component';
import { RoleGuard } from './shared/role.guard';
import { HomepageComponent } from './doctorhome/homepage.component';
import { SuperloginComponent } from './superlogin/superlogin.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
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

  { path: 'homepage', component: HomepageComponent,canActivate:[AuthGuard] },
  { path: 'denied', component: DeniedComponent },
  { path: '**', component: NotFoundComponent }
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }