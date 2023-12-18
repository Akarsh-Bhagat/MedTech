import { Component } from '@angular/core';

@Component({
  selector: 'app-doctor-appointment',
  templateUrl: './doctor-appointment.component.html',
  styleUrls: ['./doctor-appointment.component.css']
})
export class DoctorAppointmentComponent {

    availableTimeSlots: string[] = ['9:00 AM', '10:00 AM', '11:00 AM'];
  newTimeSlot: string = '';
  isAvailable: boolean = true; // Default value

  addTimeSlot() {
    if (this.newTimeSlot.trim() !== '') {
      this.availableTimeSlots.push(this.newTimeSlot.trim());
      this.newTimeSlot = '';
    }
  }

  submitTimeSlots() {
    console.log('Available Time Slots submitted:', this.availableTimeSlots);
  }

}
