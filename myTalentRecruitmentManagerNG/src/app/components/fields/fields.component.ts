import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../../services/app.service';
import { Field } from '../../model/field.model';

@Component({
  selector: 'app-fields',
  templateUrl: './fields.component.html',
  styleUrls: ['./fields.component.css']
})
export class FieldsComponent implements OnInit {

  private fields: Field[] = [];

  private field = new Field(0, "");

  constructor(private appService: AppService,
    private router: Router) { }

  ngOnInit() {
    this.getFields();
  }

  //PASAR DATOS ENTRE COMPONENTES MEDIANTE SERVICE
  //Este método almacena en el atributo fieldName de appService
  //el nombre del field para que luego se lo pida el componente field.
  getFieldName(field: Field) {
    this.appService.fieldName = field.description;
  }

  getFields() {
    this.appService.getFields().subscribe(res => {
      for (let f in res) {
        this.fields.push(res[f]);
      };
    });
  }

  saveField() {
    this.appService.saveField(this.field).subscribe();
    window.alert("Field created");
    this.field = new Field(0, "");
    this.fields = []; 
    this.getFields();
  }

  getField(field: Field) {
/*     this.appService.deleteField().subscribe(data => {console.log(data);} );
    window.alert("Field deleted");
    this.fields = [];
    this.getFields(); */
    console.log(field);
  }

}
