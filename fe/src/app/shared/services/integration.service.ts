import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IntegrationService {

constructor(private http: HttpClient) { }
  public login(username: string, password: string){
    return this.http.post("http://localhost:8080/login", {username, password},  {  responseType: 'text'  });
  }

  public getBids(){
    return this.http.get("http://localhost:8080/bids")
  }

  public getCarriers(){
    return this.http.get("http://localhost:8080/carriers")
  }

  public getPacks() {
    return this.http.get("http://localhost:8080/packs")
  }

}
