import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { HttpHeaders } from '@angular/common/http';
import { PatientService } from '../../services/patient.service';


@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {
  patientForm!: FormGroup;
  patient: any = []

  constructor(private route: ActivatedRoute, private router: Router, private patientService: PatientService, private fb: FormBuilder) { }
  
  ngOnInit(): void {
  
    // Initializing  form variable
    this.patientForm = this.fb.group({
      firstName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      lastName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      gender: ['', Validators.required],
      address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      dateOfBirth: [''],
      email: ['',[Validators.required, Validators.email]],
      contact:['']
    });

      this.route.params.subscribe(params => {
        const id = params['id'];
        this.patientService.getDataById(id).subscribe((data: any) => {
          this.patient=data;
          const {firstName, lastName, gender, address, dateOfBirth, email, contact} = data
          this.patientForm.setValue({
            firstName, lastName, gender, address, dateOfBirth, email, contact
          })

          
          console.log(data);
        });
    })
      
  }

  getErrorMessage(controlName: string) {
    const control = this.patientForm.get(controlName);
    if (control?.hasError('required')) {
      return 'Field is required';
    } else if (control?.hasError('pattern')) {
      return 'Invalid input';
    } else if (control?.hasError('gender')) {
      return 'Invalid gender';
    } else if (control?.hasError('email')) {
      return 'Invalid email';
    } else if (control?.hasError('invalidDOB')) {
      return 'Please provide a valid year after 1900';
    }
    return '';
  }

 


  onSubmit(){
    if(this.patientForm.valid){
      const patientData = this.patientForm.value;
      console.log(patientData);
      
      // navigating to home page
      this.patientService.updateData(patientData,this.patient.id)
        .subscribe(res => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/patient']);});
        })
    }
  }
}
