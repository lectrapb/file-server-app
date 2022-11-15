import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { delay } from 'rxjs/operators';
import { FileStorage } from 'src/app/model/file-storage';
import { ModalFileService } from 'src/app/services/modal-file.service';
import { FileService } from '../../services/file.service';
import Swal from 'sweetalert2';
import { PlayVideoService } from 'src/app/services/play-video.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styles: [
  ]
})
export class DashboardComponent implements OnInit, OnDestroy {

  listFiles: FileStorage[] = []; 
  loading: boolean = true;
  public fileSubs!: Subscription;
  public videoSubs!: Subscription;
  public videoName: string = 'close';

  constructor(private fileService: FileService,
              public modalFile: ModalFileService, 
              private playVideo:PlayVideoService) { }
  

  ngOnInit(): void {

    this.videoSubs = this.playVideo.notificationClose
    .subscribe(command =>{
      this.videoName = command;  
    });

    this.loadFiles();
  
    this.fileSubs = this.modalFile.newFile
    .pipe(
      delay(10)
    )
    .subscribe(fileSave =>{
       this.listFiles.push(fileSave);
    });
    
  }

  ngOnDestroy(): void {
    this.fileSubs.unsubscribe();
    this.videoSubs.unsubscribe();
  }

  loadFiles(): void{

      this.loading = true;
      this.fileService.getFiles().subscribe(
         files =>{
            this.listFiles = files;
            this.loading = false;
         }
      )
  }

  deleteFile(fileS: FileStorage):void {

    Swal.fire({
      title: 'Are you sure?',
      text: "Are you sure to delete this file",
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          
          this.fileService.deleteFile(fileS)
          .subscribe(resp => {
                 
            Swal.fire('Deleted!',
              `Your file ${fileS.name} has been deleted.`,
              'success'
              );
              this.loadFiles();
          });
        
      }
    });

  
  }


  openModal(filename:string):void {

    this.videoName = filename;   
    this.playVideo.openModal();
  }

  previewFiles(imageName: string ): any{
      let imageP: string = '';
      imageP = `http://localhost:8080/api/preview?filename=${imageName}`;
    
      return imageP;
  }

}
