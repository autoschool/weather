'use strict';
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const webpack = require('webpack');
const path = require('path');

const PATHS = {
    build: path.join(
        __dirname,
        'build',
        'dist'
    )
};

module.exports = {
    entry: {
        main: [
            'bootstrap-loader/extractStyles',
            './src/main/js/app/index.js'
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
            template: './src/main/js/app/index.tpl.html',
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
