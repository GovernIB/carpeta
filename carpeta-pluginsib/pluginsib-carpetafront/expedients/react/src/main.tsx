/**
 * @author anadal
 * @create date 2022-08-29 09:08:12
 * @modify date 2022-08-29 09:08:12
 */
import React from "react";
import ReactDOM from "react-dom";
import ElsMeusExpedients from "./ElsMeusExpedients";
import i18next from "./i18n";

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(
  nomComponent: string,
  props: any
) {
  ReactDOM.render(
    <React.StrictMode>
      <ElsMeusExpedients
        pathtoservei={props.pathtoservei}
        titles={props.titles}
        subtitles={props.subtitles}
      />
    </React.StrictMode>,
    document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18next.changeLanguage(lng);
};
