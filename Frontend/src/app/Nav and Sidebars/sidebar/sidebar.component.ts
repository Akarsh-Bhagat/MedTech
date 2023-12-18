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
    this.userRole =  sessionStorage.getItem("userRole");
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

  navigateToForm() {
    const currentRoute = this.router.url;
    if (this.userRole === 'DOCTOR') {
      if (currentRoute === '/form') {
        window.location.reload();
      }
      this.router.navigate(['/form']);
    } else if(this.userRole === 'PATIENT') {
      if (currentRoute === '/patient/form') {
        window.location.reload();
      }
      this.router.navigate(['/patient/form']);
    }
  }
  navigateToProfile() {
    const currentRoute = this.router.url;
    const id = sessionStorage.getItem("userId");
  
    if (this.userRole === 'DOCTOR') {
      const doctorRoute = `/view/${id}`;
      if (currentRoute.includes('/view/') && id) {    
        if (currentRoute === doctorRoute) {
          window.location.reload();
        }
      } else {
        this.router.navigate([doctorRoute]);
      }
    } else if (this.userRole === 'PATIENT') {
      if (currentRoute.includes('/patient/view/') && id) {
        const patientRoute = `/patient/view/${id}`;
        if (currentRoute === patientRoute) {
          window.location.reload();
        }
        this.router.navigate([patientRoute]);
      } else {
        this.router.navigate(['/patient/form']);
      }
    }
  }
  
}
