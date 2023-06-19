import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AddNewProductService } from '../services/add-new-product.service';
import { LoginService } from 'src/app/client/services/login.service';

@Component({
  selector: 'app-add-attribute-values',
  templateUrl: './add-attribute-values.component.html',
  styleUrls: ['./add-attribute-values.component.css']
})
export class AddAttributeValuesComponent implements OnInit {
  public form: FormGroup = new FormGroup({})

  attributes: Array<string> | undefined;
  attributesAndValues: Map<string, string> |any;

  constructor(private addNewProductService: AddNewProductService, 
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.checkConditions();
    if(this.addNewProductService.isAddMode==true) {
      this.attributes = this.addNewProductService.attributes;
      if(this.attributes!=undefined) {
        this.attributes.forEach(attr => {
          this.form.addControl(attr, new FormControl('', Validators.required))
        });
      }
    } else {
      if(this.addNewProductService.attributes!=undefined) {
        this.attributesAndValues = this.addNewProductService.attributes;
        this.attributes = Object.keys(this.attributesAndValues);
        this.attributes.forEach(attr => {
            this.form.addControl(attr, new FormControl('', Validators.required))
          });
          this.form.patchValue(this.attributesAndValues)
      }
    }
  }

  submit() {
    if(this.form.valid) {
      let attributeValues = new Map<String, String>([])
      if(this.attributes!=undefined) {
        this.attributes.forEach(attr => {
          attributeValues.set(attr, this.form.getRawValue())
        });
        this.addNewProductService.addAttributeValues(this.form.getRawValue(), ).subscribe(data => {
          this.router.navigate(["products/upload-images"])
        })
      }
    } else {
      this.error = "Popunite sva polja vazecim vrijednostima.";
    }
  }

  private checkConditions() {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    } else if(this.addNewProductService.getId()==null || this.addNewProductService.getAttributes() == null) {
      this.router.navigate(["/products/add-new"]);
    }
  }

  @Input() error: string | null | undefined

}