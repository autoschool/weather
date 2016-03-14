import './WeatherView.scss'

import {onModel, on} from 'backbone-decorators'
import Weather from '../../data/Weather';
import {LayoutView} from 'backbone.marionette'
import _ from 'underscore'
import template from './WeatherView.html'
import $ from 'jquery'
import router from '../../routes'
import Suggest from '../../blocks/CitySuggest/CitiesSuggestView'

export default class WeatherView extends LayoutView {

    constructor({city, region} = {}) {
        super({
            model: new Weather({city, region}),
            regions: {
                suggest: '#suggest'
            },
            templateHelpers: {
                humanizedTemperature: function () {
                    return Number(this.temperatures[this.tempindex].value).toFixed(1)
                }
            }
        });
    }

    onRender() {
        let updated = this.model.get('updated');
        if (!updated) {
            this.model.fetch();
        }
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
            .val(name)
            .select();
    }

    applyNewName(e) {
        var target = $(e.target);
        if (target.val() === this.model.get('city')) {
            this.render();
        }

        router.toCity(target.val());
    }

    @on('keypress .weather__input')
    updateSuggest(e) {
        if (e.keyCode === 13) {
            this.applyNewName(e);
        }
        var query = $(e.target).val();
        if (query.length > 1) {
            this.suggest.show(new Suggest(query));
        } else {
            this.suggest.empty();
        }
    }
} 