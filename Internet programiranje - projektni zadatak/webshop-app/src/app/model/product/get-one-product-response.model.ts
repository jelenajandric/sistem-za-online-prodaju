import { GetAllCommentsResponse } from "./get-all-comments-response.model";
import { GetAllImagesResponse } from "./get-all-images-response.model";

export class GetOneProductResponse {
    id: Int32Array;
    title: String;
    description: String;
    price: DoubleRange;
    isNew: boolean;
    location: String;
    contact: String;
    isSold: boolean;
    categoryName: String;
    clientUsernameSelling: String;
    images: Array<GetAllImagesResponse>;
    attributesAndValues: Map<String, String>;

    constructor(id: Int32Array, title: String, description: String, price: DoubleRange, isNew: boolean, location: String, 
        contact: String, isSold: boolean, categoryName: String, clientUsernameSelling: String, images: Array<GetAllImagesResponse>, 
            attributesAndValues: Map<String, String>) {
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
        this.images = images;
        this.attributesAndValues = attributesAndValues;
    }
}
