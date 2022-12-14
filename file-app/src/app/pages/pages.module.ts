import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { PagesRoutingModule } from './pages-routing.module';
import { SharedModule } from '../shared/shared.module';

import { DashboardComponent } from './dashboard/dashboard.component';
import { PageComponent } from './page.component';
import { ComponentsModule } from '../components/components.module';



@NgModule({
  declarations: [
    DashboardComponent,
    PageComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    PagesRoutingModule,
    SharedModule,
    ComponentsModule
  ]
})
export class PagesModule { }
