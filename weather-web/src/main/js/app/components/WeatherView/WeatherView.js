import {onModel, on, onCollection} from 'backbone-decorators';
import {className} from '../../decorators';
import _ from 'underscore'
import WeatherCollection from '../../data/WeatherCollection';
import Weather from '../../data/Weather';
import {CompositeView} from 'backbone.marionette';
import WeatherCard from '../../blocks/WeatherCard/index';
import template from './WeatherView.html'
import router from '../../routes'
import './WeatherView.scss';

@className('weather-cards')
export default class WeatherView extends CompositeView {
    childView = WeatherCard;
    childViewContainer = '.weather-cards__container';
    
    template = _.template(template);

    constructor({cities}) {
        super({
            collection: new WeatherCollection(cities.split(",").map(city => new Weather({city}))),
        });
    }
    
    @onCollection('change:city')
    @onCollection('remove')
    updateUrl() {
        router.saveState(this.collection.map(model => model.get('city')).join(','));
    }
    
    @on('click .new-card') 
    addEmpty() {
         this.collection.add(new Weather({city: 'What a city?'}), {at: 0});
    }

    onRender() {
        this.refetch();
    }

    refetch() {
        this.collection.fetch();
    }
}