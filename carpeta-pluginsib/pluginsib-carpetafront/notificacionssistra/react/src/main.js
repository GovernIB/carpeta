import React from "react";
import ReactDOM from "react-dom";
import NotificacionsSistra from "./NotificacionsSistra";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<NotificacionsSistra pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} comunicacionesUrl={props.comunicacionesUrl} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};