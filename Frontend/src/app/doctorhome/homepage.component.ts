import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
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












