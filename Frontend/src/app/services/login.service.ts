
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
    loginUser(token: string){
      localStorage.setItem("token", token)
      return true;
    }
  // to check that user is logeed in
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
    // for getting the token
    getToken(){
      return localStorage.getItem("token")
    }
}