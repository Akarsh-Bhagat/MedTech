// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';


// @Injectable({
//   providedIn: 'root'
// })
// export class DoctorService {
  
  
//   private baseUrl = "http://localhost:8080/api/doctors";

//   constructor(private httpClient: HttpClient) { }
  
//   getDoctorslist(): Observable<Doctor[]> {
//     return this.httpClient.get<Doctor[]>(`${this.baseUrl}`);
//   }

//   createDoctor(doctor: Doctor): Observable<Doctor> {
//     return this.httpClient.post<Doctor>(`${this.baseUrl}`, doctor);
//   }

//   getDoctorById(id: number): Observable<Doctor> {   
//     return this.httpClient.get<Doctor>(`${this.baseUrl}/${id}`);
//   }

//   updateDoctor(id: number, doctor: Doctor): Observable<Object> {
//     return this.httpClient.put(`${this.baseUrl}/${id}`, doctor);
//   }

//   deleteDoctor(id: number): Observable<Object> {
//     return this.httpClient.delete(`${this.baseUrl}/${id}`);
//   }
// }