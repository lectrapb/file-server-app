import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalLoadFilesComponent } from './modal-load-files/modal-load-files.component';

import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering'


import { VideoPlayerComponent } from './video-player/video-player.component';



@NgModule({
  declarations: [
    ModalLoadFilesComponent,
    VideoPlayerComponent
  ],
  exports: [
    ModalLoadFilesComponent, 
    VideoPlayerComponent
  ],
  imports: [
    CommonModule,
    VgCoreModule, 
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ]
})
export class ComponentsModule { }
