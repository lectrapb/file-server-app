import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FileStorage } from '../model/file-storage';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private urlBase: string = environment.base_url;  

  constructor(private http:HttpClient) { }

  getFiles(): Observable<FileStorage[]>{
   
      const url = `${this.urlBase}/listfiles`;
      return this.http.get(url)
               .pipe(
                 map( (response: any) => {
                       let files : FileStorage[] = [];
                       if(response.length){
                          files = response as FileStorage[];
                          files.forEach(file  => {
                            file.content = `${environment.base_url}/download?filename=${file.name}`
                          }); 
                       }                     
                       return files;
                 })
               );
  }

  deleteFile(file: FileStorage){
    const url = `${environment.base_url}/delete?name-file=${file.name}`;
    return this.http.delete(url);   
  }

  createFile(fileData: File): Observable<HttpEvent<{}>>{

      const url = `${environment.base_url}/upload`;
      const formData  = new FormData();
      formData.append('file', fileData );

      const req = new HttpRequest('POST', url, formData, {
        reportProgress: true
      });
      return this.http.request(req);
   }


}
