import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../shared/password-match.directive';
import { UserService } from '../services/user.service';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm = this.fb.group({
    firstName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*$/)]],
    lastName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*$/)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required],
    role: ['',Validators.required]
  }, {
    validators:  passwordMatchValidator
  })

  constructor(
    private fb: FormBuilder,
    private service:LoginService,
    private router: Router
  ) { }
  ngOnInit(): void {
   
   

  }
  get firstName() {
    return this.signupForm.controls['firstName'];
  }

  get lastName() {
    return this.signupForm.controls['lastName'];
  }

  get email() {
    return this.signupForm.controls['email'];
  }

  get password() {
    return this.signupForm.controls['password'];
  }

  get confirmPassword() {
    return this.signupForm.controls['confirmPassword'];
  }

  get role() {
    return this.signupForm.controls['role'];
  }

  public  user={
    firstName:'',
    lastName:'',
    email:'',
    password: '',
    confirmPassword:'',
    role:''
  }

  formSubmit(){
    if(this.user.firstName == '' || this.user.firstName==null ){
      alert( 'UserName is required');
      return
    }

    this.service.register(this.signupForm.value).subscribe(
      (data)=>{
        console.log(data);
        alert('Successfully registered');
        this.router.navigate(['/homepage']);
      },
      (error)=>{
        console.log(error);
        console.log(this.signupForm.value)
        alert('Something went wrong')
      }
    )
  }



}