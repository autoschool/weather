import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import {onModel, on} from 'backbone-decorators'

import template from './template.html'
import './styles.scss'

export default class WeatherCardValue extends ItemView {
    constructor(model) {
        super({
            model,
            templateHelpers: {
                humanizedTemperature: function () {
                    return Number(this.temperatures[this.tempindex].value).toFixed(1)
                }
            }
        })
    }

    template(serialized) {
        return _.template(template)(serialized)
    }

    @on('click .weather-temperature')
    nextTemp() {
        this.model.changeTemperature();
    }
}