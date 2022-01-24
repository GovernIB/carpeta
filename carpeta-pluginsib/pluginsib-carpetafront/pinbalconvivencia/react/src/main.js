import React from "react";
import ReactDOM from "react-dom";
import DatosConvivencia from "./DatosConvivencia";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<DatosConvivencia pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} localitats={props.localitats}/>, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};