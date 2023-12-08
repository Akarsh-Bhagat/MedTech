import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, FormGroup, FormControl, FormArray, Form } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CollegeService } from '../../services/college.service';
import { Observable, map, startWith } from 'rxjs';
import { DegreeService } from '../../services/degree.service';
import { ToastrService } from 'ngx-toastr';
import { CityService } from '../../services/city.service';
import { DocspecService } from '../../services/docspec.service';


@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userForm!: FormGroup;
  currentStep = 1;
  colleges: any[] = [];
  degrees: any[]= [];
  cities: any[]= [];
  specializations: any[]=[];
  title: string= "Personal Details";
  steps: number[] = [1, 2, 3, 4, 5];
  filteredColleges: Observable<string[]> | undefined;
  collegeControl = new FormControl();
  formexperience!: FormArray<any>;
  formawards!: FormArray<any>;
  formsmedia!: FormArray<any>;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserService,
    private collegeService: CollegeService,
    private degreeService: DegreeService,
    private cityService: CityService,
    private docspecService: DocspecService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.collegeService.getColleges().subscribe(
      (colleges: any[]) => {
        this.colleges = colleges.map(college => college.college); // Assuming "college" is the key in your JSON
      },
      error => {
        console.error('Error fetching colleges', error);
      }
    );

    this.degreeService.getDegrees().subscribe(
      (degrees: any[]) => {
      this.degrees = degrees;
    },
     error => {
        console.error('Error fetching degrees', error);
      }
    );
    this.cityService.getCities().subscribe(
      (cities: any[]) => {
      this.cities = cities;
    },
     error => {
        console.error('Error fetching cities', error);
      }
    );
    this.docspecService.getDocspecs().subscribe(
      (specializations: any[]) => {
      this.specializations = specializations;
    },
     error => {
        console.error('Error fetching specializations', error);
      }
    );
  }

  initializeForm() {
    this.userForm = this.fb.group({
      personalDetails: this.fb.group({
        firstName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        lastName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
        address: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
        dateOfBirth: ['', [Validators.required, this.validateDOB]],
        email: ['', [Validators.required, Validators.email]],
        specialisation: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
        socialMediaList: this.fb.array([
          this.generateSocialMedia()
        ]), // Change from FormControl to FormArray
      }),
      eduDetails: this.fb.group({
        college: [''],
        degree: ['',[Validators.required]],
        yop: ['', [Validators.required,Validators.pattern('^[1-9][0-9]{3}$'), Validators.min(1950), Validators.max(2023)]],
      }),
      expDetails: this.fb.array([
        this.generateExp()
      ]),
      recDetails: this.fb.array([
        this.generateAward()
      ]),
      specDetails: this.fb.group({
        specialisation: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
        servicings: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
        memberships: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
      })
    });

    this.filteredColleges = this.userForm.get('eduDetails.college')!.valueChanges
    .pipe(
      startWith(''),
      map(value => this._filterColleges(value))
    );
  }

  AddExperiences() {
    this.formexperience = this.userForm.get("expDetails") as FormArray;
    this.formexperience.push(this.generateExp());
  }

  generateExp(){
   return this.fb.group({
    hospital: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
    description: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
    city: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
    startYear: ['', [Validators.pattern('^[1-9][0-9]{3}$'), Validators.min(1950), Validators.max(2023)]],
    endYear: ['', [Validators.pattern('^[1-9][0-9]{3}$'), Validators.min(1950), Validators.max(2023)]],
  })
  }

  get expDetails() {
    return this.userForm.get("expDetails") as FormArray;
  }
  removeExp(index: any) {
      this.formexperience = this.userForm.get("expDetails") as FormArray;
      this.formexperience.removeAt(index);
  }

  addAward() {
    this.formawards = this.userForm.get('recDetails') as FormArray;
    this.formawards.push(this.generateAward());
  }
  
  removeAward(index: any) {
    this.formawards = this.userForm.get("recDetails") as FormArray;
    this.formawards.removeAt(index);
  }
  get recDetails() {
    return this.userForm.get("recDetails") as FormArray;
  }
  
  generateAward() {
    return this.fb.group({
      title: ['', [Validators.pattern('^[a-zA-Z ]*$')]],
      recYear: ['', [Validators.pattern('^[1-9][0-9]{3}$'), Validators.min(1950), Validators.max(2023)]],
    });
  }

  generateSocialMedia() {
    return this.fb.group({
      handle: ['', [Validators.pattern('^[a-zA-Z0-9 ,.-]*$')]],
      link: ['', [Validators.pattern('https?://.+')]],
    })
  }

  addSocialMedia() {
    this.formsmedia = this.userForm.get("personalDetails.socialMediaList") as FormArray;
    this.formsmedia.push(this.generateSocialMedia());
}

removeSocialMedia(index: number) {
  this.formsmedia = this.userForm.get("personalDetails.socialMediaList") as FormArray;
  this.formsmedia.removeAt(index);
}

get socialMediaList(): FormArray {
  return this.userForm.get('personalDetails.socialMediaList') as FormArray;
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
      experiences: formData.expDetails.map((experience: any) => ({
        hospital: experience.hospital,
        city: experience.city,
        description: experience.description,
        startYear: experience.startYear,
        endYear: experience.endYear
      })),
      awards: formData.recDetails.map((award: any) => ({
        title: award.title,
        recYear: award.recYear
      })),
      handles: formData.personalDetails.socialMediaList.map((smedia: any) => ({
        handle: smedia.handle,
        link: smedia.link
      })),
      education: [{
        college: formData.eduDetails.college,
        degree: formData.eduDetails.degree,
        yop: formData.eduDetails.yop
      }],
      memberships: [{
        history: formData.specDetails.memberships
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
        this.title = "Education Details";
        break;
      case 3:
        this.title = "Experience Details";
        break;
      case 4:
        this.title = "Awards";
        break;
      case 5:
        this.title = "Specializations, Memberships";
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

  getAvailableSocialMediaOptions(index: number): string[] {
    const selectedHandles = this.userForm.get('personalDetails.socialMediaList')!.value
      .filter((_: any, i: number) => i !== index) 
      .map((item: { handle: any; }) => item.handle);
  
    const allHandles = ['twitter', 'linkedin', 'facebook'];
    return allHandles.filter(handle => !selectedHandles.includes(handle));
  }

  getCurrentFormGroup(): FormGroup | null {
    return this.userForm.get(Object.keys(this.userForm.controls)[this.currentStep - 1]) as FormGroup;
  }

  private _filterColleges(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.colleges.filter(college => college.toLowerCase().includes(filterValue));
  }


}
