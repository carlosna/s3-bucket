import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Bucket } from '../models/bucket.model';
import { BucketService } from './bucket.service';

@Component({
  selector: 'app-bucket',
  templateUrl: './bucket.component.html',
  styles: []
})
export class BucketComponent implements OnInit {
  
  p: number = 1;
  buckets: Bucket[];

  constructor(private router: Router, private bucketService: BucketService) {

  }

  ngOnInit() {
    this.bucketService.getObjects()
      .subscribe( data => {
        this.buckets = data;
      });
  };

  renameObject(object: Object): void {
    this.bucketService.renameObject(object)
      .subscribe( data => {
        this.buckets = this.buckets.filter(u => u !== object);
      })
  };

}

