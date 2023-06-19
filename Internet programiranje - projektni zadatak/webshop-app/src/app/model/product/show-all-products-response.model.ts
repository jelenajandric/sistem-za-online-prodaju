export class ShowAllProductsResponse {
    id: Int32Array
    title: String
    description: String
    price: DoubleRange
    isNew: boolean
    location: String
    categoryId: String
    clientIdSelling: String
    image: String

    constructor(id: Int32Array, title:String, description: String, price: DoubleRange, isNew: boolean, 
    location: String, categoryId: String,clientIdSelling: String, image: String ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isNew = isNew;
        this.location = location;
        this.categoryId = categoryId;
        this.clientIdSelling = clientIdSelling;
        this.image = image;
    }
}
