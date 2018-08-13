import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
//import { Bucket } from '../models/bucket.model';
import { BucketService } from './bucket.service';

@Component({
  templateUrl: './upload-bucket.component.html'
})
export class UploadBucketComponent {

  //bucket: Bucket = new Bucket();
  selectedFiles: FileList
  currentFileUpload: File
  progress: { percentage: number } = { percentage: 0 }

  constructor(private router: Router, private bucketService: BucketService) { }
  
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
 
  upload() {
    this.progress.percentage = 0;
 
    this.currentFileUpload = this.selectedFiles.item(0)
    this.bucketService.uploadFile(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })
 
    this.selectedFiles = undefined
  }
//  uploadBucket(): void {
//    this.bucketService.uploadFile(this.bucket)
//        .subscribe( data => {
          // alert("Object created successfully.");
        // });
  // };
}
