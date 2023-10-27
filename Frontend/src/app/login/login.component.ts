import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';

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
    private loginService:LoginService
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
      console.log("Submit the form")

      // token generte

      this.loginService.generateToken(this.credentials).subscribe(
        response=>{
          console.log(response)
        },
        error=>{
         console.log(error);
        }
      )
    }
    else{
      console.log("fields empty")
    }
  }
 
}