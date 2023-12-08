import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  doctor: any[] = [];
  filteredDoctors: any[] = [];
  searchTerm: string = '';

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getPosts().subscribe((response: any) => {
      this.doctor = response;
      this.filteredDoctors = response;
    });
  }

  deleteAndReload(id: number) {
    this.userService.deleteData({}, id).subscribe(
      () => {
        console.log('Doctor deleted from the database');
        window.location.reload();
      },
      (error) => {
        console.log('Error while deleting doctor from the database');
      }
    );
  }

  

  filterDoctors() {
    const formattedSearchTerm = this.searchTerm.trim().toLowerCase();
    if (formattedSearchTerm === '') {
      this.filteredDoctors = this.doctor;
    } else {
      this.filteredDoctors = this.doctor.filter((doc) => {
        const fullName = (doc.firstName + ' ' + doc.lastName).toLowerCase().replace(/\s+/g, ' ');
        const trimmedFullName = fullName.trim();
        return (
          doc.firstName.toLowerCase().includes(formattedSearchTerm) ||
          doc.lastName.toLowerCase().includes(formattedSearchTerm) ||
          trimmedFullName.includes(formattedSearchTerm) ||
          doc.email.toLowerCase().includes(formattedSearchTerm) ||
          doc.address.toLowerCase().includes(formattedSearchTerm)
        );
      });
    }
  }
  
}