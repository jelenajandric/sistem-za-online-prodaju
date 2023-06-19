import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShowClientServiceService } from '../services/show-client-service.service';
import { GetClientInfoResponse } from 'src/app/model/client/get-client-info-response.model';
import { GetAllProducts } from 'src/app/model/home/get-all-products.model';
import { MatDialog } from '@angular/material/dialog';
import { FilterRequest } from 'src/app/model/home/filter-request.model';
import { WebshopToolbarComponent } from 'src/app/home/webshop-toolbar/webshop-toolbar.component';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-show-client',
  templateUrl: './show-client.component.html',
  styleUrls: ['./show-client.component.css']
})
export class ShowClientComponent implements OnInit {

  clientInfo : GetClientInfoResponse | any;
  private readonly imageType : string = 'data:image/jpg;base64,';
  showProducts: Array<GetAllProducts> = new Array<GetAllProducts>
  username: string |any;

  filter: FilterRequest | any;

  constructor(private showClientService: ShowClientServiceService,
    private loginService: LoginService,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
      this.username = params['username'];
      this.showClientService.getClientInfo(this.username).subscribe(data => {
        this.clientInfo = data;
        this.clientInfo.avatar = this.imageType + this.clientInfo.avatar;
        this.filter = new FilterRequest();
        this.filter.clientId = this.clientInfo.id;
        this.filter.isSold = false;
        this.cloneFilterObject();
      });
    })
  }

  changedTab(event:any) {
    this.showProducts = new Array<GetAllProducts>();
    if(event.index==0) {
      this.filter.isSold = false;
      this.cloneFilterObject();
    } else {
      this.filter.isSold = true;
      this.cloneFilterObject();
    }
  }

  private cloneFilterObject() {
    this.filter = { ... this.filter }
  }

  public isLoggedIn() :boolean {
    return this.loginService.isLoggedIn();
  }

}
