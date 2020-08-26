import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AppService } from '../../services/app.service';
import { Field } from '../../model/field.model';
import { Speciality } from '../../model/speciality.model';

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.css']
})
export class FieldComponent implements OnInit {

  private fieldId: number;
  private fieldName: String = "";

  private specialityId: number;
  private specialityName: String = "";

  private specialities: Speciality[];

  private speciality = new Speciality(0, "", 0);

  constructor(private appService: AppService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

    this.fieldName = this.appService.fieldName;

    this.activatedRoute.params.subscribe(parametros => {
      this.fieldId = parametros['id']
      if (this.fieldId !== 0) {
        this.getSpecialities();
      } 
    });
  }
              
  ngOnInit() {}

  getSpecialities(){
    this.appService.getSpecialities(this.fieldId).subscribe(res => {
      this.specialities = [];
      for (let s in res){
         this.specialities.push(res[s]);
      };
    });
  }

  saveSpeciality() {
    this.speciality.fieldId = this.fieldId;
    this.appService.saveSpeciality(this.speciality).subscribe();
    window.alert("Speciality created");
    this.speciality = new Speciality(0, "", 0); 
    this.getSpecialities();
  }

  getSpeciality(speciality: Speciality) {
    this.specialityId = speciality.id;
    this.specialityName = speciality.description;
  }

  deleteSpeciality(){

  }

}


