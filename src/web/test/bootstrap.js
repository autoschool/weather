process.env.NODE_PATH = 'src';
module.constructor._initPaths();

require('require-ext');
require('babel-core/register')({presets: ['es2015', 'stage-1'], plugins: ['transform-decorators-legacy']});
global.dump = require('debug')('weather:test');

var jsdom = require('jsdom').jsdom;

global.document = jsdom('<html><head></head><body></body></html>', {});
global.window = global.document.defaultView;
global.navigator = global.window.navigator;
global.location = global.window.location;
global.$ = require('jquery');
global._ = require('lodash');
global.Backbone = require('backbone');
window.Marionette = require('backbone.marionette');


require.extensions['.css'] = function () {
};
require.extensions['.scss'] = function () {
};


