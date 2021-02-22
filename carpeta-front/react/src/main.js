import React, { Component } from "react";
import ReactDOM from "react-dom";

import Index from './Index';
import { HashRouter } from "react-router-dom";
/**
 * 
 * @author anadal Migracio A Routes i passar de index.jsp a Index.js
 */


ReactDOM.render(<HashRouter ><Index /></HashRouter>, document.getElementById('fullpagecarpetafront'));

