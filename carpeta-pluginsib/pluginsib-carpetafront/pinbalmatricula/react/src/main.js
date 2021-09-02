import React from "react";
import ReactDOM from "react-dom";
import DatosMatricula from "./DatosMatricula";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<DatosMatricula pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};