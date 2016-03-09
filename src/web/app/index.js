'use strict';
import '../node_modules/weather-icons/sass/weather-icons.scss'
import './index.scss'
import $ from 'jquery';
import {Application} from 'backbone.marionette'
import WeatherView from './components/WeatherView/WeatherView'
import router from './routes';
import Backbone from 'backbone';
import url from 'url'

const App = new Application({
    regions: {
        content: '#content'
    }
});

App.on('start', () => {

    router.on('route:default', (path, query) => {
        App.content.show(new WeatherView(url.parse('?' + query, true).query));
    });
    
});

App.start();
Backbone.history.start();

window.jQuery = $;
