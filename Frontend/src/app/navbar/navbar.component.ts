import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  items: MenuItem[] | undefined;

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
}
