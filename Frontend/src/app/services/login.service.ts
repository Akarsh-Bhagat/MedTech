
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  url= "http://localhost:8080/api/v1/auth"
  constructor(private http: HttpClient) { }
  // calling server to generate token
  generateToken(credentials:any){
    return this.http.post(`${this.url}/authenticate`, credentials)
  }
    //for login user

    loginUser(token: any){

      localStorage.setItem("token", token)
      return true;
    }
 
    isLoggedIn(){
     let token= localStorage.getItem("token");
     if(token==undefined || token==='' || token ==null){
      return false;
     }
     else{
      return true;
     }
    }
    logout(){
   localStorage.removeItem('token');
   return true;
    }

    getToken(){
      return localStorage.getItem("token")
    }
}