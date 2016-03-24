import {Collection} from 'backbone';
import Weather from './Weather';
import {model} from 'backbone-decorators';

@model(Weather)
export default class WeatherCollection extends Collection {
    fetch() {
        this.models
            .filter(model => !model.get('updated'))
            .forEach(model => {
                model.fetch()
            });
    }
}