import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PatientService {
  getPatient(patientId: any) {
    throw new Error('Method not implemented.');
  }
  // baseUrl:any= "http://localhost:8080/api/v1/auth"
  url:any="http://localhost:8080/api"

  constructor( private http: HttpClient) { }
  public addUser(user:any){
     return this.http.post(`${baseUrl}/register`, user)
  }

  getPosts(): Observable<any[]>{
    return this.http.get<any[]>(`${this.url}/patient`);
  }

  // For view the data 
  getDataById(id: number): Observable<any> {
    const url = `${this.url}/patient/${id}`;
    return this.http.get(url);
  }
  // adding the patient details in db
  postData(data: any) {
    return this.http.post<any>(
      `${this.url}/patient`,
      data
    );
  }

  // Viewing the patient details in update form 
  getDataForEdit(id:number):Observable<any>{
    const url = `${this.url}/${id}`;
    return this.http.get(url);
  }

  // Updating the patient details
  updateData(updateData: any,id: number): Observable<any>{
    console.log(updateData);
    const url = `${this.url}/patient/${id}`;
    console.log(url);
    
    return this.http.put(url, updateData);
  }

  //deleting a patient
  deleteData(deleteData: any,id:number): Observable<any>{
    const url = `${this.url}/patient/${id}`;
    console.log(url);
    return this.http.delete(url, deleteData);
  }
}