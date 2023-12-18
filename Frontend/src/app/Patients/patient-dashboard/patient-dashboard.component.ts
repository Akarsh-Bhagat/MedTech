import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent {
  role: any = '';
  currentDate!: Date;
  patient: any;
  constructor(private route: ActivatedRoute,private router: Router, private service: LoginService,private patientService: PatientService) {}
  ngOnInit() {
    this.role = sessionStorage.getItem("userRole");
    this.getCurrentDate();
    const idString = sessionStorage.getItem('userId');
    if (idString) {
      const id = parseInt(idString, 10); 
      this.patientService.getDataById(id).subscribe(
        (data: any) => {
          this.patient = data;
          console.log(data);
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    } else {
      console.error('User ID not found in sessionStorage.');
    }
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
