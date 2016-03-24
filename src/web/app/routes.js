import {Router, history} from 'backbone';

class AppRouter extends Router {
    constructor() {
        super({
            routes: {
                '*default': 'default'
            }
        });
    }

    saveState(cities) {
        this.navigate(`?cities=${cities}`, {trigger: false})
    }
}

export default new AppRouter();