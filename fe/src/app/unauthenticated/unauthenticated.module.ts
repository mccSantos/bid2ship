import { TableModule } from 'primeng/table';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UnauthenticatedMainPageComponent } from './unauthenticated-pages/unauthenticated-main-page/unauthenticated-main-page.component';
import { UnauthenticatedMainComponent } from './unauthenticated-main/unauthenticated-main.component'
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [UnauthenticatedMainPageComponent, UnauthenticatedMainComponent],
  imports: [CommonModule, RouterModule, SharedModule, TableModule],
})
export class UnauthenticatedModule {}
