import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAllProducts } from 'src/app/model/home/get-all-products.model';
import { Product } from 'src/app/model/home/product.model';
import { Category } from 'src/app/model/product/category.model';

@Injectable({
  providedIn: 'root'
})
export class SideMenuService {

  constructor(private httpClient: HttpClient) { }

  public getAllCategories() : Observable<Array<Category>> {
    return this.httpClient.get<Array<Category>>('http://localhost:8080/categories');
  }

  public getAllLocations() : Observable<Array<string>> {
    return this.httpClient.get<Array<string>>('http://localhost:8080/products/get-locations');
  }

  public getAttributesForCategoryId(categoryId: Int32Array): Observable<Array<string>> {
    return this.httpClient.get<Array<string>>('http://localhost:8080/attributes/' + categoryId)
  }
}
