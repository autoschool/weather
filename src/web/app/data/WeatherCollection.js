import {Collection} from 'backbone'
import {Weather} from './Weather'

export default class WeatherCollection extends Collection {
    model = Weather;
}