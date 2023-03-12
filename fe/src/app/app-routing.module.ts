import { FuckSantaoComponent } from './user/user-pages/fuck-santao/fuck-santao.component';
import { UnauthenticatedMainComponent } from './unauthenticated/unauthenticated-main/unauthenticated-main.component';
import { CompanyMainComponent } from './company/company-main/company-main.component';
import { CompanyMainPageComponent } from './company/company-pages/company-main-page/company-main-page.component';
import { AdminMainComponent } from './admin/admin-main/admin-main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserMainComponent } from './user/user-main/user-main.component';
import { UserModule } from './user/user.module';
import { UserMainPageComponent } from './user/user-pages/user-main-page/user-main-page.component';
import { UnauthenticatedMainPageComponent } from './unauthenticated/unauthenticated-pages/unauthenticated-main-page/unauthenticated-main-page.component';
import { UnauthenticatedGuard } from './shared/guards/unauthenticated.guard';
import { UserAuthGuard } from './shared/guards/userauth.guard';
import { LoginPageComponent } from './authenticationPages/login-page/login-page.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent,
    canActivate: [UnauthenticatedGuard],
  },
  {
    path: '',
    component: UnauthenticatedMainComponent,
    children: [{ path: '', component: UnauthenticatedMainPageComponent }],
    canActivate: [UnauthenticatedGuard],
  },
  { path: 'admin', component: AdminMainComponent },
  {
    path: 'company',
    component: CompanyMainComponent,
    children: [
      {
        path: '',
        component: CompanyMainPageComponent,
      },
    ],
  },
  {
    path: 'user',
    component: UserMainComponent,
    canActivate: [UserAuthGuard],
    children: [
      { path: '', component: UserMainPageComponent },
      { path: 'teste', component: UserMainPageComponent },
      { path: 'teste2', component: UserMainPageComponent },
      { path: 'fucksantao', component: FuckSantaoComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
