import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent {
  role: any = '';
  currentDate!: Date;
  constructor(private router: Router, private service: LoginService) {}
  ngOnInit() {
    this.role = localStorage.getItem("userRole");
    this.getCurrentDate();
  }
  getCurrentDate(): void {
    this.currentDate = new Date();
  }
  redirectTo() {
    if (this.role === 'PATIENT') {
      this.router.navigate(['/patient/form']);
    } else if (this.role === 'DOCTOR') {
      this.router.navigate(['/form']);
    } else {
      console.error('Invalid user role for redirection');
    }
  }
}
