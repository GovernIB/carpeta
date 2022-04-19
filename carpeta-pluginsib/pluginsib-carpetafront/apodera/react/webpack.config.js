const path = require("path");

const config = {
	entry: "./src/main.js",
	output: {
		path: path.resolve(__dirname, "dist"),
		filename: "apodera_reactjs_main.js"
	},

	module: {
		rules: [
			{
				test: /\.(js|jsx)$/,
				loaders: "babel-loader",
				query: {
					"presets": [
						//"env",
						"@babel/preset-react"
						//"stage-2",
						//"@babel/react"
					]
					//"plugins": [["flow-runtime"], "transform-decorators-legacy"]
				}
			}
			,
			// Load images.
			{
				test: /\.(gif|jpe?g|png)$/,
				loader: 'url-loader?limit=25000',
				query: {
					limit: 10000,
					name: 'static/media/images/[name].[hash:8].[ext]'
				}
			}
			,
			// css files
			{
				test: /\.css$/i,
				use: ["style-loader", "css-loader"],
			}
		]
	}
};

module.exports = config;