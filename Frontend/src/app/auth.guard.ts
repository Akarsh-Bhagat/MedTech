// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { JwtHelperService } from './services/jwt-helper.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService, private jwtHelper: JwtHelperService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const token = this.authService.getToken();

    if (token && !this.jwtHelper.isTokenExpired(token)) {
      // Token is valid, allow access
      return true;
    }

    // Token is invalid or not present, redirect to login
    this.router.navigate(['/login']);
    return false;
  }
}
