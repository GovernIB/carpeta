const path = require("path");

const config = {
  entry: "./src/main.tsx",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "reactjs_main.js",
  },

  target: "node",
  mode: "development",
  resolve: {
    extensions: [".js", ".jsx", ".ts", ".tsx"],
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx|tsx)$/,
        use: [
          {
            loader: "ts-loader",
            options: {},
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
        use: [{ loader: "style-loader" }, { loader: "css-loader" }],
      },
    ],
  },
};

module.exports = config;
