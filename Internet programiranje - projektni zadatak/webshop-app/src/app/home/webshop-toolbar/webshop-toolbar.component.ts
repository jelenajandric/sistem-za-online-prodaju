import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/client/services/login.service';
import { FilterRequest } from 'src/app/model/home/filter-request.model';

@Component({
  selector: 'app-webshop-toolbar',
  templateUrl: './webshop-toolbar.component.html',
  styleUrls: ['./webshop-toolbar.component.css']
})
export class WebshopToolbarComponent {

  @Input() isLoggedIn:boolean | any;

  constructor(private router: Router,
    private loginService: LoginService) {}

  showMyAccount() {
    this.router.navigate(["/clients/show"] , { queryParams: {username: this.loginService.getLoggedInUsername() } });
  }

  updateAccount() {
    this.router.navigate(["/clients/update-account"])
  }

  sendMailToTehnicalSupport() {
    this.router.navigate(["/clients/send-mail"])
  }

  logOut() {
    this.loginService.logOut();
  }

  login() {
    this.router.navigate(["/clients/login"])
  }

  registration() {
    this.router.navigate(["/clients/registration"])
  }

}
