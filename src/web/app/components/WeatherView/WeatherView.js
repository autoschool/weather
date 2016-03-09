import './WeatherView.scss'

import {onModel, on} from 'backbone-decorators'
import Weather from '../../data/Weather';
import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import template from './WeatherView.html'

export default class WeatherView extends ItemView {

    constructor({city, region} = {}) {
        super({
            model: new Weather({city, region})
        });
    }

    initialize() {
        this.model.fetch();
    }

    @onModel('change')
    render() {
        this.$el.html(this.template()(this.model.attributes))
    }
    
    @on('click .weather__temperature')
    nextTemp() {
        this.model.changeTemperature();
    }

    template() {
        return _.template(template)
    }
} 