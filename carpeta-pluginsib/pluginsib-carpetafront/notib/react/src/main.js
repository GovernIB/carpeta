import React from "react";
import ReactDOM from "react-dom";
import Notib from "./Notib";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<Notib pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} notificacionesUrl={props.notificacionesUrl} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};