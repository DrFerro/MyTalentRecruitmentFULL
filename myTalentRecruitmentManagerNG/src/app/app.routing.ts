/* 
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomeComponent} from "./components/home/home.component";
import {UrlPermission} from "./core/url.permission";


const appRoutes: Routes = [
  { path: 'home', component: HomeComponent ,canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '**', redirectTo: '/login' }
];

export const routing = RouterModule.forRoot(appRoutes); */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./components/login/login.component";
import { HomeComponent } from "./components/home/home.component";
import { CandidateComponent } from './components/candidate/candidate.component';
import { CustomersComponent } from './components/customers/customers.component';
import { RegisterComponent } from "./components/register/register.component";
import { RecruitersComponent } from "./components/recruiters/recruiters.component";
import { FieldsComponent } from "./components/fields/fields.component";
import { FieldComponent } from "./components/field/field.component";
import { UrlPermission } from "./core/url.permission";

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [UrlPermission] },
  { path: 'candidate/:id', component: CandidateComponent, canActivate: [UrlPermission] },
  { path: 'register', component: RegisterComponent, canActivate: [UrlPermission] },
  { path: 'recruiters', component: RecruitersComponent, canActivate: [UrlPermission] },
  { path: 'fields', component: FieldsComponent, canActivate: [UrlPermission] },
  { path: 'field/:id', component: FieldComponent, canActivate: [UrlPermission] },
  { path: 'customers', component: CustomersComponent, canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent },
  { path : '', component : LoginComponent},
  { path: '**', pathMatch: 'full', redirectTo: 'login' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})

export class AppRoutingModule { }