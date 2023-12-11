// role.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const requiredRoles = route.data['requiredRoles'] as Array<string>;

    if (!requiredRoles || requiredRoles.length === 0) {
      return true;
    }

    const userRole = localStorage.getItem('userRole');

    if (userRole && requiredRoles.includes(userRole)) {
      return true;
    } else {
      this.router.navigate(['/denied']);
      return false;
    }
  }
}
