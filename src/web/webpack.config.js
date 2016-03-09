'use strict';
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const webpack = require('webpack');

module.exports = {
    entry: [
        'bootstrap-loader/extractStyles',
        'font-awesome-loader!./font-awesome.config.js',
        './app/index.js'
    ],
    output: {
        path: __dirname + '/../../target/www/',
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js/,
                loader: 'babel',
                exclude: /(node_modules)/,
                query: {
                    cacheDirectory: true,
                    plugins: ['transform-decorators-legacy'],
                    presets: ['es2015', 'stage-1']
                }
            },
            {test: /\.scss/, loader: ExtractTextPlugin.extract('style', 'css!sass')},
            {test: /\.html/, loader: 'html'},
            {test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "url?limit=10000"},
            {test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/, loader: 'file'},
            {test: /bootstrap-sass\/assets\/javascripts\//, loader: 'imports?jQuery=jquery'}
        ]
    },
    devtool: 'source-map',
    plugins: [
        new HtmlWebpackPlugin({
            template: './app/index.tpl.html',
            inject: 'body'
        }),
        new ExtractTextPlugin('styles.css', {allChunks: true})
    ],

    devServer: {
        port: 3000,
        proxy: {
            '/api/*': {
                target: 'http://localhost:8080/'
            }
        }
    }
};