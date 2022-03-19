import {Component, Inject, OnInit} from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../models/patient.model";
import {MatTableDataSource} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Note} from "../../models/note.model";

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {
  dataSource: MatTableDataSource<Note> = new MatTableDataSource<Note>();
  displayColumns: string[] = ['id', 'riskLevel', 'note'];

  constructor(public dialogRef: MatDialogRef<NoteListComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Patient,
              private patientService: PatientService, public dialog: MatDialog) {
    this.getAllNotes(data);
  }

  ngOnInit(): void {
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
}
