import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Patient} from "../../models/patient.model";

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})

export class PatientFormComponent implements OnInit {
  formInstance: FormGroup;

  constructor(public dialogRef: MatDialogRef<PatientFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Patient) {
    this.formInstance = new FormGroup({
      "id": new FormControl('', Validators.required),
      "firstName": new FormControl('', Validators.required),
      "name": new FormControl('', Validators.required),
      "family": new FormControl('', Validators.required),
      "dob": new FormControl('', Validators.pattern("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")),
      "sex": new FormControl('', Validators.required),
      "address": new FormControl('', Validators.required),
      "phone": new FormControl('', Validators.pattern("[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9]"))

    });

    this.formInstance.setValue(data);
  }

  ngOnInit(): void {

  }

  save(): void {
    this.dialogRef.close(this.formInstance.value);
  }
}
