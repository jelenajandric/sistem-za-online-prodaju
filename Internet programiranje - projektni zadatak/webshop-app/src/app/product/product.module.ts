import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { AddNewProductComponent } from './add-new-product/add-new-product.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';
import { AddAttributeValuesComponent } from './add-attribute-values/add-attribute-values.component';
import { UploadImagesComponent } from './upload-images/upload-images.component';
import { ShowProductComponent } from './show-product/show-product.component';
import { BuyProductComponent } from './buy-product/buy-product.component';
import { HomeModule } from "../home/home.module";



@NgModule({
    declarations: [
        AddNewProductComponent,
        AddAttributeValuesComponent,
        UploadImagesComponent,
        ShowProductComponent,
        BuyProductComponent,
    ],
    imports: [
        CommonModule,
        ProductRoutingModule,
        AppMaterialModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
        HomeModule
    ]
})
export class ProductModule { }
