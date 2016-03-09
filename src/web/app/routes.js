import {Router, history} from 'backbone';

class AppRouter extends Router {
    constructor() {
        super({
            routes: {
                '*default': 'default'
            }
        });
    }
}

export default new AppRouter();