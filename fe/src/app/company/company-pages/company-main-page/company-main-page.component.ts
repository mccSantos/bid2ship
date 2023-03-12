import { Component, OnInit } from '@angular/core';
import { IntegrationService } from 'src/app/shared/services/integration.service';

@Component({
  selector: 'app-company-main-page',
  templateUrl: './company-main-page.component.html',
  styleUrls: ['./company-main-page.component.scss'],
})
export class CompanyMainPageComponent implements OnInit {
  packs: any[] = [];
  carriers: any[] = [];
  curPackage: any = undefined;
  displayBids = false;
  displayCreate = false;

  constructor(public integrationService: IntegrationService) {}

  ngOnInit(): void {
    this.integrationService.getCarriers().subscribe((value: any) => {
      this.carriers = value as any[];
    });
    this.integrationService.getPacks().subscribe((value: any) => {
      this.packs = (value as any[]).filter((el) => {
        return (el.bids as any[]).find((el2) => el2.carrier == 1) !== undefined;
      });
    });
  }
  seeBids(packet: any) {
    console.log(packet);
    this.displayBids = true;
    this.curPackage = packet;
  }
  seeCreate() {
    this.displayCreate = true;
  }
}
