import { Component, Input, OnInit } from '@angular/core';
import { PlayVideoService } from 'src/app/services/play-video.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {

  @Input() nameVideo!: string;

 
  constructor(public  playVideoService: PlayVideoService) { }

  

  ngOnInit(): void {}

  closeModal(): void {
     let videoClose: string = 'close';
     this.playVideoService.notificationClose.emit(videoClose);
     this.playVideoService.closeModal();
  }

  getUrlVideo():string{
    

    return `${environment.base_url}/video/${this.nameVideo}`;
 
  }


}
