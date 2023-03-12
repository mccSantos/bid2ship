import { ButtonModule } from 'primeng/button';
import { SharedModule } from './../shared/shared.module';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';

import { UserMainComponent } from './user-main/user-main.component';
import { UserMainPageComponent } from './user-pages/user-main-page/user-main-page.component';
import { InputTextModule } from 'primeng/inputtext';

@NgModule({
  declarations: [UserMainComponent, UserMainPageComponent],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
  ],
  exports: [UserMainComponent, UserMainPageComponent],
})
export class UserModule {}
