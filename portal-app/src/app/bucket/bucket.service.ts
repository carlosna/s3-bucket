import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { Bucket } from '../models/bucket.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BucketService {

  constructor(private http:HttpClient) {}

  private bucketUrl = 'http://localhost:8080/api';
//	private bucketUrl = '/api';

//  public getObjects() {
//    return this.http.get<Object[]>(this.bucketUrl);
//  }

  public getObjects(){
     return this.http.get<Bucket[]>(this.bucketUrl);
  }

  public renameObject(object) {
    return this.http.put(this.bucketUrl, object.key);
  }

  public uploadFile(object) {
    return this.http.post<Bucket>(this.bucketUrl, object);
  }

}
