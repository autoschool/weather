import {Model} from 'backbone'
import _ from 'underscore'

export default class Weather extends Model {
    urlRoot = '/api/weather';

    defaults() {
        return {
            editable: false,
            city: '',
            tempindex: 0,
            daypart: 'day',
            weathercode: '800',
            sunrise: 0,
            sunset: 0,
            humidity: 0,
            wind: 0,
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
        _.each(options.data, (val, key) => {if(!val) {delete options.data[key]}});
        this.set('updated', true);
        return super.fetch(options);
    }
    
    changeTemperature() {
        this.set({'tempindex': (this.get('tempindex') + 1) % this.get('temperatures').length})
    }
}