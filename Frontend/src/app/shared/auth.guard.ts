import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(): boolean {
    if (this.loginService.isLoggedIn()) {
      const userRole = sessionStorage.getItem('userRole');

      if (userRole === 'ADMIN') {
        this.router.navigate(['/home']);
      } else if (userRole === 'DOCTOR') {
        this.router.navigate(['/doctor/homepage']);
      } else if (userRole === 'PATIENT') {
        this.router.navigate(['/patient/homepage']);
      }

      return true;
    } else {
      return true;
    }
  }
}
