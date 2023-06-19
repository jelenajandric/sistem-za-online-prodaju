import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/client/services/login.service';
import { AddNewProductRequest } from 'src/app/model/product/add-new-product-request.model';
import { AddNewProductResponse } from 'src/app/model/product/add-new-product-response.model';
import { GetProductForUpdate } from 'src/app/model/product/get-product-for-update.model';
import { UpdateProductResponse } from 'src/app/model/product/update-product-response.model';

@Injectable({
  providedIn: 'root'
})
export class AddNewProductService {
  response: AddNewProductRequest | UpdateProductResponse | any;
  attributes : Array<string> | Map<string,string> | any;
  id: number | any;
  isAddMode: boolean = true;

  constructor(private httpClient: HttpClient,
              private loginService: LoginService) {
  }

  addNew(title: String, description: String, price: DoubleRange, isNew: boolean, location: String, contact: String, category: String) : Observable<AddNewProductResponse> {
    this.isAddMode=true;
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new AddNewProductRequest(title, description, price, isNew, location, contact, category, this.loginService.getLoggedInUsername()))
    return this.httpClient.post<AddNewProductResponse>("http://localhost:8080/products/add-new",body, {headers:headers} );
  }

  updateProduct(id:Int32Array,title: String, description: String, price: DoubleRange, isNew: boolean, location: String, contact: String, category: String) : Observable<UpdateProductResponse> {
    this.isAddMode = false;
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new AddNewProductRequest(title, description, price, isNew, location, contact, category, this.loginService.getLoggedInUsername()))
    return this.httpClient.post<UpdateProductResponse>("http://localhost:8080/products/update-product/" + id,body, {headers:headers} );
  }

  getDataForUpdate(id: Int32Array) :Observable<GetProductForUpdate> {
    return this.httpClient.get<GetProductForUpdate>('http://localhost:8080/products/get-product-for-update/' + id);
  }

  addAttributeValues(attributeValues: any) :Observable<any> {
    const headers = {'content-type':'application/json'}
    const body = attributeValues
    return this.httpClient.post("http://localhost:8080/products/add-attribute-values/" + this.getId(), body, {headers:headers} );
  }

  setId(id: number) {
    this.id = id;
    sessionStorage.setItem("productId", JSON.stringify(id));
  }

  getId() :Int32Array | number {
    if(!this.id) {
      if(sessionStorage.getItem("productId")) {
        this.id = JSON.parse(sessionStorage.getItem("productId")!);
      } else {
        this.id=null;
      }
    }
    return this.id;
  }

  setAttributes(attr: any) {
    this.attributes= attr;
    sessionStorage.setItem("attributes",JSON.stringify(attr));
  }

  getAttributes() :any {
    if(!this.attributes) {
      this.attributes = JSON.parse(sessionStorage.getItem("attributes") || "");
    }
    return this.attributes;
  }

  endAddingProcess() {
    this.id=null;
    this.attributes=null;
    sessionStorage.removeItem("productId");
    sessionStorage.removeItem("attributes");
  }


}
