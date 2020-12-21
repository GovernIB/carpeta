const path = require("path");

const config = {
	entry: "./src/main.js",
	output: {
	  path: path.resolve(__dirname, "dist"),
	  filename: "reactjs_main.js"
},

module: {
  rules: [
  {
      test:/\.(js|jsx)$/,
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
]
}
};

module.exports = config;