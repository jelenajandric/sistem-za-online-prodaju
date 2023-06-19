export class AddNewProductResponse {
    id: number;
    attributes: Array<string>;

    constructor(id: number, attributes: Array<string>) {
        this.id = id;
        this.attributes = attributes;
    }
}
