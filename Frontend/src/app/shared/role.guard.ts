import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(): boolean {
    let Role= localStorage.getItem("userRole");
    if(Role == "ADMIN"){
      return true;
    }
    alert("You dont't have admin rights")
    return false;
  }
}
