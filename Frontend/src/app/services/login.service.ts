import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isAdmin(): boolean {
    throw new Error('Method not implemented.');
  }
  private url = "http://localhost:8080/api/v1/auth";
  

  constructor(private http: HttpClient) { }

  authenticate(credentials: any) {
    return this.http.post(`${this.url}/authenticate`, credentials).pipe(
      tap((response: any) => {
        if (response && response.accessToken && response.refreshToken &&
            response.accessExp && response.refreshExp && response.userRole) {
          const now = Date.now();

          const accessTokenExpiration = now + response.accessExp;
          const refreshTokenExpiration = now + response.refreshExp;

          this.storeTokens(response.accessToken, response.refreshToken, accessTokenExpiration, refreshTokenExpiration, response.userRole);
          console.log('Tokens stored successfully.');
        }
      }),
      catchError(error => {
        console.error('Error during authentication:', error);
        return throwError(error);
      })
    );
  }

  register(credentials: any) {
    return this.http.post(`${this.url}/register`, credentials).pipe(
      tap((response: any) => {
        if (response && response.accessToken && response.refreshToken &&
            response.accessExp && response.refreshExp && response.userRole) {
          const now = Date.now();

          const accessTokenExpiration = now + response.accessExp;
          const refreshTokenExpiration = now + response.refreshExp;

          this.storeTokens(response.accessToken, response.refreshToken, accessTokenExpiration, refreshTokenExpiration, response.userRole);
          console.log('Tokens stored successfully.');
        }
      }),
      catchError(error => {
        console.error('Error during authentication:', error);
        return throwError(error);
      })
    );
  }

  private storeTokens(accessToken: string, refreshToken: string, accessTokenExpiration: number, refreshTokenExpiration: number, userRole: string) {
    localStorage.setItem("userRole", userRole);
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
    localStorage.setItem("accessTokenExpiration", accessTokenExpiration.toString());
    localStorage.setItem("refreshTokenExpiration", refreshTokenExpiration.toString());
  }

 
  getAccessToken(): Observable<string | null> {
    const expiration = localStorage.getItem("accessTokenExpiration");

    if (expiration && Date.now() < +expiration) {
      return of(localStorage.getItem("accessToken"));
    } else {
      return this.refreshToken().pipe(
        catchError(() => {
          this.logout();
          return of(null);
        })
      );
    }
  }

  refreshToken(): Observable<string | null> {
    const refreshToken = localStorage.getItem("refreshToken");

    if (!refreshToken) {
      return throwError("Refresh token not available");
    }

    return this.http.post<any>(`${this.url}/refresh-token`, { refreshToken }).pipe(
      switchMap(response => {
        if (response && response.accessToken && response.refreshToken &&
          response.accessExp && response.refreshExp) {
          const now = Date.now();

          const accessTokenExpiration = now + response.accessExp;
          const refreshTokenExpiration = now + response.refreshExp;

          this.storeTokens(response.accessToken, response.refreshToken, accessTokenExpiration, refreshTokenExpiration, response.userRole);

          return of(response.accessToken);
        } else {
          return throwError("Invalid response during token refresh");
        }
      })
    );
  }

  isLoggedIn(){
    let token= localStorage.getItem("accessToken");
    if(token==undefined || token==='' || token ==null){
     return false;
    }
    else{
     return true;
    }
   }

  logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('accessTokenExpiration');
    localStorage.removeItem('refreshTokenExpiration');
    localStorage.removeItem('userRole');
    return true;
  }
}
