export class Product {
    id: Int32Array
    title: String
    description: String
    price: DoubleRange
    isNew: boolean
    location: String
    contact: String
    isSold: boolean
    categoryName: String
    clientUsernameSelling: String

    constructor(id: Int32Array, title:String, description: String, price: DoubleRange, isNew: boolean, 
    location: String,contact:String, isSold: boolean, categoryName: String,clientUsernameSelling: String ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isNew = isNew;
        this.location = location;
        this.contact = contact;
        this.isSold = isSold;
        this.categoryName = categoryName;
        this.clientUsernameSelling = clientUsernameSelling;
    }

}
