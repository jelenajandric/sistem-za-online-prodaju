import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetInfoForUpdate } from 'src/app/model/client/get-info-for-update.model';
import { RegistrationRequest } from 'src/app/model/client/registration-request.model';
import { RegistrationResponse } from 'src/app/model/client/registration-response.model';
import { LoginService } from './login.service';
import { LoginResponse } from 'src/app/model/client/login-response.model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  constructor(private httpClient: HttpClient,
              private loginService: LoginService) { 
  }

  public register(name: string, surname:string, username: string, password: string, city: string, email: string): Observable<RegistrationResponse> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new RegistrationRequest(name, surname, username, password, city, email))
    return this.httpClient.post<RegistrationResponse>("http://localhost:8080/clients/registration",body, {headers:headers} );
  }

  public isUsernameFree(username: string) :Observable<boolean> {
    return this.httpClient.get<boolean>("http://localhost:8080/clients/is-username-free?username=" + username);
  }

  public updateAccount(name: string, surname:string, username: string, password: string, city: string, email: string): Observable<Int32Array> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new RegistrationRequest(name, surname, username, password, city, email))
    return this.httpClient.post<Int32Array>("http://localhost:8080/clients/update-account",body, {headers:headers} );
  }

  public getInfoForUpdate() : Observable<GetInfoForUpdate>  {
    return this.httpClient.get<GetInfoForUpdate>("http://localhost:8080/clients/get-my-info?username=" + this.loginService.getLoggedInUsername());
  }

  public setRegisteredUser(registeredUser: RegistrationResponse) {
    this.loginService.setLoggedInUser(new LoginResponse(registeredUser.id, registeredUser.name, registeredUser.surname, 
      registeredUser.username, registeredUser.city, registeredUser.email, registeredUser.avatar, registeredUser.isActivated));
  }
}
