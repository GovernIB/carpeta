/**
 * @author anadal
 * @create date 2022-08-29 09:08:12
 * @modify date 2022-08-29 09:08:12
 */

import ReactDOM from "react-dom";
import ElsMeusExpedients from "./ElsMeusExpedients";
import i18n from "./i18n";

declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(
    <ElsMeusExpedients pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} />,
    document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18n.changeLanguage(lng);
};
