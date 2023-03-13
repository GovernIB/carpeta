/**
 * @author anadal
 *
 * @create date 2023-03-08 08:36:52
 * @modify date 2023-03-08 08:36:52
 * @desc [description]
 */

import React from "react";
import ReactDOM from "react-dom";
import Regweb from "./Regweb";
import i18n from "./i18n";

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(
    <Regweb
      pathtoservei={props.pathtoservei}
      titles={props.titles}
      subtitles={props.subtitles}
      numeroregistro={props.numeroregistro}
      detallpathtoservei={props.detallpathtoservei}
    />,
    document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18n.changeLanguage(lng);
};
