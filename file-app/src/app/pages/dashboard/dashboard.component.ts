import { Component, OnInit } from '@angular/core';
import { FileStorage } from 'src/app/model/file-storage';
import { environment } from 'src/environments/environment';
import { FileService } from '../../services/file.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styles: [
  ]
})
export class DashboardComponent implements OnInit {

  listFiles: FileStorage[] = []; 
  loading: boolean = true;

  constructor(private fileService: FileService) { }

  ngOnInit(): void {
    this.loadFiles();
  }

  loadFiles(): void{

      this.loading = true;
      this.fileService.getFiles().subscribe(
         files =>{
            this.listFiles = files; 

            this.listFiles = this.listFiles.map(fileInitial =>{
              let url = `${environment.base_url}/download?filename=${fileInitial.name}`;  
              fileInitial.content = url; 
              return fileInitial;
            });

            this.loading = false;
         }
      )
  }

}
