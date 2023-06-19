export class GetAllImagesResponse {
    id: Int32Array;
    image: string;

    constructor(id: Int32Array, image: string) {
        this.id = id;
        this.image = image;
    }
}

