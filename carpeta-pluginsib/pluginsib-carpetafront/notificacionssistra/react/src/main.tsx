/**
 * @author anadal
 * @email
 * @create date 2023-03-31 12:12:14
 * @modify date 2023-03-31 12:12:14
 * @desc [description]
 */

import React from "react";
import ReactDOM from "react-dom";
import NotificacionsSistra from "./NotificacionsSistra";
import i18n from "./i18n";

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(
    <NotificacionsSistra
      pathtoservei={props.pathtoservei}
      titles={props.titles}
      subtitles={props.subtitles}
      comunicacionesUrl={props.comunicacionesUrl}
    />,
    document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18n.changeLanguage(lng);
};
