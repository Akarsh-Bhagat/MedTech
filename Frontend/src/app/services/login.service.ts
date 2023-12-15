import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { Observable, Subject, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isAdmin(): boolean {
    throw new Error('Method not implemented.');
  }
  private url = "http://localhost:8080/api/v1/auth";
  

  constructor(private http: HttpClient) { }
  private userRoleChangedSource = new Subject<string>();
  userRoleChanged$ = this.userRoleChangedSource.asObservable();

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
    this.userRoleChangedSource.next(userRole);
    sessionStorage.setItem("userRole", userRole);
    sessionStorage.setItem("accessToken", accessToken);
    sessionStorage.setItem("refreshToken", refreshToken);
    sessionStorage.setItem("accessTokenExpiration", accessTokenExpiration.toString());
    sessionStorage.setItem("refreshTokenExpiration", refreshTokenExpiration.toString());
  }

 
  getAccessToken(): Observable<string | null> {
    const expiration = sessionStorage.getItem("accessTokenExpiration");

    if (expiration && Date.now() < +expiration) {
      return of(sessionStorage.getItem("accessToken"));
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
    const refreshToken = sessionStorage.getItem("refreshToken");

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
    let token= sessionStorage.getItem("accessToken");
    if(token==undefined || token==='' || token ==null){
     return false;
    }
    else{
     return true;
    }
   }

   getUserRole(): string | null {
    return sessionStorage.getItem("userRole")
  }

  logout() {
    sessionStorage.clear();
    return true;
  }
}
