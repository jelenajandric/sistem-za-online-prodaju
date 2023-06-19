export class GetClientInfoResponse {
    id: number | any;
    name: string;
    surname: string;
    username: string;
    city: string;
    email: string;
    avatar:string

    constructor(name:string, surname:string, username: string, city: string, email: string, avatar:string) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.city = city;
        this.email = email;
        this.avatar = avatar;
    }
}
