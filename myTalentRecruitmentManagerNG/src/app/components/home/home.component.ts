import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../model/user.model';
import { Candidate } from '../../model/candidate.model';
import { CandidateSeeker } from '../../model/candidateSeeker';
import { Field } from '../../model/field.model';
import { Speciality } from '../../model/speciality.model';
import { City } from '../../model/city.model';
import { StateCandidate } from '../../model/stateCandidate.model';
import { AppService } from '../../services/app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

/*   public users : User[] = [];
 */  userName: string;

  public candidates: Candidate[] = [];

  public candidateSeeker = new CandidateSeeker(0, 0, 0, 0);

  public fields: Field[] = [];
  public specialities: Speciality[] = [];

  public cities: City[] = [];

  public statesCandidate: StateCandidate[] = []; 

  constructor(private router: Router, private appService: AppService) {
  }

  ngOnInit(): void {
    this.getFields();
    this.getCities();
    this.getStatesCandidate();
    this.seekCandidates();
  }

  getFields() {
    this.appService.getFields().subscribe(res => {
      for (let f in res) {
        this.fields.push(res[f]);
      };
    });
  }

  getSpecialities() {
    this.appService.getSpecialities(this.candidateSeeker.fieldId).subscribe(res => {
      this.specialities = [];
      for (let s in res) {
        this.specialities.push(res[s]);
      };
    });
  }

  getCities() {
    this.appService.getCities().subscribe(res => {
      for (let c in res) {
        this.cities.push(res[c]);
      };
    });
  }

  getStatesCandidate() {
    this.appService.getStatesCandidate().subscribe(res => {
      for (let s in res) {
        this.statesCandidate.push(res[s]);
      };
    });
  }

  getCandidates() {
    this.candidates = [];
    this.appService.getCandidates().subscribe(res => {
      for (let c in res) {
        this.candidates.push(res[c]);
      };
    });
  }

  seekCandidates() {
    this.appService.seekCandidates(this.candidateSeeker).subscribe(res => {
      this.candidates = [];
      for (let c in res) {
        this.candidates.push(res[c]);
      };
    });

    /*     if(this.candidateSeeker.fieldId == 0 && this.candidateSeeker.specialityId == 0 && this.candidateSeeker.cityId == 0 && this.candidateSeeker.viewed == null){
          this.candidates = [];
          this.getCandidates();
    
        } else {
          console.log(this.candidateSeeker);
          this.appService.seekCandidates(this.candidateSeeker).subscribe(res => {
            this.candidates = [];
            this.candidateSeeker = new CandidateSeeker(0, 0, 0, null, null, null, null);
            for (let c in res){
               this.candidates.push(res[c]);
            };
          });
          this.specialities = [];
        } */
  }

}
