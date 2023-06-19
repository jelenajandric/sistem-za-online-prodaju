import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SendMailRequest } from 'src/app/model/client/send-mail-request.model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class SendMailService {

  constructor(private httpClient: HttpClient,
              private loginService: LoginService) { 
  }

  public sendMailToTechnicalSupport(content: string) : Observable<any> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new SendMailRequest(this.loginService.getLoggedInUsername(), content))
    return this.httpClient.post("http://localhost:8080/clients/send-mail",body, {headers:headers} );
  }
}
