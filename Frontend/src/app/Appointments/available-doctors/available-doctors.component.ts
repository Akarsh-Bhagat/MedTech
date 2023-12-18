import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-available-doctors',
  templateUrl: './available-doctors.component.html',
  styleUrls: ['./available-doctors.component.css']
})
export class AvailableDoctorsComponent {
sendBookingRequest(_t15: any) {
throw new Error('Method not implemented.');
}
viewDoctor(_t15: any) {
throw new Error('Method not implemented.');
}
doctors: any;
@Input() availableDoctors: any[] = [];

}
