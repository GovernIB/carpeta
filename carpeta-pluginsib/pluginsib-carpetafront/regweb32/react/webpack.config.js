const path = require("path");

const config = {
  entry: "./src/main.tsx",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "regweb32_reactjs_main.js",
  },
  target: "node",
  mode: "development",
  resolve: {
    extensions: [".js",".jsx", ".ts", ".tsx"],
  },

  module: {
    rules: [
      {
        test: /\.(js|jsx|ts|tsx)$/,
        include: path.resolve(__dirname, "src"),
        use: [
          {
            loader: "ts-loader",
            options: {
              // skip typechecking for speed
              transpileOnly: true
            },
          },
        ],
      },
      /*
      {
				test: /\.(js|jsx)$/,
        include: path.resolve(__dirname, 'src'),
				use: [
					{
					  loader: "babel-loader",
					  options: {
						presets: [
						  "@babel/preset-react",
						],
					  },
					},
				  ]
			},
      */
      // Load images.
      {
        test: /\.(gif|jpe?g|png)$/,
        include: path.resolve(__dirname, "src"),
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
