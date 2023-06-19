import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { UploadImagesService } from '../services/upload-images.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { GetAllImagesResponse } from 'src/app/model/product/get-all-images-response.model';
import { LoginService } from 'src/app/client/services/login.service';
import { AddNewProductService } from '../services/add-new-product.service';


@Component({
  selector: 'app-upload-images',
  templateUrl: './upload-images.component.html',
  styleUrls: ['./upload-images.component.css']
})
export class UploadImagesComponent implements OnInit {
  public form: FormGroup = new FormGroup({})

  uploadedImage!: File;
  dbImage: any;
  postResponse: any;
  successResponse!: string;
  image: any;
  private readonly imageType : string = 'data:image/jpg;base64,'; 
  allImagesWithId: Array<GetAllImagesResponse> | undefined;

  constructor(private uploadImagesService: UploadImagesService,
              private addNewProductService: AddNewProductService, 
              private router: Router,
              private loginService: LoginService) {}

  ngOnInit(): void {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    } else if(!this.addNewProductService.getId()){
      this.router.navigate(["/products/add-new"]);
    } else {
      this.viewImages();
    }
  }

  endAddingProduct() {
    this.addNewProductService.endAddingProcess();
    this.router.navigate(["home"])
  }

  public onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
  }

  imageUploadAction() {
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);
    this.uploadImagesService.uploadImage(imageFormData).subscribe((response) => {
      if (response.status === 200) {
        this.successResponse = "Uspjesno dodata slika."
        this.viewImages();
      } else {
        this.successResponse = "Nije dodata slika."
        this.viewImages();
      }
    }
    );
  }

  viewImages() {
    this.uploadImagesService.getAllImages()
    .subscribe((data) => {   
      this.allImagesWithId = data;
      this.allImagesWithId.forEach(imageWithId => {
        imageWithId.image = this.imageType + imageWithId.image;
      })
    })
  }

  deleteImage(id: any) {
    this.uploadImagesService.deleteImage(id).subscribe(data => {
      this.viewImages();
    })
  }
}
