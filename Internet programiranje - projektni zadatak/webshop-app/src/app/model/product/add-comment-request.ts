export class AddCommentRequest {
    content: string;
    productId: Int32Array;
    clientUsername: string;

    constructor(content: string, productId: Int32Array, clientUsername: string) {
        this.content = content;
        this.productId = productId;
        this.clientUsername = clientUsername;
    }
}
