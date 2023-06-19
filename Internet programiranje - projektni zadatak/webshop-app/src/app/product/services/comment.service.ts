import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/client/services/login.service';
import { AddCommentRequest } from 'src/app/model/product/add-comment-request';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient,
              private loginService: LoginService) { }

  addComment(content: string, productId: Int32Array) : Observable<any> {
    const headers = {'content-type':'application/json'}
    const body = JSON.stringify(new AddCommentRequest(content, productId, this.loginService.getLoggedInUsername()))
    return this.httpClient.post("http://localhost:8080/comments/add-comment",body, {headers:headers} );
  }

  deleteComment(commentId: Int32Array) : Observable<any> {
    return this.httpClient.delete("http://localhost:8080/comments/" + commentId);
  }
}
