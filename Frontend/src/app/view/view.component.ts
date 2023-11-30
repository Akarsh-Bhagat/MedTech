import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
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
    trigger('openClose', [
      state('open', style({
        transform: 'translateX(0)',
      })),
      state('closed', style({
        transform: 'translateX(100%)',
      })),
      transition('open <=> closed', [
        animate('0.5s ease-in-out')
      ]),
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
  showEducation: boolean = false;
  showMemberships: boolean = false;
  showRegistrations: boolean = false;
  newEducation: any = {};
  newExperience: any = {};
  newService: any = {};
  newAward: any = {};
  newMembership: any = {};
  newSpecialization: any = {};
  newRegistration: any = {};
  doctor: any={};

  constructor( private route: ActivatedRoute,private userService :UserService ,private router: Router,private elRef: ElementRef){
  
    
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
    this.showSpecializations = !this.showSpecializations;
  }

  toggleMembershipsForm() {
    this.showMemberships = !this.showMemberships;
  }

  toggleEducationForm() {
    this.showEducation = !this.showEducation;
  }

  toggleRegistrationsForm() {
    this.showRegistrations = !this.showRegistrations;
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
    this.userService.saveEducation(this.newEducation, this.doctor.id)
      .subscribe((response: any) => {
       
        window.location.reload();
        this.newEducation = {};
        this.toggleEducationForm();
      }, (error: any) => {
        console.error('Error saving education:', error);
      });
  }

  saveExperience() {
    
    this.userService.saveExperience(this.newExperience, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newExperience = {};
        this.toggleExperiencesForm();
      }, (error: any) => {
        console.error('Error saving experience:', error);
        
      });
  }

  saveMembership() {
    
    this.userService.saveMembership(this.newMembership, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newMembership = {};
        this.toggleMembershipsForm();
      }, (error: any) => {
        console.error('Error saving membership:', error);
        
      });
  }

  saveSpecialization() {
    
    this.userService.saveSpecialization(this.newSpecialization, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newSpecialization = {};
        this.toggleSpecializationsForm();
      }, (error: any) => {
        console.error('Error saving specialization:', error);
      
      });
  }

  saveAward() {
    
    this.userService.saveAward(this.newAward, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newAward = {};
        this.toggleAwardsForm();
      }, (error: any) => {
        console.error('Error saving award:', error);
        
      });
  }

  saveService() {
    
    this.userService.saveService(this.newService, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload(); 
        this.newService = {};
        this.toggleServicesForm();
      }, (error: any) => {
        console.error('Error saving service:', error);
        
      });
  }

  saveRegistration() {
    
    this.userService.saveRegistration(this.newRegistration, this.doctor.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newRegistration = {};
        this.toggleRegistrationsForm();
      }, (error: any) => {
        console.error('Error saving registration:', error);
        
      });
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