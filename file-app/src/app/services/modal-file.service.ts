import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalFileService {

  private _hideModal: boolean = true;
  private _fileModal: File = {} as File;
  private _fileModalEmpty: File = {} as File;

  public newFile: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  get hideModal(): boolean{
    return this._hideModal;
  }

  get fileModal(): File{
    return this._fileModal;
  }

  set fileModal(file:File){
    this._fileModal = file;
  }

  openModal(): void{    
     this._hideModal = false;
  }

  closeModal(): void{
    this._hideModal = true;
    this._fileModal = this._fileModalEmpty;
  }
}
