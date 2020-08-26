import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../../model/customer.model';
import { AppService } from '../../services/app.service';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers: Customer[] = [];


  constructor(private router: Router, private appService: AppService) { }

  ngOnInit() {
    this.getCustomers();
  }

  getCustomers() {
    this.appService.getCustomers().subscribe(res => {
      console.log(this.customers);
      for (let c in res) {
        this.customers.push(res[c]);
      };
    });
  }

}
