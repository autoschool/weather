import './WeatherView.scss'

import {onModel, on} from 'backbone-decorators'
import Weather from '../../data/Weather';
import {LayoutView} from 'backbone.marionette'
import _ from 'underscore'
import template from './WeatherView.html'
import WeatherCard from '../../blocks/WeatherCard/index'
import $ from 'jquery'
import router from '../../routes'
import Suggest from '../../blocks/CitySuggest/CitiesSuggestView'

export default class WeatherView extends LayoutView {

    constructor({city, region} = {}) {
        super({
            model: new Weather({city, region}),
            regions: {
                card: '#card'
            }
        });
    }

    onRender() {
        this.card.show(new WeatherCard(this.model));

        let updated = this.model.get('updated');
        if (!updated) {
            this.model.fetch();
        }
    }

    @onModel('change')
    render() {
        super.render()
    }

    template(serialized) {
        return _.template(template)(serialized)
    }
} 