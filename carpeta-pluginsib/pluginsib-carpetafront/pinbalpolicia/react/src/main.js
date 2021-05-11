import React from "react";
import ReactDOM from "react-dom";
import DadesPolicia from "./DadesPolicia";
import i18n from './i18n';


// console.log(" +++++++++++++++   CARREGANT COMPONENT 1 ¿¿¿¿¿¿¿¿¿¿¿");

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {

  ReactDOM.render(<DadesPolicia pathtodocumentidentitat={props.pathtodocumentidentitat} titles={props.titles} subtitles={props.subtitles} />, document.getElementById(nomComponent));
};


// console.log(" +++++++++++++++   CARREGANT COMPONENT 2 ¿¿¿¿¿¿¿¿¿¿¿");

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {

  i18n.changeLanguage(lng);
};



// console.log(" +++++++++++++++   FINAL CARREGA DE COMPONENTS !!!!!!!");