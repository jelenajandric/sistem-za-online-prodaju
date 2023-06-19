import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAttributeValuesComponent } from './add-attribute-values/add-attribute-values.component';
import { AddNewProductComponent } from './add-new-product/add-new-product.component';
import { ShowProductComponent } from './show-product/show-product.component';
import { UploadImagesComponent } from './upload-images/upload-images.component';
import { BuyProductComponent } from './buy-product/buy-product.component';

const routes: Routes = [
  {
    path: '',
    component: AddNewProductComponent,
    children: [
      { path: 'add-new', component: AddNewProductComponent },
      { path: 'update/:id', component: AddNewProductComponent }
    ]
  },
  // {
  //   path: 'add-new',
  //   component: AddNewProductComponent
  // }, 
  {
    path: '',
    component: AddAttributeValuesComponent,
    children: [
      { path: 'add-attribute-values', component: AddAttributeValuesComponent },
      { path: 'update/add-attribute-values/:id', component: AddAttributeValuesComponent }
    ]
  },
  // {
  //   path: 'add-attribute-values',
  //   component: AddAttributeValuesComponent
  // }, 
  {
    path: 'upload-images',
    component: UploadImagesComponent
  },
  {
    path: ':id',
    component: ShowProductComponent
  },
  {
    path: 'buying/:id',
    component: BuyProductComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
