export class RegistrationRequest {
    name: string;
    surname: string;
    username: string;
    password: string;
    city: string;
    email: string;

    constructor(name:string, surname:string, username: string, password: string, city: string, email: string) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.city = city;
        this.email = email;
    }
}
