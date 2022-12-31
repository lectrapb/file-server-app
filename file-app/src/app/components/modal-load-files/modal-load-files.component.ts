import { HttpEventType } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2'

import { environment } from 'src/environments/environment';
import { FileStorage } from 'src/app/model/file-storage';

import { FileService } from 'src/app/services/file.service';
import { ModalFileService } from 'src/app/services/modal-file.service';

@Component({
  selector: 'app-modal-load-files',
  templateUrl: './modal-load-files.component.html',
  styleUrls: ['./modal-load-files.component.css']
})
export class ModalLoadFilesComponent implements OnInit {


  public progress: number = 0;

  @ViewChild('myInputFile')
  myInputVariable!: ElementRef;

  constructor(public modalFileService: ModalFileService, 
              private fileService:FileService) { }

  ngOnInit(): void {
    this.progress = 0;
  }

  chooseFile(file: File){
    this.progress = 0;
    if(!file.size){return;}
    console.log('No emty');
    
    this.modalFileService.fileModal = file; 
  }


  closeModal():void{
     this.progress = 0;
     this.modalFileService.closeModal();
     this.myInputVariable.nativeElement.value = "";
  }

  uploadImage(){

     
    this.fileService.createFile(this.modalFileService.fileModal)
     .subscribe(event =>{
      
          if(event.type === HttpEventType.UploadProgress){
             let total = event.total == null ? 1: event.total;   
             this.progress = Math.round(((event.loaded/ total)*100)-2);

          } else if(event.type === HttpEventType.Response){
             
             let response: any = event.body;
             const d = new Date();
             const location: string =  `${environment.base_url}/download?filename=${response.name}`;
             const fileStorage: FileStorage = 
                                new FileStorage(response.id, response.name,
                                                response.type, d.toString() , location);
             this.modalFileService.newFile.emit(fileStorage); 
             this.progress = 100;
             Swal.fire('Upload file sucess', `The file ${response.name} had been upload !`, 'success')
          }
        });
    
  }

 
     
}
