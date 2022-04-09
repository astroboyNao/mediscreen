import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Patient} from "../models/patient.model";
import {Note} from "../models/note.model";
import {Assess} from "../models/assess.model";
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
    return this.http.put<Patient>(baseUrl + "/patient", patient);
  }

  add(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(baseUrl + "/patient", patient);
  }

  getAllNotes(patient: Patient): Observable<Note[]> {
    return this.http.get<Note[]>(baseUrl + "/patient/" + patient.id + "/notes");
  }

  addNoteForPatient(note: Note, patient: Patient): Observable<Note> {
    return this.http.post<Note>(baseUrl + "/patient/" + patient.id + "/note", note);
  }

  updateNoteForPatient(note: Note, patient: Patient): Observable<Note> {
    return this.http.put<Note>(baseUrl + "/patient/" + patient.id + "/note/" + note.id, note);
  }

  getAssess(patient: Patient): Observable<Assess> {
    return this.http.get<Assess>(baseUrl + "/patient/" + patient.id + "/assess");
  }
}
