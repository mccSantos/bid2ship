import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingSpinnerService {

  private loadingLevel: number = 0;
  private isLoading: boolean = false;

  constructor() { }

  public startLoading(){
    this.loadingLevel ++;
    this.isLoading = true;
  }

  public stopLoading(){
    this.loadingLevel--;
    setTimeout(()=>{
      if(this.loadingLevel === 0){
        this.isLoading = false;
      }
    }, 100  )
  }

}
