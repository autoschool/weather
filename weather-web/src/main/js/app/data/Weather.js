import {Model} from 'backbone';
import {urlRoot} from '../decorators';
import _ from 'underscore';

@urlRoot('/api/weather')
export default class Weather extends Model {
    defaults() {
        return {
            city: '',
            tempindex: 0,
            daypart: 'day',
            weathercode: '800',
            dt: new Date().getTime(),
            sunrise: 0,
            sunset: 0,
            humidity: 0,
            wind: 0,
            temperatures: [
                {
                    unit: '',
                    value: 0
                }]
        }
    }

    fetch(opts) {
        let {city, region} = this.attributes;
        let options = opts || {};
        options.data = _.pick({city, region}, value => value);
        this.set('updated', true);
        return super.fetch(options);
    }

    changeTemperature() {
        this.set({'tempindex': (this.get('tempindex') + 1) % this.get('temperatures').length})
    }
}