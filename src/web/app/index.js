'use strict';
import $ from 'jquery';
import WeatherView from './components/WeatherView/WeatherView'

const view = new WeatherView();
view.render();
$('#content').append(view.el);

window.jQuery = $;
