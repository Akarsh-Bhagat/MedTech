import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  userForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserService
  ){
    this.userForm = this.fb.group({
      firstname: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      lastname: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      address: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
      dob: ['',[Validators.required, this.validateDOB]],
      email: ['',[Validators.required, Validators.email]],
      specialisation: ['', [Validators.required,Validators.pattern('^[a-zA-Z ]*$')]],
    });
  }
  validateDOB(control: AbstractControl) {
    const dob = new Date(control.value);
    const year = dob.getFullYear();
    return year > 1900 ? null : { invalidDOB: true };
  }
  getErrorMessage(controlName: string) {
    const control = this.userForm.get(controlName);
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
    if(this.userForm.valid){
      const userData = this.userForm.value;
      console.log(userData);
      this.userService.postData(userData)
        .subscribe(res => {
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/home']);});
        })
    }
  }
}