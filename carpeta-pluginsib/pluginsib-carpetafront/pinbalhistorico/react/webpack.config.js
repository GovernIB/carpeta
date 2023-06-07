const path = require("path");

const config = {
  entry: "./src/main.tsx",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "pinbalhistorico_reactjs_main.js",
  },
  //mode: 'development',
  mode: "production",
  resolve: {
    extensions: [".js", ".jsx", ".ts", ".tsx"],
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx|ts|tsx)$/,
        include: path.resolve(__dirname, "src"),
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
