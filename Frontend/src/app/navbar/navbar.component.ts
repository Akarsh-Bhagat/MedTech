import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
navToElement(arg0: string) {
throw new Error('Method not implemented.');
}
  items: MenuItem[] | undefined;
navbarcolour: any;
light: any;

  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-home',
        routerLink: '/'
      },
      {
        label: 'About',
        icon: 'pi pi-info',
        routerLink: '/about'
      },
      {
        label: 'Services',
        icon: 'pi pi-briefcase',
        routerLink: '/services'
      },
      {
        label: 'Contact',
        icon: 'pi pi-envelope',
        routerLink: '/contact'
      }
    ];
  }

  isDropdownVisible = false;

  toggleDropdownPanel(event: Event): void {
    event.preventDefault();
    this.isDropdownVisible = !this.isDropdownVisible;
  }
}
