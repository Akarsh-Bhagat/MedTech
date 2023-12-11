import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  isSidebarOpen: boolean = true;
  userRole: any;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.userRole =  localStorage.getItem("userRole");
  }

  toggleSidebar(): void {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  navigateToDashboard() {
    const currentRoute = this.router.url;
    if (this.userRole === 'ADMIN') {
      if (currentRoute === '/home') {
        window.location.reload();
      }
      this.router.navigate(['/home']);
    } else if(this.userRole === 'DOCTOR') {
      if (currentRoute === '/doctor/homepage') {
        window.location.reload();
      }
      this.router.navigate(['/doctor/homepage']);
    }
    else if(this.userRole === 'PATIENT') {
      if (currentRoute === '/patient/homepage') {
        window.location.reload();
      }
      this.router.navigate(['/patient/homepage']);
    }
  }
}
