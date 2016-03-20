var webpack = require('./webpack.config.js');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],

        files: [
            'test/**/*.spec.js'
        ],

        exclude: [],
        preprocessors: {
            'test/**/*.spec.js': ['webpack', 'sourcemap']
        },

        webpack: {
            module: {
                loaders: webpack.module.loaders
            },
            devtool: 'inline-source-map',
            plugins: [
                new ExtractTextPlugin('styles.css', {allChunks: true})
            ]
        },

        webpackServer: {
            noInfo: true
        },

        reporters: ['progress', 'html'],
        port: 9876,
        colors: true,

        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        autoWatch: true,
        browsers: ['Firefox'],
        singleRun: false
        
    })
};
