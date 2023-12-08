import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private patientService: PatientService,
    private doctorService: UserService
  ) {}

  doctorCount: any;
  patientCount: any;

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.doctorService.getDoctorsCount().subscribe(
      count => {
        this.doctorCount = count;
      },
      error => {
        console.error('Error fetching doctor count', error);
      }
    );

    this.patientService.getPatientsCount().subscribe(
      count => {
        this.patientCount = count;
      },
      error => {
        console.error('Error fetching patient count', error);
      }
    );
  }
}
