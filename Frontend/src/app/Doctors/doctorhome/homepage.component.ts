import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { PatientService } from 'src/app/services/patient.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  role: any = '';
  currentDate!: Date;
  patientCount: any;
  constructor(private router: Router, private service: LoginService,private patientService: PatientService) {}
  ngOnInit() {
    this.role = sessionStorage.getItem("userRole");
    this.getCurrentDate();
    this.patientService.getPatientsCount().subscribe(
      count => {
        this.patientCount = count;
      },
      error => {
        console.error('Error fetching patient count', error);
      }
    );
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












