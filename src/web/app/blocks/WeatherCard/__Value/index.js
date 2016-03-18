import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import {onModel, on} from 'backbone-decorators'

import template from './template.html'
import './styles.scss'

export default class WeatherCardValue extends ItemView {
    template = _.template(template);
    
    constructor(options) {
        super({
            ...options, ...{
                templateHelpers: {
                    humanizedTemperature: function () {
                        return Number(this.temperatures[this.tempindex].value).toFixed(1)
                    }
                }
            }
        });
    }

    @on('click .weather-temperature')
    nextTemp() {
        this.model.changeTemperature();
    }
}