import './CitiesSuggestView.scss'

import {ItemView} from 'backbone.marionette'
import {CollectionView} from 'backbone.marionette'
import CitySuggestCollection from '../../data/CitySuggestsCollection'
import _ from 'underscore'
import {on} from 'backbone-decorators'
import {className} from '../../decorators'

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
    suggestClicked(e) {
        e.stopPropagation();
        var city = this.$(e.currentTarget).find('.city__name').data('city');
        this.triggerMethod('clicked:city', city);
        this.hideSuggest();
    }
    
    subscribeOnClick(func) {
        this.on('clicked:city', func);
    }
}