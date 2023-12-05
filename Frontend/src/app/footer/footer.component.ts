import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  private isFooterVisible = false;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    const scrollPosition = window.scrollY + window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    // Show the footer when scrolled to the bottom
    this.isFooterVisible = scrollPosition >= documentHeight;
  }

  get showFooter(): boolean {
    return this.isFooterVisible;
  }
}
