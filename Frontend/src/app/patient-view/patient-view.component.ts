import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.css'],
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
export class PatientViewComponent {

  patient: any
  showReports: boolean = false;
  showHideText: string = "Show Reports";
  showClinics: boolean = false;
  newClinic:any={};

  constructor( private route: ActivatedRoute,private patientService: PatientService){
  
    
  }
  
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.patientService.getDataById(id).subscribe((data: any) => {
        this.patient=data;
        console.log(data);
      });
    });
  }
  toggleClinicsForm() {
    this.showClinics = !this.showClinics;
  }


  toggleReports(clinicId: number) {
    const clinic = this.patient.clinics.find((c: any) => c.id === clinicId);
    if (clinic) {
      clinic.showReports = !clinic.showReports;
      this.showHideText = clinic.showReports ? "Hide Reports" : "Show Reports";
    }
  }


  saveClinic() {
    this.patientService.saveClinic(this.newClinic, this.patient.id)
      .subscribe((response: any) => {
        window.location.reload();
        this.newClinic = {};
        this.toggleClinicsForm();
      }, (error: any) => {
        console.error('Error saving education:', error);
      });
  }

}
