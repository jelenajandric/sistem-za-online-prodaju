import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAllImagesResponse } from 'src/app/model/product/get-all-images-response.model';
import { AddNewProductService } from './add-new-product.service';

@Injectable({
  providedIn: 'root'
})
export class UploadImagesService {

  constructor(private httpClient: HttpClient,
              private addNewProductService: AddNewProductService) { }

  uploadImage(imageFormData: FormData) : Observable<HttpResponse<Object>> {
    return this.httpClient.post('http://localhost:8080/product-images/add-new/' + this.addNewProductService.getId(), 
    imageFormData, { observe: 'response' })
  }

  getAllImages() : Observable<Array<GetAllImagesResponse>> {
    return this.httpClient
    .get<Array<GetAllImagesResponse>>('http://localhost:8080/product-images/' + this.addNewProductService.getId()) 
  }

  deleteImage(id: any) :Observable<any> {
    return this.httpClient.delete('http://localhost:8080/product-images/' + id);
  }


}
