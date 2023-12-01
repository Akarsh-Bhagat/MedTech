import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  url: any = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  getPosts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/patient`);
  }

  getDataById(id: number): Observable<any> {
    const url = `${this.url}/patient/${id}`;
    return this.http.get(url);
  }

  postData(data: any): Observable<string> {
    return this.http.post(
      `${this.url}/patient`,
      data, { responseType: 'text' }
    );
  }

  getDataForEdit(id: number): Observable<any> {
    const url = `${this.url}/${id}`;
    return this.http.get(url);
  }

  updateData(updateData: any, id: number): Observable<string> {
    console.log(updateData);
    const url = `${this.url}/patient/${id}`;
    console.log(url);
    return this.http.put(url, updateData, { responseType: 'text' });
  }

  deleteData(deleteData: any, id: number): Observable<string> {
    const url = `${this.url}/patient/${id}`;
    console.log(url);
    return this.http.delete(url, { responseType: 'text' });
  }

}
