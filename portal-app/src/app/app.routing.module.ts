import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BucketComponent } from './bucket/bucket.component';
import { UploadBucketComponent} from './bucket/upload-bucket.component';

const routes: Routes = [
  { path: 'bucket', component: BucketComponent },
  { path: 'upload', component: UploadBucketComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
