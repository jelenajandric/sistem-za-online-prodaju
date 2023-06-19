export class GetProductForUpdate {
    id: Int32Array;
    title: string;
    description: string;
    price: DoubleRange;
    isNew: string;
    location: string;
    contact: string;
    category: string;
    clientUsernameSeller: string;

    constructor(id: Int32Array, title: string, description: string, price: DoubleRange, isNew: string, location: string, 
                    contact: string, categoryName: string, clientUsernameSeller: string) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isNew = isNew;
        this.location = location;
        this.contact = contact;
        this.category = categoryName;
        this.clientUsernameSeller = clientUsernameSeller;
    }
}
