import {Model} from 'backbone'
import {Collection} from 'backbone'
import url from 'url'

class CityInSuggestModel extends Model {
}

export default class CitySuggestsCollection extends Collection {
    model = CityInSuggestModel;

    constructor(query) {
        super({});
        this.query = query;
    }

    url() {
        return '/api/suggest?query=' + this.query;
    }
}