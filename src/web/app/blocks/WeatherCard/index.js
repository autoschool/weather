import {Model} from 'backbone';
import {ItemView} from 'backbone.marionette';
import _ from 'underscore';
import moment from 'moment';
import Card from '../../blocks/Card/index';
import WeatherCardValue from './__Value/index';
import InplaceEditView from '../InplaceEdit/index';
import {on} from 'backbone-decorators'
import {region} from '../../decorators';
import Suggest from '../CitySuggest/CitiesSuggestView';
import {onModel} from 'backbone-decorators';
import './styles.scss';

export default class WeatherCard extends Card {

    @region('.suggest')
    suggest;

    onBeforeRender() {
        this.views = {
            title: new InplaceEditView({model: this.model, field: 'city'}),
            text: new WeatherCardValue({model: this.model}),
            actions: new ItemView({template: _.template('-'), className: 'remove-card btn btn-default'})
        };

        this.views.title.subscribe((query) => {
            this.showSuggest(query);
        })
    }

    getTitlePrimaryView() {
        return this.views.title;
    }

    getTitleSecondaryView() {
        // fixme в отдельный класс надо бы
        return new ItemView({
            model: new Model({
                dt: moment(this.model.get('dt'), 'X').format('h A, DD MMM YY (Z)')
            }),
            template: _.template('<div><%= dt %></div><div class="suggest"></div>')
        });
    }

    getTextView() {
        return this.views.text;
    }

    getActionsView() {
        return this.views.actions;
    }

    showSuggest(query) {
        if (!this.suggest) {
            return;
        }

        if (query.length > 1) {
            var suggest = new Suggest(query);
            suggest.subscribeOnClick((city) => {
                this.model.set('city', city);
            });
            this.suggest.show(suggest);
        } else {
            this.suggest.empty();
        }
    }

    @onModel('change')
    render() {
        super.render();
    }

    @onModel('change:city')
    refetch(model) {
        model.fetch();
    }
    
    @on('click .remove-card')
    rmCard() {
        this.model.destroy(); 
    }
}