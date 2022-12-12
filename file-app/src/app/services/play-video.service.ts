import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PlayVideoService {

 
  private _hideVideoModal: boolean = true;

  private _notificationClose = new EventEmitter<any>();

  constructor() { }


 get notificationClose(): EventEmitter<any>{

    return this._notificationClose;
 }


  get hideVideoModal(): boolean{
     return  this._hideVideoModal;
  }

  openModal(): void {
    this._hideVideoModal = false;
  }

  closeModal(): void {
    this._hideVideoModal = true;
  }
}
