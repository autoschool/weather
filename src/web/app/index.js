'use strict';
import './index.scss'
import $ from 'jquery';
import {Application} from 'backbone.marionette'
import WeatherView from './components/WeatherView/WeatherView'
import router from './routes';
import Backbone from 'backbone';
import URI from 'urijs'

const App = new Application({
    regions: {
        content: '#content'
    }
});

App.on('start', () => {

    router.on('route:default', (path, query) => {
        App.content.show(new WeatherView(new URI('?' + query).query(true)));
    });
    
});

App.start();
Backbone.history.start();

window.jQuery = $;
