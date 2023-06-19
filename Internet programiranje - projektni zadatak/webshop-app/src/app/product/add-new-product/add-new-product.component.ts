import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { SideMenuService } from 'src/app/home/services/side-menu.service';
import { AddNewProductService } from '../services/add-new-product.service';
import { GetProductForUpdate } from 'src/app/model/product/get-product-for-update.model';
import { Category } from 'src/app/model/product/category.model';
import { LoginService } from 'src/app/client/services/login.service';

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent implements OnInit {
  public form: FormGroup = new FormGroup({})
  id: Int32Array | any;
  isAddMode?: boolean;
  loading = false;
  submitted = false;

  categories: Array<Category> = new Array<Category>

  @Input() error: string | null | undefined

  constructor(private sideMenuService: SideMenuService,
              private addNewProductService: AddNewProductService,
              private router: Router,
              private route: ActivatedRoute,
              private loginService: LoginService) {
  }

  ngOnInit(): void {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    }
    this.sideMenuService.getAllCategories().subscribe(data => {
      if(data!=null) {
        data.forEach(element => {
          this.categories.push(element)
        });
      }
    })

    this.form = new FormGroup({    
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      isNew: new FormControl('', Validators.required),
      location: new FormControl('', Validators.required),
      contact: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required)
    })

    this.route.firstChild?.paramMap.subscribe(params => {
      this.id = params.get('id');
      this.isAddMode = !this.id;
      if (!this.isAddMode) {
        this.addNewProductService.getDataForUpdate(this?.id)
          .subscribe(x => {
            if(this.loginService.getLoggedInUsername() != x.clientUsernameSeller) {
              this.router.navigate(["/products/" + this.id])
            }
            this.form.patchValue(x)
          });
      }
    });
  }

  compareCategoryObjects(object1: any, object2: any) {
    return object1 && object2 && object1 == object2;
  } 

  public submit() :void {
    if(this.form.valid) {
      this.submitted = true;
      if (this.isAddMode) {
        this.createNew();
      } 
      else {
        this.upadteProduct();
      }
    } else {
      this.error="Popunite sva polja vazecim vrijednostima."
    }
  }

  private createNew() {
    this.addNewProductService.addNew(this.form.value.title, this.form.value.description, this.form.value.price, 
      this.form.value.isNew=="Nov",this.form.value.location, this.form.value.contact, this.form.value.category).subscribe(data => {
        this.addNewProductService.setId(data.id);
        this.addNewProductService.setAttributes(data.attributes);
        this.router.navigate(["products/add-attribute-values"])
      })
      this.form.reset();
  }

  private upadteProduct() {
    this.addNewProductService.updateProduct(this.id, this.form.value.title, this.form.value.description, this.form.value.price, 
      this.form.value.isNew=="Nov",this.form.value.location, this.form.value.contact, this.form.value.category).subscribe(data => {
        this.addNewProductService.setId(data.id);
        this.addNewProductService.setAttributes(data.attributesAndValues);
        this.router.navigate(["products/add-attribute-values"])
      })
      this.form.reset();
  }
}
