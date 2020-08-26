import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Router } from '@angular/router';
import 'rxjs/Rx';

import { Suggestion } from '../model/Suggestion';
import { Field } from '../model/Field';

@Injectable()
export class SuggestionService {

    //Para desplegar independientex del back habrá que cambiar la dirección añediendo la ubicación de la API.
    //private URLServerSuggestion = 'http://localhost:8080/myTalentRecruitment/suggestion/';
    //private URLServerSuggestion = 'http://localhost:8080/suggestion/';
    private URLServerSuggestion = 'http://localhost:9080/suggestion/';
    private newSuggestionURL = this.URLServerSuggestion + 'suggestion';

    private newFileURL = this.URLServerSuggestion + 'file';

    private getFieldsURL = this.URLServerSuggestion + 'fields';
    private getSpecialitiesURL = this.URLServerSuggestion + 'specialities';

    private getCitiesURL = this.URLServerSuggestion + 'cities';

    field = new Field(0);

    constructor(private http: Http,
        private router: Router,
        private httpClient: HttpClient) { }

/*     createSuggestion(suggestion: Suggestion) {
        let body = JSON.stringify(suggestion);
        body = JSON.parse(body);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.post(this.newSuggestionURL, body, options)
            .map(res => {

                if (res.json() == false) {
                    alert("Muchas gracias pero ya contamos con este candidato.");
                    this.router.navigate(['/home']);
                }

                return res.json();
            })
    } */

    createSuggestion(suggestion: Suggestion, file: File) {
        let body = JSON.stringify(suggestion);
        body = JSON.parse(body);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.post(this.newSuggestionURL, body, options)
            .map(res => {

                if (res.json() == false) {
                    alert("Muchas gracias pero ya contamos con este candidato.");
                    this.router.navigate(['/home']);

                } /* else {
                    this.sendFile(suggestion.candidate.email, file);
                } */

                return res.json();
            })
    }

    sendFile(email: string, file: File) {

        let formdata: FormData = new FormData();
        formdata.append('file', file);
        formdata.append('email', email);

        const req = new HttpRequest('POST', this.newFileURL, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.httpClient.request(req);
    }

    getFields() {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.get(this.getFieldsURL, options).map(res => res.json());
    }

    getSpecialities(fieldId: number) {
        this.field.id = fieldId;
        let body = JSON.stringify(this.field);
        body = JSON.parse(body);

        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.post(this.getSpecialitiesURL, body, options).map(res => res.json());
    }

    getCities() {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.get(this.getCitiesURL, options).map(res => res.json());
    }

}