import { Component, Input, OnChanges, OnInit, SimpleChange, SimpleChanges } from '@angular/core';
import { ShowAllProductsResponse } from 'src/app/model/product/show-all-products-response.model';
import { ShowAllProductsService } from '../services/show-all-products.service';
import { PageEvent } from '@angular/material/paginator';
import { FilterRequest } from 'src/app/model/home/filter-request.model';

@Component({
  selector: 'app-show-all-products',
  templateUrl: './show-all-products.component.html',
  styleUrls: ['./show-all-products.component.css']
})
export class ShowAllProductsComponent implements OnInit, OnChanges {

  products : Array<ShowAllProductsResponse> = new Array<ShowAllProductsResponse>;
  private readonly imageType : string = 'data:image/jpg;base64,'; 
  totalElements: number = 0;
  private size: number = 5;

  @Input() filter:FilterRequest | any;
  
  constructor(private showAllProducts: ShowAllProductsService) {
  }

  ngOnInit() :void {
    this.getProductsWithFilter({ page: "0", size: "5" });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(!changes['filter'].firstChange) {
      this.getProductsWithFilter({ page: "0", size: this.size })
    }
  }

  private getProductsWithFilter(request:any) {
    if(this.filter!=undefined) {
      this.products = new Array<ShowAllProductsResponse>();
      this.showAllProducts.getAllProductsWithFilter(request, this.filter).subscribe(data => {
        this.totalElements = data.products['totalElements'];
        (data.products['content']).forEach((element:any) => {
          let image= data.images[element.id];
          this.products.push(new ShowAllProductsResponse(element.id, element.title, element.description,element.price, element.isNew, 
            element.location, element.categoryId, element.clientIdSelling, image?this.imageType + image : image ));
        });
      })
    }
  }

  nextPage(event: PageEvent) {
    const req : any = {};
    req['page'] = event.pageIndex.toString();
    req['size'] = event.pageSize.toString();
    this.size= event.pageSize;
    this.getProductsWithFilter(req);
  }

}
