import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Response, ResponseContentType, Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { User } from '../model/user.model';
import { Customer } from '../model/customer.model';
import { Candidate } from '../model/candidate.model';
import { CandidateSeeker } from '../model/candidateSeeker';
import { Field } from '../model/field.model';
import { Speciality } from '../model/speciality.model';
import { City } from '../model/city.model';
import { StateCandidate } from '../model/stateCandidate.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class AppService {

  private userUrl = 'http://localhost:8080/talentmanager';

  //Este atributo es usado para pasar el nombre del field.  
  public fieldName: String;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl + '/users');
  }

  public deleteUser(id: number) {
    return this.http.post(this.userUrl + '/deleteUser/' +id, httpOptions);
  }

  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.userUrl + '/customers');
  }

  public getCandidates(): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(this.userUrl + '/candidates');
  }

  public getCandidate(id: string): Observable<Candidate> {
    return this.http.get<Candidate>(this.userUrl + '/candidate/' + id);
  }

  public getFields(): Observable<Field[]> {
    return this.http.get<Field[]>(this.userUrl + '/fields');
  }

  public saveField(field: Field) {
    let body = JSON.stringify(field);
    body = JSON.parse(body);
    return this.http.post(this.userUrl + '/field', body, httpOptions);
  }

  public getSpecialities(filedId: number): Observable<Speciality[]> {
    return this.http.get<Speciality[]>(this.userUrl + '/specialities/' + filedId);
  }

  public saveSpeciality(speciality: Speciality) {
    let body = JSON.stringify(speciality);
    body = JSON.parse(body);
    return this.http.post(this.userUrl + '/speciality', body, httpOptions);
  }

  public getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.userUrl + '/cities');
  }

  public getStatesCandidate(): Observable<StateCandidate[]> {
    return this.http.get<StateCandidate[]>(this.userUrl + '/statesCandidate');
  }

  public seekCandidates(candidateSeeker: CandidateSeeker) {
    let body = JSON.stringify(candidateSeeker);
    body = JSON.parse(body);
    return this.http.post(this.userUrl + '/seekCandidates', body, httpOptions);
  }

  public updateCandidate(candidate: Candidate) {
    let body = JSON.stringify(candidate);
    body = JSON.parse(body);
    return this.http.post(this.userUrl + '/updateCandidate', body, httpOptions);
  }

  public deleteCandidate(id: string) {
    return this.http.post(this.userUrl + '/deleteCandidate/' +id, httpOptions);
  }

  public downloadPDF(id: string): any {
    return this.http.get(this.userUrl + '/file/' + id, { responseType: 'blob' as 'json' }); //{  responseType: 'blob' as 'json' } para que entienda que es un blob.
  }  

}