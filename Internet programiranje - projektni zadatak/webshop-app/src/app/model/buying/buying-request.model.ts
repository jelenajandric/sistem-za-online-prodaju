export class BuyingRequest {
    productId: Int32Array;
    clientUsername: string;
    paymentMethod: string;
    cardNumber: string;

    constructor(productId: Int32Array, clientUsername: string, paymentMethod: string, cardNumber: string) {
        this.productId = productId;
        this.clientUsername = clientUsername;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
    }
}
