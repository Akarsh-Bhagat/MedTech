import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-doctors',
  templateUrl: './search-doctors.component.html',
  styleUrls: ['./search-doctors.component.css']
})
export class SearchDoctorsComponent {
  specialization: string = '';
  startTime: string = '';
  endTime: string = '';

  @Output() searchRequestEmitter = new EventEmitter<any>();
selectedDate: any;

  searchDoctors(): void {
    // Convert string dates to ISO format
    const formattedStartTime = this.formatToISODate(this.startTime);
    const formattedEndTime = this.formatToISODate(this.endTime);

    const searchRequest = {
      specialization: this.specialization,
      startTime: formattedStartTime,
      endTime: formattedEndTime
    };
    console.log('Search Request:', searchRequest); 
    this.searchRequestEmitter.emit(searchRequest);
  }
  private formatToISODate(dateTimeString: string): string {
    const dateObject = new Date(dateTimeString);
  
    // Set timezone offset for IST
    const timezoneOffsetHours = 5;
    const timezoneOffsetMinutes = 30;
  
    // Adjust the date with the timezone offset
    dateObject.setHours(dateObject.getHours() + timezoneOffsetHours);
    dateObject.setMinutes(dateObject.getMinutes() + timezoneOffsetMinutes);
  
    // Format date string without 'Z' at the end and without milliseconds
    const isoString = dateObject.toISOString().replace('Z', '').replace('.000', '');
  
    return isoString;
  }
  
  
}
