import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { MenuModule } from 'primeng/menu';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    AvatarModule,
    AvatarGroupModule,
    MenuModule,
  ],
  declarations: [HeaderComponent],
  exports: [HeaderComponent],
})
export class SharedModule {}
