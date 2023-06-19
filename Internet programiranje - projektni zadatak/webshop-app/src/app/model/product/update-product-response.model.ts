export class UpdateProductResponse {
    id: number;
    attributesAndValues: Map<string,string>;

    constructor(id: number, attributesAndValues: Map<string, string>) {
        this.id = id;
        this.attributesAndValues = attributesAndValues;
    }
}
