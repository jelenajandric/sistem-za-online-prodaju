export class ActivateAccountRequest {
    private username: string;
    private pin: Int32Array;

    constructor(username: string, pin: Int32Array) {
        this.username = username;
        this.pin = pin;
    }
}
