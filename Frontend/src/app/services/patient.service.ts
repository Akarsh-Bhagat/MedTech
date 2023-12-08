import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  baseUrl: any = "http://localhost:8080/api/v1/auth";
  url: any = "http://localhost:8080/api/v1";

  constructor(private http: HttpClient) { }

  public addUser(user: any) {
    return this.http.post(`${baseUrl}/register`, user);
  }

  getPosts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/patient`);
  }

  getPatientsCount(): Observable<number> {
    return this.http.get<any[]>(`${this.url}/patient`).pipe(
      map(patients => patients.length)
    );
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


  saveClinic(newClinic: any, id: any): Observable<string> {
    const url = `${this.url}/patient/clinic/${id}`;
    return this.http.post(url, newClinic, { responseType: 'text' });
  }

}
