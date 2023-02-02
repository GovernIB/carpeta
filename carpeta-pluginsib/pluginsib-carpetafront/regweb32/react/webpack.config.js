const path = require("path");

const config = {
	entry: "./src/main.js",
	output: {
		path: path.resolve(__dirname, "dist"),
		filename: "regweb32_reactjs_main.js"
	},
	mode: 'production',
	resolve: {
		fallback: {
			url: false,
			http: require.resolve('stream-http'),
            https: require.resolve('https-browserify'),
			buffer: false,
			stream: false,
			assert: false,
            crypto: false,
			zlib: false,
			util: false,
			tty: false,
			os: false,

		}
	},

	module: {
		rules: [
			{
				test: /\.(js|jsx)$/,
                include: path.resolve(__dirname, 'src'),
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
			,
			// css files
			{
				test: /\.css$/i,
				use: [
					{ loader: "style-loader"},
					{ loader: "css-loader" }
				],
			}
		]
	}
};

module.exports = config;