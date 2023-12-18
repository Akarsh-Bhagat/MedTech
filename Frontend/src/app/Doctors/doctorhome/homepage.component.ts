import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { PatientService } from 'src/app/services/patient.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  role: any = '';
  currentDate!: Date;
  patientCount: any;
  doctor: any={};
  constructor(private route: ActivatedRoute,private router: Router, private service: LoginService,private patientService: PatientService,private userService: UserService) {}
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
    const idString = sessionStorage.getItem('userId');
    if (idString) {
      const id = parseInt(idString, 10); 
      this.userService.getDataById(id).subscribe(
        (data: any) => {
          this.doctor = data;
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












