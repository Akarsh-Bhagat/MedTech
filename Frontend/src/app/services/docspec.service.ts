import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DocspecService {
  private docspecsUrl = 'assets/docspecs.json';

  constructor(private http: HttpClient) {}

  getDocspecs(): Observable<string[]> {
    return this.http.get<string[]>(this.docspecsUrl);
  }
}
