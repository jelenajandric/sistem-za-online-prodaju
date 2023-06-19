export class ChangeEmailRequest {
    private username: string;
    private email: string;

    constructor(username: string, email: string) {
        this.username = username;
        this.email = email;
    }
}
