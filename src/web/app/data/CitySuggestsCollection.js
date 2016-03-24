import {Model} from 'backbone'
import {Collection} from 'backbone'

class CityInSuggestModel extends Model {
}

export default class CitySuggestsCollection extends Collection {
    constructor(query) {
        super([], {model: CityInSuggestModel});
        this.query = query;
    }

    url() {
        return '/api/suggest?query=' + this.query;
    }
}