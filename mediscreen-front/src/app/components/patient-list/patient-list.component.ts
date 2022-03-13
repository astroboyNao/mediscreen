import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../models/patient.model";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  dataSource: MatTableDataSource<Patient> = new MatTableDataSource<Patient>();
  displayColumns: string[] = ['id', 'name', 'firstName', 'family', 'dob', 'sex', 'address', 'phone'];

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.getAllPatients();
  }

  getAllPatients(): void {
    this.patientService.getAll().subscribe(
      {
        next: (data) => {
          this.dataSource.data = data;
        }
      }
    )
  }
}
