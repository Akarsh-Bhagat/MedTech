import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-superlogin',
  templateUrl: './superlogin.component.html',
  styleUrls: ['./superlogin.component.css']
})
export class SuperloginComponent {
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
          alert('Login successful');
          this.router.navigate(['/home']);
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
