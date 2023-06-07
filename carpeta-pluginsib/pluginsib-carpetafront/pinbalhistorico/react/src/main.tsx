import ReactDOM from "react-dom";
import DatosHistorico from "./DatosHistorico";
import i18n from './i18n';


declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(<DatosHistorico pathtoservei={props.pathtoservei} titles={props.titles} subtitles={props.subtitles} localitats={props.localitats}/>, document.getElementById(nomComponent));
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18n.changeLanguage(lng);
};