import {Injectable} from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Bucket } from '../models/bucket.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BucketService {

  constructor(private http:HttpClient) {}

  private bucketUrl = 'http://localhost:8080/api';

  public getObjects(){
     return this.http.get<Bucket[]>(this.bucketUrl);
  }

  public renameObject(bucket, key) {
    return this.http.put(this.bucketUrl + "/key?filename=" + key, bucket);
  }

  public uploadFile(file: File): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    formdata.append('file', file);
 
    const req = new HttpRequest('POST', this.bucketUrl + "/upload", formdata, {
      reportProgress: true,
      responseType: 'text'
    });
 
    return this.http.request(req);
  }
  // public uploadFile(bucket) {
  //   return this.http.post<Bucket>(this.bucketUrl + "/upload", bucket.key);
  // }

}
