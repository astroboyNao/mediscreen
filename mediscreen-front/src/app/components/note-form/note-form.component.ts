import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, PatternValidator, Validators} from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Patient} from "../../models/patient.model";
import {Note} from "../../models/note.model";

@Component({
  selector: 'app-patient-form',
  templateUrl: './note-form.component.html',
  styleUrls: ['./note-form.component.css']
})

export class NoteFormComponent implements OnInit {
  formInstance: FormGroup;

  constructor(public dialogRef: MatDialogRef<NoteFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Note
  ) {
    this.formInstance = new FormGroup({
      "id": new FormControl('', Validators.required),
      "riskLevel": new FormControl('', Validators.required),
      "note": new FormControl('', Validators.required)
    });

    this.formInstance.setValue(data);
  }

  ngOnInit(): void {

  }

  save(): void {
    this.dialogRef.close(this.formInstance.value);
  }
}
