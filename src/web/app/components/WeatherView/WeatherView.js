import './WeatherView.scss'

import {onModel, on} from 'backbone-decorators'
import Weather from '../../data/Weather';
import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import template from './WeatherView.html'
import $ from 'jquery'
import router from '../../routes'

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
        super.render()
    }

    @on('click .weather__temperature')
    nextTemp() {
        this.model.changeTemperature();
    }

    template(serialized) {
        return _.template(template)(serialized)
    }

    @on('click .weather__city > span')
    editName(el) {
        var name = this.model.get('city');
        var editableName = $(el.target);

        editableName.html($('<input/>', {
            type: 'text',
            class: 'weather__input'
        }));

        editableName.find('input')
            .focus()
            .val(name);
    }

    @on('blur .weather__input')
    applyNewName(e) {
        var target = $(e.target);
        if (target.val() === this.model.get('city')) {
            this.render();
        }
        
        router.toCity(target.val());
    }
} 