import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { CarouselComponent } from './carousel/carousel.component';
import { FooterComponent } from './footer/footer.component';



@NgModule({
  declarations: [
    NavbarComponent,
    CarouselComponent,
    FooterComponent
  ],
  exports: [
    NavbarComponent, 
    CarouselComponent, 
    FooterComponent
  ],   
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
