import {Model} from 'backbone'
import {ItemView} from 'backbone.marionette'

import Card from '../../blocks/Card/index'
import $ from 'jquery'
import _ from 'underscore'
import router from '../../routes'

import WeatherCardCity from './__City/index'
import WeatherCardValue from './__Value/index'

export default class WeatherCard extends Card {

    constructor(model) {
        super({});
        this.model = model;
    }

    getTitlePrimaryView() {
        return new WeatherCardCity(this.model);
    }

    getTitleSecondaryView() {
        return new ItemView({template: _.template('today')});
    }

    getTextView() {
        return new WeatherCardValue(this.model);
    }

    getActionsView() {
        return new ItemView({template: _.template('')});
    }
}