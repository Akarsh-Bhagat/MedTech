import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent {
  patientForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private patientService: PatientService
  ){
    this.patientForm = this.fb.group({
      firstName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      lastName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      gender: ['', Validators.required],
      address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      dateOfBirth: ['',[Validators.required, this.validateDOB]],
      email: ['',[Validators.required, Validators.email]],
      contact: ['']
    });
  }
  validateDOB(control: AbstractControl) {
    const dateOfBirth = new Date(control.value);
    const year = dateOfBirth.getFullYear();
    return year > 1900 ? null : { invalidDOB: true };
  }
  getErrorMessage(controlName: string) {
    const control = this.patientForm.get(controlName);
    if (control?.hasError('required')) {
      return 'Field is required';
    } else if (control?.hasError('pattern')) {
      return 'Invalid input';
    } else if (control?.hasError('email')) {
      return 'Invalid email';
    } else if (control?.hasError('invalidDOB')) {
      return 'Please provide a valid year after 1900';
    }
    return '';
  }

  onSubmit(){
    console.log("inside submit")
    if(this.patientForm.valid){
      const patientData = this.patientForm.value;
      console.log(patientData);
      this.patientService.postData(patientData)
        .subscribe(res => {
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/patient']);});
        })
    }
  }
}