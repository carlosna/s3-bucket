import {Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA, MatInputModule} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import { Bucket } from '../models/bucket.model';

@Component({
    selector: 'dialog-box',
    templateUrl: './dialog.component.html'
})
export class DialogComponent implements OnInit {

    form: FormGroup;
    keyname: string;

    constructor(
       private formBuilder: FormBuilder,
        private dialogRef: MatDialogRef<DialogComponent>,
        @Inject(MAT_DIALOG_DATA) private data) {
        }

    ngOnInit() {
        this.form = this.formBuilder.group({
            keyname: ""
        })
    }

    submit(form) {
        this.dialogRef.close();
      }
      //`${form.value.keyname}`
    // save() {
    //     this.dialogRef.close(this.form.value);
    // }

    // close() {
    //     this.dialogRef.close();
    // }

}