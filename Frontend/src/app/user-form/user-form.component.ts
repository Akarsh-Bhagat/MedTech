import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  userForm: FormGroup;
  genders: string[] = ['Male', 'Female', 'Other'];


  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserService
  ){
    this.userForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      address: ['', Validators.required],
      dob: ['', Validators.required],
      email: ['', Validators.required],
      specialisation: ['', Validators.required],
      
    });
  }

  onSubmit(){
    console.log("inside submit")
    if(this.userForm.valid){
      const userData = this.userForm.value;
      console.log(userData);
      // navigating to home page
      this.userService.postData(userData)
        .subscribe(res => {
        this.router.navigate(['/']);
        })
    }
  }
}
