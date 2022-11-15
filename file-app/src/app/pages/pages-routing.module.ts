import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './page.component';



const routes: Routes = [
  {path:'dashboard', component:PageComponent}, 
  {path: '**', pathMatch:'full', redirectTo:'/dashboard'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
