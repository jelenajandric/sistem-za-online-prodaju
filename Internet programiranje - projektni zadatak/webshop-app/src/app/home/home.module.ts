import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ShowAllProductsComponent } from './show-all-products/show-all-products.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WebshopToolbarComponent } from './webshop-toolbar/webshop-toolbar.component';



@NgModule({
  declarations: [
    SideMenuComponent,
    ShowAllProductsComponent,
    WebshopToolbarComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    AppMaterialModule,
    CommonModule,
    MatPaginatorModule,
    ReactiveFormsModule,
  ],
  exports: [
    ShowAllProductsComponent,
    WebshopToolbarComponent
  ]
})
export class HomeModule { }
