import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharingService } from '../../services/sharing.service';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-patient-home',
  templateUrl: './patient-home.component.html',
  styleUrls: ['./patient-home.component.css']
})
export class PatientHomeComponent implements OnInit {
  patient: any[] = [];
  filteredPatients: any[] = [];
  searchTerm: string = '';
  @Input() showSidebar: boolean = true;

  constructor(private router: Router, private patientService: PatientService) {}

  ngOnInit(): void {
    this.patientService.getPosts().subscribe((response) => {
      this.patient = response;
      this.filteredPatients = response;
      console.log(response);
    });
  }

  deleteAndReload(id: number) {
    this.patientService.deleteData({}, id).subscribe(
      () => {
        window.location.reload();
      },
      (error) => {
      }
    );
  }

  

  filterPatients() {
    const formattedSearchTerm = this.searchTerm.trim().toLowerCase();
    if (formattedSearchTerm === '') {
      this.filteredPatients = this.patient;
    } else {
      this.filteredPatients = this.patient.filter((pat) => {
        const fullName = (pat.firstName + ' ' + pat.lastName).toLowerCase().replace(/\s+/g, ' ');
        const trimmedFullName = fullName.trim();
        return (
          pat.firstName.toLowerCase().includes(formattedSearchTerm) ||
          pat.lastName.toLowerCase().includes(formattedSearchTerm) ||
          trimmedFullName.includes(formattedSearchTerm) ||
          pat.email.toLowerCase().includes(formattedSearchTerm) ||
          pat.address.toLowerCase().includes(formattedSearchTerm)
        );
      });
    }
  }
  
}
