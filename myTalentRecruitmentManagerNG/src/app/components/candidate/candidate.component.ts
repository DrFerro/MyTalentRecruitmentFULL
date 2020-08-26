import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ViewChild, ElementRef } from '@angular/core';
import { Candidate } from '../../model/candidate.model';
import { StateCandidate } from '../../model/stateCandidate.model';
import { AppService } from '../../services/app.service';

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {

  //Asigno esta etiqueta para cerrar el modal de eliminar. ViewChild obtiene elementos de DOM.
  @ViewChild('closeBtn') closeBtn: ElementRef;

  id: string;

  candidate: Candidate = {
    id: 0,
    name: "",
    last_name1: "",
    last_name2: "",
    email: "",
    phone: "",
    field: "",
    speciality: "",
    city: "",
    observations: "",
    cv: null,
    date: "",
    customer: "",
/*     viewed: null,
    inTest: null,
    worker: null,
    paid: null */
    stateCandidate: 0
  }

  public statesCandidate: StateCandidate[] = []; 

  constructor(private appService: AppService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

    this.activatedRoute.params.subscribe(parametros => {
      this.id = parametros['id']
      if (this.id !== "nuevo") {
        this.appService.getCandidate(this.id)
          .subscribe(candidate => { this.candidate = candidate })
      }
    });
  }

  ngOnInit() {
    this.getStatesCandidate();
   }

  getStatesCandidate() {
    this.appService.getStatesCandidate().subscribe(res => {
      for (let s in res) {
        this.statesCandidate.push(res[s]);
      };
    });
  }

  updateCandidate() {
    this.appService.updateCandidate(this.candidate)
      .subscribe(data => {
        if (data == null) {
          alert("Candidato modificado.");
          this.router.navigate(['/home']);
        }
      },
        error => console.error(error));
  }

  deleteCandidate() {
    this.appService.deleteCandidate(this.id)
      .subscribe(data => {
        //console.log(data)
      },
        error => console.error(error));

    //Orden para cerrar el modal
    this.closeBtn.nativeElement.click();
    this.router.navigate(['/home']);
  }

  downloadFile() {
    this.appService.downloadPDF(this.id).subscribe((res) => {
      var file = new Blob([res], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }

}
