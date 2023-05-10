import React from "react";
import ReactDOM from "react-dom";
import ReprendreTramit from "./ReprendreTramit";
import i18next from './i18n';


declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(<ReprendreTramit pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18next.changeLanguage(lng);
};