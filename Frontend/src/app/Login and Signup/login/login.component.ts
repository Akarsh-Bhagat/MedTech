import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';

import { Router } from '@angular/router';
import { CustomInterceptor } from '../../services/custom.interceptor';
import { ToastrService } from 'ngx-toastr';

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
    private router: Router,
    private toastr: ToastrService


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
          this.toastr.success('Login Successful', 'Success', { timeOut: 1500 });
          this.router.navigate(['/homepage']);
        },
        error=>{
         console.log(error);
         this.toastr.error('Something went wrong', 'Error', { timeOut: 1500 });
        }
      )
    }
    else{
      console.log("fields empty")
    }

  }
}