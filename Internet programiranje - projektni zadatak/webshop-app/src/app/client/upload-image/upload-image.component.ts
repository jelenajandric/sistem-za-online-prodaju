import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UploadImageService } from '../services/upload-image.service';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit{
  public form: FormGroup = new FormGroup({})

  constructor(private uploadImageService: UploadImageService, 
              private loginService: LoginService,
              private router: Router) {}
  
  uploadedImage!: File;
  dbImage: any;
  successResponse!: string;
  image: any;
  private readonly imageType : string = 'data:image/jpg;base64,'; 

  ngOnInit(): void {
    if(!this.loginService.isLoggedIn() && this.loginService.getLoggedInUsername()===null) {
      this.router.navigate(["/clients/login"])
    } else {
      this.viewImage(); 
    }
  }

  public onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
  }

  imageUploadAction() {
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);
    this.uploadImageService.uploadImage(imageFormData).subscribe((response) => {
      if (response.status === 200) {
        this.successResponse = "Uspješno dodata slika."
        this.viewImage();
      }
    }
    );
  }

  viewImage() {
    this.uploadImageService.getImage()
    .subscribe((data) => {
      if(data.content) {
        this.dbImage = this.imageType + data.content;
      }
    })
  }

  deleteImage() {
    this.uploadImageService.deleteImage().subscribe(data => {
      this.dbImage="";
    })
  }

  nextStep() {
    if(this.loginService.isLoggedIn()) {
      this.router.navigate(["home"])
    } else {
      this.router.navigate(["clients/activate-account"])
    }
  }

  // private checkConditions() {
  //   if(!this.loginService.isLoggedIn() && this.loginService.getLoggedInUsername()===null) {
  //       this.router.navigate(["/clients/login"])
  //   }
  // }
}
