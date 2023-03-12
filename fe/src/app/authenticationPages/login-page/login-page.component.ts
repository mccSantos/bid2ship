import { IntegrationService } from './../../shared/services/integration.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent {

  constructor(private authService: AuthenticationService, private router: Router,private integrationService: IntegrationService){}

  public async login(){

    this.integrationService.login("tiago", "tiago").subscribe((value)=>{


      this.authService.setToken(value);
      this.router.navigateByUrl("/user")

    })


  }

}
