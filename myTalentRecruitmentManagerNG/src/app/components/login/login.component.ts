import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../../core/auth.service';
import {TokenStorage} from '../../core/token.storage';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage) {
  }

  username: string;
  password: string;

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['/home']);
      }
    );
  }
  
  ngOnInit() {
  }

}
