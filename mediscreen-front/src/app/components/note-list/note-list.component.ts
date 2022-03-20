import {Component, Inject, OnInit} from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../models/patient.model";
import {MatTableDataSource} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Note} from "../../models/note.model";
import {PatientFormComponent} from "../patient-form/patient-form.component";
import {NoteFormComponent} from "../note-form/note-form.component";

@Component({
  selector: 'app-com.abernathy.note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {
  dataSource: MatTableDataSource<Note> = new MatTableDataSource<Note>();
  displayColumns: string[] = ['id', 'riskLevel', 'note', 'add'];
  patient: Patient;

  constructor(public dialogRef: MatDialogRef<NoteListComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Patient,
              private patientService: PatientService, public dialog: MatDialog) {
    this.patient = data;
  }

  ngOnInit(): void {
    this.getAllNotes(this.patient);
  }

  getAllNotes(patient: Patient): void {
    this.patientService.getAllNotes(patient).subscribe(
      {
        next: (data) => {
          this.dataSource.data = data;
        }
      }
    )
  }

  add() {
    let note: Note = {
      note: "", riskLevel: ""
    };
    const dialogRef = this.dialog.open(NoteFormComponent, {
      width: '400px',
      data: note
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.patientService.addNoteForPatient(result, this.patient).subscribe({
          next: (note) => this.getAllNotes(this.patient)
        });
      }
    });
  }
}
