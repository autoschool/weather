import {Router, history} from 'backbone';

class AppRouter extends Router {
    constructor() {
        super({
            routes: {
                '*default': 'default'
            }
        });
    }

    toCity(city) {
        this.navigate(`?city=${city}`, {trigger: true})
    }
}

export default new AppRouter();