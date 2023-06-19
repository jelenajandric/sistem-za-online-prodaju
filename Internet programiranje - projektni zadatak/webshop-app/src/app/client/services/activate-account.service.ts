import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivateAccountRequest } from 'src/app/model/client/activate-account-request.model';
import { ChangeEmailRequest } from 'src/app/model/client/change-email-request.model';
import { RegistrationService } from './registration.service';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class ActivateAccountService {

  constructor(private loginService: LoginService,
    private httpClient: HttpClient) {}

  public acctivateAccountSubmit(pin: Int32Array) : Observable<Boolean> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new ActivateAccountRequest(this.loginService.getLoggedInUsername(), pin))
    return this.httpClient.post<Boolean>("http://localhost:8080/activate-account",body, {headers:headers} );
  }

  public sendPinAgain() : Observable<any> {
    return this.httpClient.get("http://localhost:8080/send-pin-again/" + this.loginService.getLoggedInUsername());
  }

  public changeEmail(email: string): Observable<Boolean> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new ChangeEmailRequest(this.loginService.getLoggedInUsername(), email))
    return this.httpClient.post<Boolean>("http://localhost:8080/clients/change-email",body, {headers:headers} );
  }
}
