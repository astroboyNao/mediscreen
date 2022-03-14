import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Patient} from "../models/patient.model";
const baseUrl = 'http://localhost:8081/api';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Patient[]> {
    return this.http.get<Patient[]>(baseUrl + "/patients");
  }

  edit(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(baseUrl + "/patient", patient);
  }
}
