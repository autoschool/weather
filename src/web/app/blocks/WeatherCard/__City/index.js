import {LayoutView} from 'backbone.marionette'
import _ from 'underscore'
import {on} from 'backbone-decorators'
import {region} from '../../../decorators'
import $ from 'jquery'
import router from '../../../routes'
import Suggest from '../../CitySuggest/CitiesSuggestView'

import template from './template.html'
import './styles.scss'

export default class WeatherCardCity extends LayoutView {
    @region('.city-suggest-area')
    suggest;
    
    constructor(model) {
        super({model})
    }
    
    template(serialized) {
        return _.template(template)(serialized)
    }

    @on('click span.weather-city')
    editName(el) {
        var name = this.model.get('city');
        const editableName = $(el.target);

        editableName.html($('<input/>', {
            type: 'text',
            class: 'weather-city_editable'
        }));
        
        editableName.find('input')
            .focus()
            .val(name)
            .select();
    }

    applyNewName(e) {
        var target = $(e.target);
        if (target.val() === this.model.get('city')) {
            this.suggest.empty();
            this.render();
        }

        router.toCity(target.val());
    }

    @on('keypress .weather-city_editable')
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