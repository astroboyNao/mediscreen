import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, PatternValidator, Validators} from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Patient} from "../../models/patient.model";
import {Note} from "../../models/note.model";
import {Assess} from "../../models/assess.model";

@Component({
  selector: 'app-patient-form',
  templateUrl: './assess-form.component.html',
  styleUrls: ['./assess-form.component.css']
})

export class AssessFormComponent {
  public assess: Assess;

  constructor(public dialogRef: MatDialogRef<AssessFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Assess
  ) {
    console.log(data);
    this.assess = data;
  }

  cloase(): void {
    this.dialogRef.close();
  }
}
