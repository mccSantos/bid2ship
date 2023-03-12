import { IntegrationService } from './../../../shared/services/integration.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-main-page',
  templateUrl: './user-main-page.component.html',
  styleUrls: ['./user-main-page.component.scss'],
})
export class UserMainPageComponent implements OnInit {
  carriers: any[] = [];

  packs: any[] = [];

  displayCreate = false;

  displayBids = false;

  curPackage = undefined;

  constructor(public integrationService: IntegrationService) {}

  ngOnInit(): void {
    this.integrationService.getCarriers().subscribe((value) => {
      this.carriers = value as any[];
    });
    this.integrationService.getPacks().subscribe((value) => {
      this.packs = (value as any[]).filter((el) => {
        return el.owner == 4;
      });
    });
    this.integrationService.getPacks().subscribe((value) => {
      var result = false;
      var packIds:any[]  = [];
      (value as any[]).forEach((el,id) => {
        if(el.status !== "open"){
          if(el.bids.length == 0){
            packIds.push(el);
            result = true;
          }
        }


      });
      console.log(result);
      console.log(packIds)
    });
  }

  showCreate() {
    this.displayCreate = true;
  }

  seeBids(packet: any) {
    console.log(packet);
    this.displayBids = true;
    this.curPackage = packet;
  }
}
