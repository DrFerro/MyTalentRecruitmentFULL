import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../../core/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) {}

  username: string;
  password: string;

  ngOnInit() {}

  register(): void {
    this.authService.register(this.username, this.password).subscribe(
      data => {
        //this.token.saveToken(data.token);
        window.alert("Account created");
        this.router.navigate(['/home']);
      }
    );
  }

}
