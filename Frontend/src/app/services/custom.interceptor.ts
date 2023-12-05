import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { LoginService } from './login.service';

@Injectable()
export class CustomInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.loginService.getAccessToken().pipe(
      switchMap((token: string | null) => {
        if (token) {
          request = this.addToken(request, token);
        }

        return next.handle(request).pipe(
          catchError((error) => {
            if (error instanceof HttpErrorResponse && error.status === 401) {
              return this.handleUnauthorizedError(request, next);
            }

            return throwError(error);
          })
        );
      })
    );
  }

  private addToken(request: HttpRequest<unknown>, token: string): HttpRequest<unknown> {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  private handleUnauthorizedError(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.loginService.refreshToken().pipe(
      switchMap((newToken: string | null) => {
        if (newToken) {
          request = this.addToken(request, newToken);
          return next.handle(request);
        }
        this.loginService.logout();
        return throwError('Unable to refresh token. Logging out.');
      })
    );
  }
}
