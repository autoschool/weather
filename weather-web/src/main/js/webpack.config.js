'use strict';
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const webpack = require('webpack');
const path = require('path');

const PATHS = {
    build: path.join(
        __dirname,
        '..',
        '..',
        '..',
        'target',
        'classes',
        'META-INF',
        'resources'
    )
};

module.exports = {
    entry: {
        main: [
            'bootstrap-loader/extractStyles',
            './app/index.js'
        ],
        vendor: [
            'url',
            'backbone',
            'backbone.marionette',
            'jquery' 
        ]
    },
    output: {
        path: PATHS.build,
        filename: '[name].js',
        chunkFilename: '[id].js'
    },
    module: {
        loaders: [
            {
                test: /\.js/,
                loader: 'babel',
                exclude: /(node_modules)/,
                query: {
                    cacheDirectory: true
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
        new ExtractTextPlugin('styles.css', {allChunks: true}),
        new webpack.optimize.CommonsChunkPlugin('vendor', 'vendor.js')
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
