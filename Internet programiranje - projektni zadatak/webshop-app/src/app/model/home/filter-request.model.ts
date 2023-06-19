import { JSONAbleMap } from "../util/jsonable-map";

export class FilterRequest {
    title: string | any;
    categoryId: Int32Array | any;
    location: string | any;
    priceFrom: DoubleRange | any;
    priceTo: DoubleRange | any;
    newOrUsedOrAll: string | any;
    clientId: Int32Array | any;
    isSold: boolean | any;
    attributesAndValues: Map<String, String> | any;

    constructor() {}
}
