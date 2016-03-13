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
    template(serialized) {
        return _.template(itemtemplate)(serialized)
    }
}

@className('city-suggest')
export default class CitiesSuggestView extends CollectionView {
    childView = CitySuggestView;

    constructor(query) {
        super({collection: new CitySuggestCollection(query)});
    }

    onRender() {
        this.collection.fetch();
    }
}