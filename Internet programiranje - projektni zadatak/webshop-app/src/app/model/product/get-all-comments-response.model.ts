import { Time } from "@angular/common";
import { Timestamp } from "rxjs";

export class GetAllCommentsResponse {
    id: Int32Array;
    content: string;
    clientName: string;
    clientSurname: string;
    clientUsername: string;
    clientAvatar: string;
    time: string;

    constructor(id: Int32Array, content: string, clientName: string, clientSurname: string, clientUsername: string, 
        clientAvatar: string, time: string) {
        this.id = id;
        this.content = content;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientUsername = clientUsername;
        this.clientAvatar = clientAvatar;
        this.time = time;
    }
    
}
