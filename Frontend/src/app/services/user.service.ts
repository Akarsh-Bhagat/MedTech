import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl:any= "http://localhost:8080/api/v1/auth"
  constructor( private http: HttpClient) { }
  public addUser(user:any){
     return this.http.post(`${baseUrl}/register`, user)
  }

  getPosts(): Observable<any[]>{
    return this.http.get<any[]>(`${this.baseUrl}/people`);
  }

  // For view the data 
  getDataById(id: number): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get(url);
  }
  // adding the user details in db
  postData(data: any) {
    return this.http.post<any>(
      this.baseUrl ,
      data
    );
  }

  // Viewing the user details in update form 
  getDataForEdit(id:number):Observable<any>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get(url);
  }

  // Updating the user details
  updateData(updateData: any,id: number): Observable<any>{
    console.log(updateData);
    const url = `${this.baseUrl}/${id}`;
    console.log(url);
    
    return this.http.patch(url, updateData);
  }
}