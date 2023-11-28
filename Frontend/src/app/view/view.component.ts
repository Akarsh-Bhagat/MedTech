import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
// view.component.ts

import { Router } from '@angular/router';




@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  navigateToEditProfile(): void {
    this.router.navigate(['/edit-profile']);
  }

  showServices: boolean = false;
  showExperiences: boolean = false;
  showAwards: boolean = false;
  showSpecializations: boolean = false;
  showMemberships: boolean = false;
  showEducation: boolean = false;
  doctor: any={};

  constructor( private route: ActivatedRoute,private userService :UserService ,private router: Router){
  
    
  }
  
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.userService.getDataById(id).subscribe((data: any) => {
        this.doctor=data;
        console.log(data);
      });
    });
  }

  toggleServicesForm() {
    this.showServices = !this.showServices;
  }

  toggleExperiencesForm() {
    this.showExperiences = !this.showExperiences;
  }

  toggleAwardsForm() {
    this.showAwards = !this.showAwards;
  }

  toggleSpecializationsForm() {
    this.showAwards = !this.showAwards;
  }

  toggleMembershipsForm() {
    this.showMemberships = !this.showMemberships;
  }

  toggleEducationForm() {
    this.showEducation = !this.showEducation;
  }


}