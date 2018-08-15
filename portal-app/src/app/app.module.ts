import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {MatDialogModule, MatFormFieldModule, MatInputModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

import { AppComponent } from './app.component';
import { BucketComponent } from './bucket/bucket.component';
import { AppRoutingModule } from './app.routing.module';
import {BucketService} from './bucket/bucket.service';
import {HttpClientModule} from "@angular/common/http";
import {UploadBucketComponent} from './bucket/upload-bucket.component';
import { DialogComponent } from './bucket/dialog.component';
 
@NgModule({
  declarations: [
    AppComponent,
    BucketComponent,
    UploadBucketComponent,
    DialogComponent
  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule
  ],
  entryComponents: [DialogComponent],
  providers: [BucketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
