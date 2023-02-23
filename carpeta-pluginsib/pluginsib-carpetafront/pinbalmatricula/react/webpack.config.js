const path = require("path");

const config = {
	entry: "./src/main.tsx",
	output: {
		path: path.resolve(__dirname, "dist"),
		filename: "reactjs_main.js"
	},
	target: "node",
  	mode: "production",
  	resolve: {
    	extensions: [".js", ".jsx", ".ts", ".tsx"],
  	},
	module: {
		rules: [
			{
				test: /\.(js|jsx|tsx)$/,
		        include: path.resolve(__dirname, 'src'),
				use: [
					{
					  loader: "ts-loader",
					  options: {},
					},
				  ]
			}
			,
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
				]
			}
		]
	}
};

module.exports = config;