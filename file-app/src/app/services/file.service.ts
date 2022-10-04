import { HttpClient } from '@angular/common/http';
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
   
      const url = `${this.urlBase}/listcontent`;
      return this.http.get(url).pipe(
                map(response => response as FileStorage[])
               );
  }
}
