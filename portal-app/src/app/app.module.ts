import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';

import { AppComponent } from './app.component';
import { BucketComponent } from './bucket/bucket.component';
import { AppRoutingModule } from './app.routing.module';
import {BucketService} from './bucket/bucket.service';
import {HttpClientModule} from "@angular/common/http";
import {UploadBucketComponent} from './bucket/upload-bucket.component';

@NgModule({
  declarations: [
    AppComponent,
    BucketComponent,
    UploadBucketComponent
  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BucketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
