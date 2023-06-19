export class GetAllProducts {
    id: Int32Array
    title: String
    description: String
    price: DoubleRange
    isNew: boolean
    location: String
    categoryName: String
    clientUsernameSelling: String
    image: any

    constructor(id: Int32Array, title:String, description: String, price: DoubleRange, isNew: boolean, 
    location: String, categoryName: String,clientUsernameSelling: String, image: string ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isNew = isNew;
        this.location = location;
        this.categoryName = categoryName;
        this.clientUsernameSelling = clientUsernameSelling;
        this.image = image;
    }
}
