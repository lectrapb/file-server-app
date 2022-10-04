import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

   banners: any [] = [
     
    {
      order: '0',
      slide: 'Slide 1', 
      title: 'FREE SIZE ', 
      desc:  'Manage your file size! ', 
      srcImage: '../../../assets/c-images/FileServerC1.jpg'
     },
     {
      order: '1',
      slide: 'Slide 2',
      title: 'ANY RESTRICTION! ', 
      desc:  'Manage your own content! ', 
      srcImage: '../../../assets/c-images/FileServerC2.jpg '
     },
     {
      order: '2',
      slide: 'Slide 3',
      title: 'SHARE FILES! ', 
      desc:  'Share your files with peopl ', 
      srcImage: '../../../assets/c-images/FileServerC3.jpg '
     }, 

  ]

  constructor() { }

  ngOnInit(): void {
 
    
  }

}
