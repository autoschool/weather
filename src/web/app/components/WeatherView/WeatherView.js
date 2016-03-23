import {onModel, on} from "backbone-decorators";
import {region} from "../../decorators";
import Weather from "../../data/Weather";
import {LayoutView} from "backbone.marionette";
import _ from "underscore";
import template from "./WeatherView.html";
import WeatherCard from "../../blocks/WeatherCard/index";
import "./WeatherView.scss";

export default class WeatherView extends LayoutView {

    template = _.template(template);

    @region('#card')
    card;

    constructor({city, region, ...options} = {}) {
        super({
            ...options,
            model: new Weather({city, region})
        });
    }

    onRender() {
        this.card.show(new WeatherCard({model: this.model}));

        let updated = this.model.get('updated');
        if (!updated) {
            this.model.fetch();
        }
    }

    @onModel('change')
    render() {
        super.render()
    }


} 