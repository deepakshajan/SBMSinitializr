const REACT_HOME =  __dirname + '/src/main/resources/react/';
const REACT_OUTPUT =  __dirname + '/src/main/webapp/';

var path = require('path');
var config = {
    entry: REACT_HOME+'react-main.js',
    output: {
        path: REACT_OUTPUT,
        filename: 'react-output.js',
    },
    devServer: {
        inline: true,
        port: 8080,
        contentBase: REACT_HOME,
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /node_modules/,
                loader: 'babel-loader',
                query: {
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
}
module.exports = config;