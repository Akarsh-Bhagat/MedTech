import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: any = "http://localhost:8080/api/v1/auth";
  url: any = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public addUser(user: any) {
    return this.http.post(`${baseUrl}/register`, user);
  }

  getPosts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/doctors`);
  }

  getDataById(id: number): Observable<any> {
    const url = `${this.url}/doctors/${id}`;
    return this.http.get(url);
  }

  postData(data: any): Observable<string> {
    return this.http.post(
      `${this.url}/doctors`,
      data, { responseType: 'text' }
    );
  }

  getDataForEdit(id: number): Observable<any> {
    const url = `${this.url}/${id}`;
    return this.http.get(url);
  }

  updateData(updateData: any, id: number): Observable<string> {
    console.log(updateData);
    const url = `${this.url}/doctors/${id}`;
    console.log(url);
    return this.http.put(url, updateData, { responseType: 'text' });
  }

  deleteData(deleteData: any, id: number): Observable<string> {
    const url = `${this.url}/doctors/${id}`;
    console.log(url);
    return this.http.delete(url, { responseType: 'text' });
  }

  // New methods for saving data

  saveEducation(newEducation: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/education/${id}`;
    return this.http.post(url, newEducation, { responseType: 'text' });
  }

  saveExperience(newExperience: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/experiences/${id}`;
    return this.http.post(url, newExperience, { responseType: 'text' });
  }

  saveMembership(newMembership: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/memberships/${id}`;
    return this.http.post(url, newMembership, { responseType: 'text' });
  }

  saveSpecialization(newSpecialization: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/specializations/${id}`;
    return this.http.post(url, newSpecialization, { responseType: 'text' });
  }

  saveAward(newAward: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/awards/${id}`;
    return this.http.post(url, newAward, { responseType: 'text' });
  }

  saveService(newService: any, id: any): Observable<string> {
    const url = `${this.url}/doctors/servicings/${id}`;
    return this.http.post(url, newService, { responseType: 'text' });
  }
}
