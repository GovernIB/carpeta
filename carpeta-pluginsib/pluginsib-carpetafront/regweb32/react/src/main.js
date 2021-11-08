import React from "react";
import ReactDOM from "react-dom";
import Regweb from "./Regweb";
// import DetallRegWeb from "./DetallRegweb";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<Regweb pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

// newInstancePlugin2 = function createReactCompAppIntern(nomComponent, props){
//  ReactDOM.render(<DetallRegWeb pathtoservei={props.pathtoservei} />, document.getElementById(nomComponent));
// };

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};