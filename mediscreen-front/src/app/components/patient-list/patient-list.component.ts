import {Component, OnInit} from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../models/patient.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {PatientFormComponent} from "../patient-form/patient-form.component";
import {NoteListComponent} from "../note-list/note-list.component";
import {AssessFormComponent} from "../assess-form/assess-form.component";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  dataSource: MatTableDataSource<Patient> = new MatTableDataSource<Patient>();
  displayColumns: string[] = ['id', 'name', 'firstName', 'family', 'dob', 'sex', 'address', 'phone', 'assess', 'notes', 'edit'];

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

  showAssess(data: Patient) {
    this.patientService.getAssess(data).subscribe({
      next: (assess) => {

        const dialogRef = this.dialog.open(AssessFormComponent, {
          width: '800px',
          data: assess
        });
      }
    });
  }

  showNotes(data: Patient) {
    const dialogRef = this.dialog.open(NoteListComponent, {
      width: '800px',
      data: data
    });
  }

  add() {
    let patient: Patient = {
      address: "", dob: "", family: "", firstName: "", id: 0, name: "", phone: "", sex: ""
    };
    const dialogRef = this.dialog.open(PatientFormComponent, {
      width: '400px',
      data: patient
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.patientService.add(result).subscribe({
          next: (patient) => this.getAllPatients()
        });
      }
    });
  }
}
