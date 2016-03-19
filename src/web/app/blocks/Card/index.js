import './styles.scss'

import {Model} from 'backbone'
import {LayoutView} from 'backbone.marionette'
import {region} from '../../decorators'
import _ from 'underscore'

import template from './template.html'

export default class Card extends LayoutView {

    @region('.card-title__primary')
    titlePrimary;

    @region('.card-title__secondary')
    titleSecondary;

    @region('.card-text')
    text;

    @region('.card-actions')
    actions;

    template = _.template(template);

    constructor(options) {
        super(options);
        this.templateHelpers = {
            actionsEnabled: true
        }
    }

    onRender() {
        this.titlePrimary.show(this.getTitlePrimaryView());
        this.titleSecondary.show(this.getTitleSecondaryView());
        this.text.show(this.getTextView());
        this.actions.show(this.getActionsView());
    }

    getTitlePrimaryView() {

    }

    getTitleSecondaryView() {

    }

    getTextView() {

    }

    getActionsView() {

    }
}