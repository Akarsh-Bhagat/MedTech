import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  baseUrl = 'http://localhost:8080/api/v1';
  url: any;

  constructor(private http: HttpClient) { }

  requestAppointment(patientId: number, doctorId: number, appointmentTime: Date): Observable<any> {
    const url = `${this.baseUrl}/appointment/request`;
    const params = {
      patientId: patientId.toString(),
      doctorId: doctorId.toString(),
      appointmentTime: appointmentTime.toISOString() // Convert to ISO string
    };
    return this.http.post(url, null, { params });
  }

  respondToAppointment(appointmentId: number, accept: boolean): Observable<any> {
    const url = `${this.baseUrl}/appointment/respond`;
    const params = {
      appointmentId: appointmentId.toString(),
      accept: accept.toString()
    };
    return this.http.post(url, null, { params });
  }

  getAllAppointments(): Observable<any> {
    const url = `${this.baseUrl}/appointment`;
    return this.http.get(url);
  }

  getAppointmentsByDoctorId(doctorId: number): Observable<any> {
    const url = `${this.baseUrl}/appointment/doctor/${doctorId}`;
    return this.http.get(url);
  }

  getAppointmentByPatientId(patientId: number): Observable<any> {
    const url = `${this.baseUrl}/appointment/patient/${patientId}`;
    return this.http.get(url);
  }

  searchDoctors(searchRequest: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/patient/search-doctors`, searchRequest);
  }

}
