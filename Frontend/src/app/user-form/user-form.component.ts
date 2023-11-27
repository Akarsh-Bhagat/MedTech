import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { CollegeService } from '../services/college.service';
import { Observable, map, startWith } from 'rxjs';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userForm!: FormGroup;
  currentStep = 1;
  colleges: any[] = [];
  title: string= "Personal Details";
  steps: number[] = [1, 2, 3, 4, 5];
  filteredColleges: Observable<string[]> | undefined;
  collegeControl = new FormControl();

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserService,
    private collegeService: CollegeService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    // this.collegeService.getColleges().subscribe(data => {
    //   this.colleges = data;
    // });
    this.collegeService.getColleges().subscribe(
      (colleges: any[]) => {
        this.colleges = colleges.map(college => college.college); // Assuming "college" is the key in your JSON
      },
      error => {
        console.error('Error fetching colleges', error);
      }
    );
  }

  initializeForm() {
    this.userForm = this.fb.group({
      personalDetails: this.fb.group({
        firstName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        address: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
        dateOfBirth: ['', [Validators.required, this.validateDOB]],
        email: ['', [Validators.required, Validators.email]],
        specialisation: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
      }),
      eduDetails: this.fb.group({
        college: [''],
        degree: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        yop: ['', [Validators.pattern('^[1-9][0-9]{3}$'), Validators.min(1950), Validators.max(2023)]],
      }),
      expDetails: this.fb.group({
        hospital: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        description: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
        city: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        startYear: ['', [Validators.required]],
        endYear: ['', [Validators.required]],
      }),
      recDetails: this.fb.group({
        awards: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        recYear: [''],
        memberships: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
      }),
      specDetails: this.fb.group({
        specialisation: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        servicings: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
      })
    });

    this.filteredColleges = this.userForm.get('eduDetails.college')!.valueChanges
    .pipe(
      startWith(''),
      map(value => this._filterColleges(value))
    );
  }


  validateDOB(control: AbstractControl) {
    const dateOfBirth = new Date(control.value);
    const currentDate = new Date();
    if (dateOfBirth > currentDate) {
      return { 'invalidDOB': true, 'futureDate': true };
    }

    const age = currentDate.getFullYear() - dateOfBirth.getFullYear();
    if (age < 18) {
      return { 'invalidDOB': true, 'underage': true };
    }

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
    } else if (control?.hasError('underage') ) {
      return 'Underage to be a doctor!';
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
      email: formData.personalDetails.email,
      address: formData.personalDetails.address,
      dateOfBirth: formData.personalDetails.dateOfBirth,
      specialisation: formData.personalDetails.specialisation,
      experiences: [{
        hospital: formData.expDetails.hospital,
        city: formData.expDetails.city,
        description: formData.expDetails.description,
        startYear: formData.expDetails.startYear,
        endYear: formData.expDetails.endYear
      }],
      awards: [{
        title: formData.recDetails.awards,
        recYear: formData.recDetails.recYear
      }],
      education: [{
        college: formData.eduDetails.college,
        degree: formData.eduDetails.degree,
        yop: formData.eduDetails.yop
      }],
      memberships: [{
        history: formData.recDetails.memberships
      }],
      servicings: [{
        name: formData.specDetails.servicings
      }],
      specializations: [{
        name: formData.specDetails.specialisation
      }]
    };
  }

  onSubmit() {
    if (this.userForm.valid) {
      const formData =  this.transformFormData(this.userForm.value);
      console.log(formData);
      this.userService.postData(formData).subscribe(
        (response) => {
        console.log('Doctor created successfully:', response);
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/home']);
        });
      },
      (error) => {
        console.error('Error creating doctor:', error);

        if (error.error instanceof ErrorEvent) {
          // Client-side error (e.g., network issues)
          console.error('Client-side error:', error.error.message);
        } else {
          // Backend returned an unsuccessful response code
          console.error(`Backend returned code ${error.status}, body was:`, error.error);
        }

        // Handle error as needed
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
        this.title = "Education Details";
        break;
      case 3:
        this.title = "Experience Details";
        break;
      case 4:
        this.title = "Awards and Memberships";
        break;
      case 5:
        this.title = "Specializations and Servicings";
        break;
    }
  }



  nextStep() {
    const currentFormGroup = this.getCurrentFormGroup();
   
    if (currentFormGroup?.valid) {
      this.currentStep++;
      this.updateTitle();
    }
  }

  prevStep() {
    this.currentStep--;
    this.updateTitle();
  }

  getCurrentFormGroup(): FormGroup | null {
    return this.userForm.get(Object.keys(this.userForm.controls)[this.currentStep - 1]) as FormGroup;
  }

  private _filterColleges(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.colleges.filter(college => college.toLowerCase().includes(filterValue));
  }
}
