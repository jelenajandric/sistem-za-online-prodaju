import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetClientInfoResponse } from 'src/app/model/client/get-client-info-response.model';
import { GetAllProducts } from 'src/app/model/home/get-all-products.model';

@Injectable({
  providedIn: 'root'
})
export class ShowClientServiceService {

  constructor(private httpClient: HttpClient) { }

  getClientInfo(username: string) :Observable<GetClientInfoResponse> {
    return this.httpClient.get<GetClientInfoResponse>("http://localhost:8080/clients/get-my-info?username=" + username );
  }  

}
