import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Candidate } from '../../model/Candidate';
import { User } from '../../model/User';
import { Suggestion } from '../../model/Suggestion';
import { Field } from '../../model/Field';
import { Speciality } from '../../model/Speciality';
import { City } from '../../model/city';

import { Router } from "@angular/router";
import { RecaptchaModule } from 'ng-recaptcha';
import { RecaptchaFormsModule } from 'ng-recaptcha/forms';

import { SuggestionService } from '../../services/suggestion.service';
//import { forEach } from '@angular/router/src/utils/collection';

export interface FormModel {
  captcha?: string;
}

@Component({
  selector: 'app-suggestion',
  templateUrl: './suggestion.component.html',
  styleUrls: ['./suggestion.component.css']
})
export class SuggestionComponent implements OnInit {

  public candidate = new Candidate("", "", "", "", "", 0, 0, 0, "");
  public user = new User("", "", "", "", "", false, false);
  public suggestion = new Suggestion(this.candidate, this.user);

  public fields: Field[] = [];
  public specialities: Speciality[] = [];

  public cities: City[] = [];

  public selectedFiles: FileList;
  public currentFileUpload: File;

  public confirmCV: boolean = false;
  public confirmConditions: boolean = false;
  
  //Form para el recaptcha
  public formModel: FormModel = {};
  public captcha?: string;

  //progress: { percentage: number } = { percentage: 0 }

  constructor(private suggestionService: SuggestionService, public router: Router) { }

  ngOnInit() {
    this.getFields();
    this.getCities();
  }

  createSuggesion(myForm: NgForm) {
    //console.log(myForm);
    //console.log("Valor", myForm.value);
    console.log("Suggestion", this.suggestion);

    this.suggestionService.createSuggestion(this.suggestion, this.currentFileUpload).subscribe(data => {
      //console.log(data);
    });

    this.suggestionService.sendFile(this.candidate.email, this.currentFileUpload).subscribe();

    alert("Muchas gracias por tu confianza. Te mantedremos informado del seguimiento del candidato.");
    this.router.navigate(['/home']);
  }

  getFields() {
    this.suggestionService.getFields().subscribe(res => {
      for (let f in res) {
        this.fields.push(res[f]);
      };
    });
  }

  getSpecialities() {
    this.suggestionService.getSpecialities(this.candidate.fieldId).subscribe(res => {
      this.specialities = [];
      for (let s in res) {
        this.specialities.push(res[s]);
      };
    });
  }

  getCities() {
    this.suggestionService.getCities().subscribe(res => {
      for (let c in res) {
        this.cities.push(res[c]);
      };
    });
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.currentFileUpload = this.selectedFiles.item(0);
    console.log(this.currentFileUpload);
  }

}
