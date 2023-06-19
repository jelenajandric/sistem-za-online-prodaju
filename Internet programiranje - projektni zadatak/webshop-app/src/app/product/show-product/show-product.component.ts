import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GetOneProductResponse } from 'src/app/model/product/get-one-product-response.model';
import { GetAllImagesResponse } from "src/app/model/product/get-all-images-response.model";
import { ShowProductService } from '../services/show-product.service';
import { GetAllCommentsResponse } from 'src/app/model/product/get-all-comments-response.model';
import { CommentService } from '../services/comment.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmModalComponent } from 'src/app/confirm-modal/confirm-modal.component';
import { LoginService } from 'src/app/client/services/login.service';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.css']
})
export class ShowProductComponent implements OnInit {
  public form: FormGroup = new FormGroup({})

  productId: Int32Array | any;
  product: GetOneProductResponse | any;
  comments: Array<GetAllCommentsResponse> | any;
  private readonly imageType : string = 'data:image/jpg;base64,'; 
  attributeValuesSize = 0;

  @Input() error: string | null | undefined;


  constructor(private route: ActivatedRoute,
              private showProductService: ShowProductService,
              private commentService: CommentService,
              private loginService: LoginService,
              private router: Router,
              private dialog: MatDialog,) {
    
  }

  public ngOnInit(): void {
    this.form = new FormGroup({    
      commentContent: new FormControl('')
    })
    this.getProductInfo();
    this.getAllComments();
  }

  addComment() {
    if(!this.form.value.commentContent) {
      this.error = "Potrebno je da unesete tekst komentara."
    } else {
      this.commentService.addComment(this.form.value.commentContent, this.productId).subscribe(data => {
        this.getAllComments();
        this.form.reset();
      })
    }
  }

  deleteComment(commentId: Int32Array) {
    this.commentService.deleteComment(commentId).subscribe(data => {
      this.getAllComments();
    })
  }

  private getAllComments() {
    this.showProductService.getCommentsForProduct(this.productId).subscribe(data => {
      this.comments = data;
      this.comments.forEach((comment: GetAllCommentsResponse) => {
        if(comment.clientAvatar!=null)
          comment.clientAvatar = this.imageType + comment.clientAvatar;
          comment.time = new Date(comment.time).toLocaleString();
      });
    })
  }

  private getProductInfo() {
    this.productId = this.route.snapshot.paramMap.get('id');
    this.showProductService.getProduct(this.productId).subscribe(data => {
      this.product = data;
      this.product.images.forEach((element: GetAllImagesResponse) => {
        element.image = this.imageType + element.image;
      });
      this.attributeValuesSize = Object.keys(this.product.attributesAndValues).length;
    })
  }

  buyThisProduct(id: Int32Array) {
    this.router.navigate(["products/buying/" + id])
  }

  deleteProduct(id: Int32Array) {
    if(this.isLoggedUserSeller()) {
      this.dialog.open(ConfirmModalComponent, {
        width: '300px'
      })
        .afterClosed()
        .subscribe(result => {
          if (result) {
            this.showProductService.deleteProduct(id).subscribe(data => {
              this.router.navigate(["/home"]);
            })
          }
        });
    }
  }

  changeProductToSold(id: Int32Array) {
    if(this.isLoggedUserSeller()) {
      this.showProductService.changeProductToSold(id).subscribe(data => {
        this.getProductInfo();
      })
    }
  }

  changeProductToAvailable(id:Int32Array) {
    if(this.isLoggedUserSeller()) {
      this.showProductService.changeProductToAvailable(id).subscribe(data => {
        this.getProductInfo();
      })
    }
  }

  updateThisProduct(id:number) {
    if(this.isLoggedUserSeller()) {
      this.router.navigate(["/products/update/"+ id]);
    }
  }

  getSellerInfo() {
    this.router.navigate(["/clients/show"] , { queryParams: {username: this.product?.clientUsernameSelling } });
  }

  public isLoggedIn() :boolean {
    return this.loginService.isLoggedIn();
  }

  public isLoggedUserSeller() : boolean {
    return this.loginService.getLoggedInUser().username === this.product?.clientUsernameSelling;
  }
}
