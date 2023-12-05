import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';

import { Router } from '@angular/router';
import { CustomInterceptor } from '../services/custom.interceptor';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit{
  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  })
  credentials={
    email: '',
    password:''
  }
  constructor(
    private fb: FormBuilder,

    private loginService:LoginService,
    private router: Router 


  ) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  get email() {
    return this.loginForm.controls['email'];
  }
  get password() { return this.loginForm.controls['password']; }
  onSubmit(){
    if((this.credentials.email!='' && this.credentials.password!='' ) &&(this.credentials.email!=null && this.credentials.password!=null )){
      this.loginService.authenticate(this.credentials).subscribe(
        (response:any)=>{
          alert('Login successful'),
          this.router.navigate(['/']);
        },
        error=>{
         console.log(error);
         alert('Bad credentials')
        }
      )
    }
    else{
      console.log("fields empty")
    }

  }
}