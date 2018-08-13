import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Bucket } from '../models/bucket.model';
import { BucketService } from './bucket.service';

@Component({
  templateUrl: './upload-bucket.component.html'
})
export class UploadBucketComponent {

  bucket: Bucket = new Bucket();

  constructor(private router: Router, private bucketService: BucketService) {

  }

  uploadBucket(): void {
    this.bucketService.uploadFile(this.bucket)
        .subscribe( data => {
          alert("Object created successfully.");
        });

  };

}
