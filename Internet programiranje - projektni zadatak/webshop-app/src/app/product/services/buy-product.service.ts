import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/client/services/login.service';
import { BuyingRequest } from 'src/app/model/buying/buying-request.model';
import { GetProductForBuying } from 'src/app/model/buying/get-product-for-buying.model';

@Injectable({
  providedIn: 'root'
})
export class BuyProductService {

  constructor(private httpClient: HttpClient,
              private loginService: LoginService) { }

  buyProduct(productId: Int32Array, paymentMethod: string, cardNumber: string) :Observable<Int32Array> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new BuyingRequest(productId, this.loginService.getLoggedInUsername() , paymentMethod, cardNumber))
    return this.httpClient.post<Int32Array>("http://localhost:8080/buying",body, {headers:headers} );

  }

  getProductForBuying(productId: Int32Array) :Observable<GetProductForBuying> {
    return this.httpClient.get<GetProductForBuying>("http://localhost:8080/products/get-product-for-buying/" + productId)
  }

}
