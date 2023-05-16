import React from "react";
import ReactDOM from "react-dom";
import DatosAyudaSubvenciones from "./DatosAyudaSubvenciones";
import i18n from './i18n';

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}


newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(<DatosAyudaSubvenciones pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18n.changeLanguage(lng);
};