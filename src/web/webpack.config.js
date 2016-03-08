'use strict';
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: [
        'bootstrap-loader',
        'font-awesome-loader',
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
                    presets: ['es2015']
                }
            },
            { test: /\.scss/, loader: 'style!css!sass'},
            { test: /\.html/, loader: 'html'},
            { test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "url?limit=10000"},
            { test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/, loader: 'file'},
            { test: /bootstrap-sass\/assets\/javascripts\//, loader: 'imports?jQuery=jquery' }
        ]
    },
    devtool: 'source-map',
    plugins: [
        new HtmlWebpackPlugin({
            template: './app/index.tpl.html'
        })
    ]
};