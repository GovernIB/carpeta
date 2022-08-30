const path = require("path");

const config = {
  entry: "./src/main.js",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "apodera_reactjs_main.js",
  },
  
  mode: 'development',

  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        use: [
          {
            loader: "babel-loader",
            options: {
              presets: [
                //"env",
                "@babel/preset-react",
                //"stage-2",
                //"@babel/react"
              ],
              //"plugins": [["flow-runtime"], "transform-decorators-legacy"]
            },
          },
        ],
      },
      // Load images.
      {
        test: /\.(gif|jpe?g|png)$/,
        use: [
          {
            loader: "url-loader?limit=25000",
            options: {
              limit: 10000,
              name: "static/media/images/[name].[hash:8].[ext]",
            },
          },
        ],
      },
      // css files
      {
        test: /\.css$/i,
        use: [
			{ loader: "style-loader"},
			{ loader: "css-loader" }
		],
      },
    ],
  },
};

module.exports = config;
