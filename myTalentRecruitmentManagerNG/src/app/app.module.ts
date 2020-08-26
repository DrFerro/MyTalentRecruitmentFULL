import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app.routing';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AppService } from "./services/app.service";
import { AuthService } from "./core/auth.service";
import { Interceptor } from "./core/inteceptor";
import { TokenStorage } from "./core/token.storage";
import { UrlPermission } from "./core/url.permission";
import { CandidateComponent } from './components/candidate/candidate.component';
import { FilterPipe } from './pipes/filter.pipe';
import { RecruitersComponent } from './components/recruiters/recruiters.component';
import { HeaderComponent } from './components/header/header.component';
import { FieldsComponent } from './components/fields/fields.component';
import { FieldComponent } from './components/field/field.component';
import { CustomersComponent } from './components/customers/customers.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    CandidateComponent,
    FilterPipe,
    RecruitersComponent,
    HeaderComponent,
    FieldsComponent,
    FieldComponent,
    CustomersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
  ],
  providers: [
    AngularFontAwesomeModule,
    AppService,
    AuthService,
    TokenStorage,
    TokenStorage,
    UrlPermission,
    {provide: HTTP_INTERCEPTORS,
     useClass: Interceptor,
     multi : true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
