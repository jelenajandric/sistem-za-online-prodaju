export class GetProductForBuying {
    id:Int32Array;
    title:string;
    image:string;
    isSold:boolean

    constructor(id:Int32Array, title:string, image:string, isSold: boolean) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.isSold = isSold;
    }
}
