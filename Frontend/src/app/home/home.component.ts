import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { SharingService } from '../services/sharing.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  doctor: any[] = [];
  filteredDoctors: any[] = [];
  searchTerm: string = '';

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getPosts().subscribe((response) => {
      this.doctor = response;
      this.filteredDoctors = response;
      console.log(response);
    });
  }

  deleteAndReload(id: number) {
    this.userService.deleteData({}, id).subscribe(
      () => {},
      (error) => {
        window.location.reload();
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
