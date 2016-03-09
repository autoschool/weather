import {Model} from 'backbone'
import _ from 'underscore'

export default class Weather extends Model {
    urlRoot() {
        return '/api/weather';
    }

    defaults() {
        return {
            city: '',
            temperatures: [{
                unit: '',
                value: 0
            }]
        }
    }

    fetch(opts) {
        let {city, region} = this.attributes;
        let options = opts || {};
        options.data = {city, region};
        super.fetch(options);
    }
}