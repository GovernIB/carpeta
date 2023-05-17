const path = require("path");

const config = {
  entry: "./src/main.js",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "pinbalconvivencia_reactjs_main.js",
  },
  mode: 'production',
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        include: path.resolve(__dirname, "src"),
        use: [
          {
            loader: "babel-loader",
            options: {
				"presets": [
					//"env",
					"@babel/preset-react"
					//"stage-2",
					//"@babel/react"
				]
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
    ],
  },
};

module.exports = config;
