export class LoginResponse {
    id: Int32Array;
    name: string;
    surname: string;
    username: string;
    city: string;
    email: string;
    avatar: string;
    isActivated: boolean;

    constructor(id:Int32Array, name:string, surname:string, username: string, city: string, email: string, avatar: string, isActivated:boolean) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.city = city;
        this.email = email;
        this.avatar = avatar;
        this.isActivated = isActivated;
    }
}
