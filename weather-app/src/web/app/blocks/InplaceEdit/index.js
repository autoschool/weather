import {ItemView} from 'backbone.marionette'
import _ from 'underscore'
import {on} from 'backbone-decorators'

import template from './template.html'

export default class InplaceEditView extends ItemView {
    template = _.template(template);
    
    initialize() {
        this.options.editable = false;
    }

    @on('click .inplace_displayed')
    startEdit() {
        this.options.editable = true;
        this.render();
        this.$('.inplace').focus();
    }

    @on('blur .inplace_editable')
    submitEdit() {
        this.options.editable = false;
        var value = this.currentValue();
        this.render();
        this.model.set(this.options.field, value);
    }


    @on('keyup .inplace_editable')
    updateInput(e) {
        if (e.keyCode === 13) {
            this.submitEdit();
        }
        this.triggerMethod('input:updated', e.target.value);
    }
    
    subscribe(func) {
        this.on('input:updated', func);
    }

    currentValue() {
        return this.$('.inplace').val();
    }

    serializeData() {
        return {...this.options, ...{value: this.model.get(this.options.field)}};
    }
}