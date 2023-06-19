import { MatList } from "@angular/material/list";

export class GetAllCategories {
    categories: Array<String>

    constructor(categories: Array<String>) {
        this.categories = categories;
    }
    
}
