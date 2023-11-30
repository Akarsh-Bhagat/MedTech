import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtHelperService {
  private readonly TOKEN_KEY = 'token';

  constructor() {}

  // Save the token to localStorage
  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  // Get the token from localStorage
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  // Decode the token and return the payload
  decodeToken(token: string): any | null {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload;
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  // Check if the token is expired
  isTokenExpired(token: string): boolean {
    const payload = this.decodeToken(token);

    if (payload && payload.exp) {
      const expirationDate = new Date(payload.exp * 1000);
      return expirationDate <= new Date();
    }

    return true; // Token is considered expired if there's no 'exp' claim
  }

  // Remove the token from localStorage
  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
