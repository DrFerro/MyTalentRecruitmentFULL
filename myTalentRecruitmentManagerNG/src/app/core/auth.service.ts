import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthService {

  //baseUrl: 'http://localhost:8080/email2sms/';

  private session: any = null;

  constructor(private http: HttpClient) {
  }

  attemptAuth(ussername: string, password: string): Observable<any> {
    const credentials = { username: ussername, password: password };
    //console.log('attempAuth ::');
    return this.http.post<any>('http://localhost:8080/token/generate-token', credentials);
  }

  register(ussername: string, password: string): Observable<any> {
    const credentials = { username: ussername, password: password };
    return this.http.post<any>('http://localhost:8080/talentmanager/signup', credentials);
  }

  isLoggedIn() {
    if (sessionStorage.getItem("AuthToken") === null) {
      return false;
     
    } else {
      return true;
    }
  }

}
