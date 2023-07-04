/**
 * @author fbosch
 * @email [example@mail.com]
 * @create date 2023-06-21 13:53:14
 * @modify date 2023-06-21 13:53:14
 * @desc [description]
 **/

import ReactDOM from "react-dom";
import DadesPolicia from "./DadesPolicia";
import i18next from './i18n';


declare global {
  var newInstancePlugin: Function;
  var changeLanguagePlugin: Function;
}

newInstancePlugin = function createReactCompAppIntern(nomComponent: string, props: any) {
  ReactDOM.render(<DadesPolicia pathtodocumentidentitat={props.pathtodocumentidentitat} titles={props.titles} subtitles={props.subtitles} />, 
  document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18next.changeLanguage(lng);
};
