import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../services/patient.service';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.css']
})
export class PatientViewComponent {
  patient: any
  showReports: boolean = false;
  showHideText: string = "Show Reports";

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
  toggleReports(clinicId: number) {
    const clinic = this.patient.clinics.find((c: any) => c.id === clinicId);
    if (clinic) {
      clinic.showReports = !clinic.showReports;
      this.showHideText = clinic.showReports ? "Hide Reports" : "Show Reports";
    }
  }

}
