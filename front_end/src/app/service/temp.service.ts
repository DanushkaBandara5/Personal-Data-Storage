import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TempService {
  public BASE_URL="http://localhost:8080"

  constructor(public http:HttpClient) { }
}
