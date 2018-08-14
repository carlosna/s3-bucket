import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Bucket } from '../models/bucket.model';
import { BucketService } from './bucket.service';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { DialogComponent } from 'src/app/bucket/dialog.component';

@Component({
  selector: 'app-bucket',
  templateUrl: './bucket.component.html',
  styles: []
})
export class BucketComponent implements OnInit {
  
  p: number = 1;
  buckets: Bucket[];
  dialogRef: MatDialogRef<DialogComponent>
  key: string;
  old_key: string;

  constructor(private router: Router, 
              private bucketService: BucketService, 
              private dialog: MatDialog) {

  }
  
  openDialog(bucket: Bucket): void{
    const dialogConfig = new MatDialogConfig()

    const dialogRef = this.dialog.open(DialogComponent, {
      width: '300px',
      height: '200x',
      autoFocus: true,
      data: {
        key: bucket.key
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.key = result;
      this.old_key = bucket.key;
      this.renameObject(result, this.old_key)
    });
    
 }  

  ngOnInit() {
    this.bucketService.getObjects()
      .subscribe( data => {
        this.buckets = data;
      });
  };

  renameObject(bucket: Bucket, old_key: string): void {
    this.bucketService.renameObject(bucket)
      .subscribe( data => {
        this.buckets = this.buckets.filter(u => u !== bucket);
      })
  };

}

