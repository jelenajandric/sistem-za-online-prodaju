import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'clients',
    loadChildren: () => import('./client/client.module').then(mod => mod.ClientModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(mod => mod.HomeModule)
  },
  {
    path: 'products',
    loadChildren: () => import('./product/product.module').then(mod => mod.ProductModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],              
  exports: [RouterModule]
})
export class AppRoutingModule { }
