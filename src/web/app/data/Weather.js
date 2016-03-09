import {Model} from 'backbone'

export default class Weather extends Model {
    urlRoot() {
        return '/api/weather'; 
    }
    
    defaults() {
        return {
            city: '',
            temperatures: [{
                unit: '',
                value: 0
            }]
        }
    }
}