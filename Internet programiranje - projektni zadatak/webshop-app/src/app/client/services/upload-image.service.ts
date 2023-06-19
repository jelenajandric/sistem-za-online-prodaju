import { Injectable } from '@angular/core';
import { RegistrationService } from './registration.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UploadImageResponse } from 'src/app/model/client/upload-image-response.model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {

  constructor(private loginService: LoginService,
    private httpClient: HttpClient) { }

  public uploadImage(imageFormData: FormData): Observable<HttpResponse<Object>> {
    return this.httpClient.post('http://localhost:8080/upload-avatar/' +  this.loginService.getLoggedInUsername(), 
                                    imageFormData, { observe: 'response' })
  }

  public getImage() :Observable<any> { 
    return this.httpClient
    .get<UploadImageResponse>('http://localhost:8080/get-avatar-by-username/' +  this.loginService.getLoggedInUsername()) 
  }
  
  public deleteImage() :Observable<any> {
    return this.httpClient.get('http://localhost:8080/delete-avatar/' +  this.loginService.getLoggedInUsername())
  }
}