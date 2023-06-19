import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FilterRequest } from 'src/app/model/home/filter-request.model';
import { GetAllProducts } from 'src/app/model/home/get-all-products.model';

@Injectable({
  providedIn: 'root'
})
export class ShowAllProductsService {

  constructor(private httpClient: HttpClient) { }

  public getAllProductsWithFilter(request: any, filter: FilterRequest) : Observable<any> {
    const params = request;
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(filter, this.replacer);
    const options = { params: params, headers: headers };
    return this.httpClient.post('http://localhost:8080/products/filtering', body,options);
  }


  private replacer(key: any, value: any) :any {
    if(value instanceof Map) {
      return {
        dataType: 'Map',
        value: value.entries(),
      };
    } else {
      return value;
    }
  }


}
