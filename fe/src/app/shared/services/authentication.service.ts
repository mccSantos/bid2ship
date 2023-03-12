import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private token: string = 'a';

  constructor() {}

  public isAuthenticated(): boolean {
    this.token = this.getToken();
    return this.token !== '';
  }

  private getToken(){
    return localStorage.getItem("token") || "";
  }

  public setToken(token: string): void {
    this.token = token;
    localStorage.setItem("token", token);
  }

  public removeToken(): void {
    this.setToken("");
  }

  public getUser(): any {
    this.token = this.getToken();
    if(this.token === ""){
      return null;
    }
    return this.getDecodedAccessToken(this.token);
  }

  public getCurrentToken(): string{
    return this.token;
  }

  private getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }
}
