import { Component, Input, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthenticationService } from '../services/authentication.service';

interface IHeaderItem {
  text: string;
  href: string;
  condition?: () => boolean;
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  @Input() items: IHeaderItem[] = [];

  menuItems : MenuItem[] = [];
  menuItemsAuth : MenuItem[] = [];

  constructor(private router: Router, public authService: AuthenticationService) {}

  ngOnInit(): void {
    this.menuItems =  [
      {
        label: 'Sem sessão iniciada, por favor registe-se ou entre na sua conta para continuar.',
        items: [
          { label: 'Entrar', icon: 'pi pi-fw pi-plus', command: ()=>{this.router.navigateByUrl("/login")} },
          { label: 'Registrar', icon: 'pi pi-fw pi-download' },
        ],
      },

    ];

    this.menuItemsAuth =  [
      {
        label: 'Bem-vindo ' + this.getUsername(),
        items: [
          {label: "Sair", command: ()=>{this.authService.removeToken(); this.router.navigate(["/"])}}
        ],
      },

    ];
  }

  getUsername(){
    if(!this.authService.getUser()){
      return ""
    }
    return this.authService.getUser().name;
  }
}
