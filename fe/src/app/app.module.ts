import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserModule } from './user/user.module';
import { UnauthenticatedModule } from './unauthenticated/unauthenticated.module';
import { HeaderComponent } from './shared/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginPageComponent } from './authenticationPages/login-page/login-page.component';
import { ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { InputTextModule } from 'primeng/inputtext';
import { CompanyModule } from './company/company.module';

@NgModule({
  declarations: [AppComponent, LoginPageComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UserModule,
    UnauthenticatedModule,
    BrowserModule,
    BrowserAnimationsModule,

    ButtonModule,
    HttpClientModule,
    InputTextModule,
    CompanyModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
