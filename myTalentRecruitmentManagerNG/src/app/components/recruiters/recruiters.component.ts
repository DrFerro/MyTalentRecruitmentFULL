import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ViewChild, ElementRef } from '@angular/core';
import { AppService } from '../../services/app.service';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-recruiters',
  templateUrl: './recruiters.component.html',
  styleUrls: ['./recruiters.component.css']
})
export class RecruitersComponent implements OnInit {

  @ViewChild('closeBtn') closeBtn: ElementRef;

  private users : User[] = [];
  private idRecruiter: number;
  private nameRecruiter: string;

  constructor(private appService: AppService,
              private router: Router) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers(){
    this.appService.getUsers().subscribe(
      data => {this.users = data;}
    );
  }

  deleteUser() {
    this.appService.deleteUser(this.idRecruiter)
      .subscribe(data => {
        //console.log(data)
      },
        error => console.error(error));

    //Orden para cerrar el modal
    this.closeBtn.nativeElement.click();
    this.router.navigate(['/home']);
  }

  getUser(recruiter: User) {
    this.idRecruiter = recruiter.id;
    this.nameRecruiter = recruiter.username;
  }

}
