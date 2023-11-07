

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
const routes: Routes = [
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
    component: HomeComponent,
  },
  {
    path: 'form',
    component:UserFormComponent
  },
  {
    path: 'edit/:id',
    component:EditComponent
  },
  {
    path: 'view/:id',
    component: ViewComponent
  },
  {
    path: 'patient',
    component: PatientHomeComponent,
  },
  {
    path: 'patient/form',
    component:PatientFormComponent
  },
  {
    path: 'patient/view/:id',
    component: PatientViewComponent
  },
  {
    path: 'patient/edit/:id',
    component:PatientEditComponent
  },
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }