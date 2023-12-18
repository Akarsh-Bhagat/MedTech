import { Component, Output } from '@angular/core';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-patient-appointment',
  templateUrl: './patient-appointment.component.html',
  styleUrls: ['./patient-appointment.component.css']
})
export class PatientAppointmentComponent {
  showAvailableDoctors: boolean = false;
  availableDoctors: any[] = [];

  constructor(private appointmentService: AppointmentService) { }

  onSearchDoctors(searchRequest: any): void {
    this.appointmentService.searchDoctors(searchRequest)
      .subscribe(
        (response) => {
          this.availableDoctors = response;
          this.showAvailableDoctors = true;
        },
        (error) => {
          console.error('Error fetching available doctors:', error);
        }
      );
  }
}
