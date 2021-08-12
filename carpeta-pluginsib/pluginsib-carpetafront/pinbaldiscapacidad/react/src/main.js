import React from "react";
import ReactDOM from "react-dom";
import DatosDiscapacidad from "./DatosDiscapacidad";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<DatosDiscapacidad pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};