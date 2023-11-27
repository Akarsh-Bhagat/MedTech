// college.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CollegeService {
  private collegesUrl = '../../assets/colleges.json';

  constructor(private http: HttpClient) {}

  getColleges(): Observable<any[]> {
    return this.http.get<any[]>(this.collegesUrl).pipe(
      tap((colleges) => console.log('Fetched colleges:', colleges)),
      catchError(this.handleError('getColleges', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
