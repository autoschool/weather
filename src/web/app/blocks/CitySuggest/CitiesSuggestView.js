import './CitiesSuggestView.scss'

import {ItemView} from 'backbone.marionette'
import {CollectionView} from 'backbone.marionette'
import CitySuggestCollection from '../../data/CitySuggestsCollection'
import _ from 'underscore'
import {on} from 'backbone-decorators'
import {className} from '../../decorators'
import $ from 'jquery'

import itemtemplate from './CitySuggestView.html'

@className('city')
class CitySuggestView extends ItemView {
    template = _.template(itemtemplate);
}

@className('city-suggest')
export default class CitiesSuggestView extends CollectionView {
    childView = CitySuggestView;

    constructor(query) {
        super({collection: new CitySuggestCollection(query)});
    }

    onRender() {
        this.collection.fetch().then(()=> {
            if (this.collection.length == 0) {
                 this.hideSuggest();
            }
        });
    }
    
    hideSuggest() {
        this.el.style.display = 'none'; 
    }
   
    @on('mousedown .city')
    goToCity(e) {
        e.stopPropagation();
        this.hideSuggest();
        this.triggerMethod('clicked:city', e && $(e.target).find('.city__name').data('city'))
    }
    
    subscribeOnClick(func) {
        this.on('clicked:city', func);
    }
}