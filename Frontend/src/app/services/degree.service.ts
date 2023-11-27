// degree.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DegreeService {
  private degreesUrl = 'assets/degrees.json';

  constructor(private http: HttpClient) {}

  getDegrees(): Observable<string[]> {
    return this.http.get<string[]>(this.degreesUrl);
  }
}
