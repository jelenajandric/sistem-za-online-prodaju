import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAllCommentsResponse } from 'src/app/model/product/get-all-comments-response.model';
import { GetOneProductResponse } from 'src/app/model/product/get-one-product-response.model';

@Injectable({
  providedIn: 'root'
})
export class ShowProductService {
  showedProductId : Int32Array | any;

  constructor(private httpClient: HttpClient) { }

  getProduct(id: any) : Observable<GetOneProductResponse> {
    this.showedProductId = id;
    return this.httpClient.get<GetOneProductResponse>('http://localhost:8080/products/' + id);
  }

  getCommentsForProduct(id: any): Observable<Array<GetAllCommentsResponse>> {
    return this.httpClient.get<Array<GetAllCommentsResponse>>('http://localhost:8080/comments/' + id);
  }

  deleteProduct(id: Int32Array) : Observable<any> {
    return this.httpClient.delete('http://localhost:8080/products/' + id);
  }

  changeProductToSold(id:Int32Array) :Observable<any> {
    return this.httpClient.get('http://localhost:8080/products/change-to-sold/' + id)
  }

  changeProductToAvailable(id:Int32Array) : Observable<any> {
    return this.httpClient.get('http://localhost:8080/products/change-to-available/' + id)
  }
}
