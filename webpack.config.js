var path = require('path');

module.exports = {
  entry: './sneakysneaksGUI/src/index.js',
  devtool: 'source-map',
  cache: true,
  mode: 'development',
  target: 'web',
  output: {
    path: __dirname,
    filename: './src/main/resources/static/built/bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: ["@babel/preset-env", "@babel/preset-react"]
          }
        }]
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      }
    ]
  },
  resolve: {
    extensions: ['*', '.js', '.jsx'],
    fallback: {
      "url": false,
      "http": false,
      "https": false,
      "stream": false,
      "crypto": false,
      "net": false,
      "tls": false,
      "fs": false
    }
  }
};
