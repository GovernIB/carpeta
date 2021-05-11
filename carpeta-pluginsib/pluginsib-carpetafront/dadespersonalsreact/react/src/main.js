import React from "react";
import ReactDOM from "react-dom";
import i18n from './i18n';
import Pipelles from './Pipelles';


//i18n.on('languageChanged', function(lng) { alert('Idioma canviat a ' + lng );});

//i18n.on('languageChanged', function(lng) { alert('XXXXXXXXXXXX  Idioma canviat a ' + lng );});


// console.log(" +++++++++++++++   CARREGANT COMPONENT 1 ¿¿¿¿¿¿¿¿¿¿¿");

newInstancePlugin = function createReactCompAppIntern(nomComponent, props) {

  ReactDOM.render(<Pipelles dades={props} />, document.getElementById(nomComponent));
};


// console.log(" +++++++++++++++   CARREGANT COMPONENT 2 ¿¿¿¿¿¿¿¿¿¿¿");

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng) {

  i18n.changeLanguage(lng);
};



console.log(" +++++++++++++++   FINAL CARREGA DE COMPONENTS !!!!!!!");