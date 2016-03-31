import {LayoutView} from "backbone.marionette";
import _ from "underscore";
import {onModel, on} from "backbone-decorators";
import moment from "moment";
import template from "./template.html";
import "./styles.scss";

export default class WeatherCardValue extends LayoutView {
    template = _.template(template);

    constructor(options) {
        super({
            ...options,
            templateHelpers: {
                humanizedTemperature: function () {
                    return Number(this.temperatures[this.tempindex].value).toFixed(1)
                },
                asTime: function (unixts) {
                    return moment(unixts, 'X').format('HH:mm')
                },
                weatherImage: function () {
                    return `wi-owm-${this.daypart.toLowerCase()}-${this.weathercode}`
                }
            }
        });
    }

    @on('click .weather-temperature')
    nextTemp() {
        this.model.changeTemperature();
    }
}