import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/model/client/login-request.model';
import { LoginResponse } from 'src/app/model/client/login-response.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loggedIn : boolean = false;
  private loggedInUser : string | any;

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<LoginResponse> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new LoginRequest(username, password))
    return this.http.post<LoginResponse>("http://localhost:8080/clients/login",body, {headers:headers} );
  }

  public isLoggedIn() : boolean {
    if(sessionStorage.getItem("isLoggedIn")==null) {
      this.loggedIn = false;
    } else {
      this.loggedIn = JSON.parse(sessionStorage.getItem("isLoggedIn") || "");
    }
    return this.loggedIn;
  }

  public setIsLoggedIn(isLoggedIn: boolean) {
    sessionStorage.setItem("isLoggedIn", JSON.stringify(isLoggedIn));
    this.loggedIn = isLoggedIn;
  }

  public getLoggedInUser() : LoginResponse {
    if(this.loggedInUser==null) {
      this.loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser")!);
    }
    return this.loggedInUser;
  }

  public getLoggedInUsername() : string {
    if(this.loggedInUser==null) {
      return this.getLoggedInUser().username;
    }
    return this.loggedInUser.username;
  }

  public setLoggedInUser(user: LoginResponse) {
    sessionStorage.setItem("loggedInUser" , JSON.stringify(user));
    this.loggedInUser = user;
  }

  public logOut() {
    this.loggedIn = false;
    this.loggedInUser = null;
    sessionStorage.setItem("isLoggedIn", JSON.stringify(false));
    sessionStorage.removeItem("loggedInUser")
  }

  setIsActivatedOnTrue() {
    this.loggedInUser.isActivated = true;
    this.setLoggedInUser(this.loggedInUser);
  }


}
