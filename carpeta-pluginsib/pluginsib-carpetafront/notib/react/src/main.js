import React from "react";
import ReactDOM from "react-dom";
import Notib from "./Notib";
import i18n from './i18n';

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {
  ReactDOM.render(<Notib pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} notificacionesUrl={props.notificacionesUrl} comunicacionesTodasUrl={props.comunicacionesTodasUrl} notificacionesPendientesUrl={props.notificacionesPendientesUrl} notificacionesLeidasUrl={props.notificacionesLeidasUrl} comunicacionesPendientesUrl={props.comunicacionesPendientesUrl} comunicacionesLeidasUrl={props.comunicacionesLeidasUrl} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {
  i18n.changeLanguage(lng);
};