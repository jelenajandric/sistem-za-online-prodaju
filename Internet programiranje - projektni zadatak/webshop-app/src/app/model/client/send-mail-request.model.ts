export class SendMailRequest {
    private clientUsername: string;
    private content: string;

    constructor(clientUsername: string, emailContent: string) {
        this.clientUsername = clientUsername;
        this.content = emailContent;
    }
}
