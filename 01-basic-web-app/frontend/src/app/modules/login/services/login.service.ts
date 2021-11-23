import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // TODO user environment variable to distinguish different hosts
  private baseUrl = "http://localhost:8080/v1/login";

  constructor(private _httpClient: HttpClient) { }

  public login(userId: string, pass: string): Observable<any> {
    let credentials = btoa(`${userId}:${pass}`);
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this._httpClient.get(`${this.baseUrl}?ignoreThis=${Date.now()}`, httpOptions).pipe();
  }
}
