/**
 * @author anadal
 * @email
 * @create date 2023-03-31 12:12:14
 * @modify date 2023-03-31 12:12:14
 * @desc [description]
 */

import React from "react";
import ReactDOM from "react-dom";
import Sistra from "./Sistra";
import i18n from "./i18n";

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(<Sistra detallpathtoservei={props.detallpathtoservei} pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng:string) {
  i18n.changeLanguage(lng);
};