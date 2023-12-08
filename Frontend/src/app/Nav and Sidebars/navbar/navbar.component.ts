import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  currentRoute: string = '';
  userRole: any;
  showLogin: boolean = true;

  constructor(private router: Router,private loginService: LoginService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.url;
      }
    });
  }

  ngOnInit(): void {
    this.currentRoute = this.router.url;
    this.userRole =  localStorage.getItem("userRole");
  }
  toggleDropdownPanel(event: Event): void {
    event.preventDefault();
  }

  toggleLoginSignup(): void {
    this.showLogin = !this.showLogin;
  }


  isLoggedIn(): boolean {
    return this.loginService.isLoggedIn();
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
