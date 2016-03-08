'use strict';
import $ from 'jquery';
import {Application} from 'backbone.marionette'
import WeatherView from './components/WeatherView/WeatherView'


const App = new Application({
    regions: {
        content: '#content'
    }
});

App.on('start', () => {
    let view = new WeatherView();

    App.content.show(view);
});

App.start();

window.jQuery = $;
