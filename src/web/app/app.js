import {Application} from 'backbone.marionette'

import WeatherView from './components/WeatherView/WeatherView'
import router from './routes';
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

export {App};