export class AddNewProductRequest {
    title: String
    description: String
    price: DoubleRange
    isNew: boolean
    location: String
    contact: String
    categoryName: String
    clientUsernameSelling: String

    constructor(title:String, description: String, price: DoubleRange, isNew: boolean, 
        location: String,contact:String, categoryName: String,clientUsernameSelling: String ) {
            this.title = title;
            this.description = description;
            this.price = price;
            this.isNew = isNew;
            this.location = location;
            this.contact = contact;
            this.categoryName = categoryName;
            this.clientUsernameSelling = clientUsernameSelling;
        }
}
