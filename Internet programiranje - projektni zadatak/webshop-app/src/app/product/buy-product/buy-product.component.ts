import { Component, Input, OnInit } from '@angular/core';
import { BuyProductService } from '../services/buy-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GetProductForBuying } from 'src/app/model/buying/get-product-for-buying.model';
import { LoginService } from 'src/app/client/services/login.service';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit{
  public form: FormGroup = new FormGroup({})

  selectedValue : any;
  id: Int32Array | any;
  isCardPayment : boolean = false;
  productForBuying: GetProductForBuying | any;
  private readonly imageType : string = 'data:image/jpg;base64,'; 
  paymentMethods: string[] = ['Karticno placanje', 'Placanje pouzecem'];
  private isMarked: boolean = false;

  constructor(private buyProductService: BuyProductService,
    private loginService: LoginService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    if(!this.loginService.isLoggedIn()) {
      this.router.navigate(["/clients/login"]);
    }
    
    this.form = new FormGroup({    
      cardNumber: new FormControl('', Validators.required)
    })
    this.id = this.route.snapshot.paramMap.get('id')
    this.buyProductService.getProductForBuying(this.id).subscribe(data=> {
      if(data.isSold) {
        this.router.navigate(["/products/" + this.id]);
      }
      this.productForBuying = data;
      this.productForBuying.image = this.imageType + this.productForBuying.image;
    })
  }

  buyProduct() {
    if(this.isMarked) {
      if(this.isCardPayment && this.form.valid) {
        this.buyProductService.buyProduct(this.id, this.paymentMethods[0], this.form.value.cardNumber).subscribe();
      } else {
        this.buyProductService.buyProduct(this.id, this.paymentMethods[1], "").subscribe();
      }
      this.router.navigate(["/products/" + this.id]);
    } else {
      this.error = "Morate oznaciti nacin placanja i unijeti ispravan broj kartice ukoliko ste odabrali karticno placanje.";      
    }
  }

  onChangePaymentMethodRadioButton(event: any) {
    this.isMarked=true;
    if(event.value==this.paymentMethods[0]) {
      this.isCardPayment=true;
    } else {
      this.isCardPayment=false;
    }
  }
  
  @Input() error: string | null | undefined

}
