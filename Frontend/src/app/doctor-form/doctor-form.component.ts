// Import necessary modules from Angular
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.css']
})
export class DoctorFormComponent implements OnInit {
  // Declare a form group to hold the form controls
  doctorForm!: FormGroup;

  // Inject the FormBuilder service to create the form
  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    // Initialize the form structure in the ngOnInit lifecycle hook
    this.doctorForm = this.fb.group({
      services: this.fb.control('', Validators.required),
      experiences: this.fb.control('', Validators.required),
      awards: this.fb.control('', Validators.required),
      education: this.fb.control('', Validators.required),
      specializations: this.fb.control('', Validators.required),
      memberships: this.fb.control('', Validators.required),
    });
  }

  // Function to handle form submission
  onSubmit() {
    if (this.doctorForm.valid) {
      // Send the form data to your backend API for storage
      const formData = this.doctorForm.value;
      // Call your backend API service here to send the data
      console.log(formData);
      // Reset the form after submission
      this.doctorForm.reset();
    }
  }
}
