import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
// view.component.ts

import { Router } from '@angular/router';
import { animate, state, style, transition, trigger } from '@angular/animations';




@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css'],
  animations: [
    trigger('fadeInOut', [
      state('void', style({
        opacity: 0
      })),
      transition('void <=> *', animate(300)),
    ]),
  ],
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
  newEducation: any = {};
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

  calculateOverallExperience(): number {
    let totalExperience = 0;
  
    if (this.doctor?.experiences) {
      for (let experience of this.doctor.experiences) {
        const startYear = parseInt(experience.startYear);
        const endYear = experience.endYear ? parseInt(experience.endYear) : new Date().getFullYear();
        totalExperience += endYear - startYear;
      }
    }
  
    return totalExperience;
  }

  saveEducation() {
    this.doctor.education.push(this.newEducation);
    this.newEducation = {}; 
  }

  getIconClass(handle: string): string {
    switch (handle.toLowerCase()) {
      case 'facebook':
        return 'fab fa-facebook-square fa-2x';
      case 'twitter':
        return 'fab fa-twitter-square fa-2x';
      case 'linkedin':
        return 'fab fa-linkedin fa-2x';
      default:
        return 'fas fa-question fa-2x'; 
    }
  }

  

}