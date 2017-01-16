'use strict';

import '../../../../node_modules/weather-icons/sass/weather-icons.scss'
import './index.scss'
import $ from 'jquery';

import Backbone from 'backbone';
import {App} from './app'

App.start();
Backbone.history.start();

window.jQuery = $;
