import './WeatherView.scss'
import Weather from '../../data/Weather';
import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import template from './WeatherView.html'

export default class WeatherView extends ItemView {

    initialize() {
        this.model = new Weather();
        this.model.on('change', this.render, this);
        this.model.fetch();
    }
    
    render() {
        this.$el.html(WeatherView.template()(this.model.attributes))
    }

    static template() {
        return _.template(template)
    }
} 