export class JSONAbleMap<S,T> extends Map<S,T> {
    
    toJSON() {
        //return [...this.entries()]
        return Object.fromEntries(this);
    }
}
