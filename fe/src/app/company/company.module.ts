import { SharedModule } from './../shared/shared.module';
import { RouterModule } from '@angular/router';
import { CompanyMainComponent } from './company-main/company-main.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyMainPageComponent } from './company-pages/company-main-page/company-main-page.component';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
  ],
  declarations: [CompanyMainComponent, CompanyMainPageComponent],
})
export class CompanyModule {}
