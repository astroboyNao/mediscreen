import {Component, OnInit} from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../models/patient.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {PatientFormComponent} from "../patient-form/patient-form.component";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  dataSource: MatTableDataSource<Patient> = new MatTableDataSource<Patient>();
  displayColumns: string[] = ['id', 'name', 'firstName', 'family', 'dob', 'sex', 'address', 'phone', 'edit'];

  constructor(private patientService: PatientService, public dialog: MatDialog) {
  }

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


  edit(data: Patient) {
    const dialogRef = this.dialog.open(PatientFormComponent, {
      width: '400px',
      data: data
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.patientService.edit(result).subscribe({
          next: (patient) => this.getAllPatients()
        });
      }
    });
  }
}
