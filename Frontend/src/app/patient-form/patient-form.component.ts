import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, FormGroup, FormControl, FormArray, Form } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, map, startWith } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { CityService } from '../services/city.service';
import { PatientService } from '../services/patient.service';


@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {
  patientForm!: FormGroup;
  currentStep = 1;
  cities: any[]= [];
  title: string= "Personal Details";
  steps: number[] = [1, 2, 3];
  formallergies!: FormArray<any>;
  formsurgeries!: FormArray<any>;
  formpconditions!: FormArray<any>;
  formmedications!: FormArray<any>;
  formclinic!: FormArray<any>;


  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private patientService: PatientService,
    private cityService: CityService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    
    this.initializeForm();
    this.cityService.getCities().subscribe(
      (cities: any[]) => {
      this.cities = cities;
    },
     error => {
        console.error('Error fetching cities', error);
      }
    );
  }

  initializeForm() {
    this.patientForm = this.fb.group({
      personalDetails:  this.fb.group({
        firstName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
        lastName: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
        gender: ['', Validators.required],
        address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
        dateOfBirth: ['',[Validators.required, this.validateDOB]],
        email: ['',[Validators.required, Validators.email]],
        contact: [''] 
      }),
      medicalHistory: this.fb.group({
        pastConditions: this.fb.array([
          this.generatePastConditions()
        ]),
        allergies: this.fb.array([
          this.generateAllergies()
        ]),
        surgeries: this.fb.array([
          this.generateSurgeries()
        ]),
        medications: this.fb.array([
          this.generateMedications()
        ]),
        familyHistory: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
        bloodGroup: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]]
      }),
      clinicDetails: this.fb.array([
        this.generateClinic()
      ])
    });

  }

  AddClinic() {
    this.formclinic = this.patientForm.get("clinicDetails") as FormArray;
    this.formclinic.push(this.generateClinic());
  }

  generateClinic(){
   return this.fb.group({
    hospital: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
    department: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
    attendingDoctor: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
    admissionDate: [''],
    dischargeDate: [''],
    clinicRoom: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
  })
  }

  get clinicDetails() {
    return this.patientForm.get("clinicDetails") as FormArray;
  }
  removeClinic(index: any) {
      this.formclinic = this.patientForm.get("clinicDetails") as FormArray;
      this.formclinic.removeAt(index);
  }


  generateAllergies() {
    return this.fb.group({
      allergy: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]]
    })
  }

  addAllergy() {
    this.formallergies = this.patientForm.get('medicalHistory.allergies') as FormArray;
    this.formallergies.push(this.generateAllergies());
  }

  removeAllergy(index: number) {
  this.formallergies = this.patientForm.get('medicalHistory.allergies') as FormArray;
  this.formallergies.removeAt(index);}

  get allergies(): FormArray {
  return this.patientForm.get('medicalHistory.allergies') as FormArray;}

  generateSurgeries() {
    return this.fb.group({
      surgery: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]]
    })
  }

  addSurgery() {
    this.formsurgeries = this.patientForm.get('medicalHistory.surgeries') as FormArray;
    this.formsurgeries.push(this.generateSurgeries());
  }

  removeSurgery(index: number) {
  this.formsurgeries = this.patientForm.get('medicalHistory.surgeries') as FormArray;
  this.formsurgeries.removeAt(index);}

  get surgeries(): FormArray {
  return this.patientForm.get('medicalHistory.surgeries') as FormArray;}

  generatePastConditions() {
    return this.fb.group({
      pastCondition: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]]
    })
  }

  addPastCondition() {
    this.formpconditions = this.patientForm.get('medicalHistory.pastConditions') as FormArray;
    this.formpconditions.push(this.generatePastConditions());
  }

  removePastCondition(index: number) {
  this.formpconditions = this.patientForm.get('medicalHistory.pastConditions') as FormArray;
  this.formpconditions.removeAt(index);}

  get pastConditions(): FormArray {
  return this.patientForm.get('medicalHistory.pastConditions') as FormArray;}

  generateMedications() {
    return this.fb.group({
      medication: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]]
    })
  }

  addMedication() {
    this.formsurgeries = this.patientForm.get('medicalHistory.medications') as FormArray;
    this.formsurgeries.push(this.generateMedications());
  }

  removeMedication(index: number) {
  this.formsurgeries = this.patientForm.get('medicalHistory.medications') as FormArray;
  this.formsurgeries.removeAt(index);}

  get medications(): FormArray {
  return this.patientForm.get('medicalHistory.medications') as FormArray;}


  validateDOB(control: AbstractControl) {
    const dateOfBirth = new Date(control.value);
    const currentDate = new Date();
    if (dateOfBirth > currentDate) {
      return { 'invalidDOB': true, 'futureDate': true };
    }
    const age = currentDate.getFullYear() - dateOfBirth.getFullYear();
    const maxAgeLimit = 120;
    if (age > maxAgeLimit) {
      return { 'invalidDOB': true, 'tooOld': true };
    } 
    return null;
  }

  getErrorMessage(control: any) {
    if (control?.hasError('required')) {
      return 'Field is required';
    } else if (control?.hasError('pattern')) {
      return 'Invalid input';
    } else if (control?.hasError('email')) {
      return 'Invalid email';
    } 
    else if (control?.hasError('futureDate') ) {
      return 'Please provide a valid date of birth!';
    }
    else if (control?.hasError('tooOld') ) {
      return 'Seems like older than expected!';
    }
    return '';
  }

  transformFormData(formData: any): any {
    return {
      firstName: formData.personalDetails.firstName,
      lastName: formData.personalDetails.lastName,
      gender: formData.personalDetails.gender,
      email: formData.personalDetails.email,
      address: formData.personalDetails.address,
      dateOfBirth: formData.personalDetails.dateOfBirth,
      medicalHistory: [{
        pastConditions: formData.medicalHistory.pastConditions,
        allergies: formData.medicalHistory.allergies,
        surgeries: formData.medicalHistory.surgeries,
        medications: formData.medicalHistory.medications,
        familyHistory: formData.medicalHistory.familyHistory,
        bloodGroup: formData.medicalHistory.bloodGroup
      }],
      clinics: formData.clinicDetails.map((clinic: any) => ({
        admissionDate: clinic.admissionDate,
        dischargeDate: clinic.dischargeDate,
        attendingDoctor: clinic.attendingDoctor,
        clinicRoom: clinic.clinicRoom,
        hospital: clinic.hospital,
        department: clinic.department,
      }))
    };
  }

  onSubmit() {
    if (this.patientForm.valid) {
      const formData =  this.transformFormData(this.patientForm.value);
      console.log(formData);
      this.patientService.postData(formData).subscribe(
        (response) => {
        console.log('Patient added successfully:', response);
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/patient']);
        });
      },
      (error) => {
        console.error('Error adding patient:', error);
        if (error.error instanceof ErrorEvent) {
          console.error('Client-side error:', error.error.message);
        } else {
          console.error(`Backend returned code ${error.status}, body was:`, error.error);
        }
      }
      );
    }
  }

  updateTitle(): void {
    switch (this.currentStep) {
      case 1:
        this.title = "Personal Details";
        break;
      case 2:
        this.title = "Medical History";
        break;
      case 3:
        this.title = "Clinic ";
        break;
    }
  }

  nextStep() {
    const currentFormGroup = this.getCurrentFormGroup();
   
    if (currentFormGroup?.valid) {
      this.currentStep++;
      this.toastr.success('Successfully moved to the next step', 'Success', {
        timeOut: 1000,
        progressBar: true,
        closeButton: true,
        positionClass: 'toast-top-right', 
      });
      this.updateTitle();
    } else {
      this.toastr.error('Form is incomplete or invalid!', 'Error', {
        timeOut: 1000,
        progressBar: true,
        closeButton: true,
        positionClass: 'toast-top-right',  
      });
    }
  }

  prevStep() {
    this.currentStep--;
    this.toastr.success('Successfully moved to prev step', 'Success', {
      timeOut: 1000, 
      progressBar: true, 
      closeButton: true, 
      positionClass: 'toast-top-right',
    });
    this.updateTitle();
  }


  getCurrentFormGroup(): FormGroup | null {
    return this.patientForm.get(Object.keys(this.patientForm.controls)[this.currentStep - 1]) as FormGroup;
  }
}
