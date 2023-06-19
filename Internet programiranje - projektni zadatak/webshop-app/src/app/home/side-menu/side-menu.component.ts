import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { SideMenuService } from '../services/side-menu.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { Product } from 'src/app/model/home/product.model';
import { GetAllProducts } from 'src/app/model/home/get-all-products.model';
import { BrowserModule } from '@angular/platform-browser'
import { Category } from 'src/app/model/product/category.model';
import { ShowAllProductsResponse } from 'src/app/model/product/show-all-products-response.model';
import { PageEvent } from '@angular/material/paginator';
import { filter, map } from 'rxjs/operators';
import { FilterRequest } from 'src/app/model/home/filter-request.model';
import { FormControl, FormGroup } from '@angular/forms';
import { JSONAbleMap } from 'src/app/model/util/jsonable-map';
import { MatMenuItem } from '@angular/material/menu';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/client/services/login.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  categories: Array<Category> = new Array<Category>();
  locations: Array<string> = new Array<string>();
  filter: FilterRequest = new FilterRequest();
  public form: FormGroup = new FormGroup({})
  public searchForm: FormGroup = new FormGroup({})
  attributes: Array<string> | any;
  attributesAndValues: Map<string, string> = new Map<string, string>
  searchedWord: string | any;

  constructor(private sideMenuService: SideMenuService,
              private router: Router,
              private loginService: LoginService) {
    this.form = new FormGroup({
      priceFrom: new FormControl(''),
      priceTo: new FormControl('')
    })

    this.searchForm = new FormGroup({
      searchTitle: new FormControl()
    })
  }

  ngOnInit(): void {
    this.sideMenuService.getAllCategories().subscribe(data => {
      if (data != null) {
        data.forEach(element => {
          this.categories.push(element)
        });
      }
    })

    this.sideMenuService.getAllLocations().subscribe(data => {
      if (data != null) {
        data.forEach(element => {
          this.locations.push(element)
        });
      }
    })
    this.filter.isSold = false;
  }


  search() {
    this.filter.title = this.searchForm.value.searchTitle;
    this.cloneFilterObject();
  }

  clearSearhWord() {
    this.searchedWord = null;
  }

  onClickCategory(categoryyId: Int32Array) {
    this.filter.categoryId = categoryyId;
    this.filter = { ... this.filter } //ngOnChanges ne detektuje izmjenu objekta pa ga kloniram, vrijednost se prenosi po referenci

    this.attributes = new Array<string>();
    this.sideMenuService.getAttributesForCategoryId(categoryyId).subscribe(data => {
      data.forEach(element => {
        this.attributes.push(element)
        this.form.addControl(element, new FormControl())
      });
    })
  }

  onClickAllCategories() {
    this.filter.categoryId = null;
    this.attributes = null;
    this.cloneFilterObject();
  }

  onClickLocation(location: string) {
    this.filter.location = location;
    this.cloneFilterObject();
  }

  onClickAllLocations() {
    this.filter.location = null;
    this.cloneFilterObject();
  }

  onClickNewUsedAllButtons(productState: string) {
    this.filter.newOrUsedOrAll = productState;
    this.cloneFilterObject();
  }

  submitFilters() {
    this.filter.priceFrom = this.form.value.priceFrom;
    this.filter.priceTo = this.form.value.priceTo;
    if (this.attributes != null) {
      this.attributes.forEach((attr: any) => {
        if (this.form.value[attr]) {
          this.attributesAndValues.set(attr, this.form.value[attr])
        }
      });
      this.filter.attributesAndValues = new JSONAbleMap(this.attributesAndValues);
    }
    this.attributesAndValues = new Map<string,string>();
    this.cloneFilterObject();
  }

  clearFilters() {
    this.form.reset();
    this.filter = new FilterRequest();
    this.filter.isSold = false;
    if(this.searchedWord) {
      this.filter.title = this.searchedWord;
    }
    this.cloneFilterObject();
  }

  private cloneFilterObject() {
    this.filter = { ... this.filter }
  }

  public isLoggedIn() :boolean {
    return this.loginService.isLoggedIn();
  }

}
