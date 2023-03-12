import { Component } from '@angular/core';
import { IntegrationService } from 'src/app/shared/services/integration.service';

@Component({
  selector: 'app-unauthenticated-main-page',
  templateUrl: './unauthenticated-main-page.component.html',
  styleUrls: ['./unauthenticated-main-page.component.scss']
})
export class UnauthenticatedMainPageComponent {

  carriers:any[] = [];
  packs: any[] = [];

  constructor(public integrationService: IntegrationService) { }

  ngOnInit(): void {
    this.integrationService.getCarriers().subscribe((value)=>{this.carriers = value as any[]});
    this.integrationService.getPacks().subscribe((value)=>{this.packs = (value as any[])})

  }

}
