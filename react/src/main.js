import React, { Component } from "react";
import ReactDOM from "react-dom";
import './i18n';
import MenuLateral from './MenuLateral';
import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
import Peu from './Peu';
import MollaPa from './MollaPa';
import Inici from './Inici';
import Accessibilitat from './Accessibilitat';
import TramitsPendents from './TramitsPendents';
import Registres from './Registres';
import DetallRegistre from './DetallRegistre';
import Notificacions from './Notificacions';
import MapaWeb from './MapaWeb';
import DadesPersonals from './DadesPersonals';
import Breadcrumb from './Breadcrumb';
import * as breadcrumbPaths from './utils/breadcrumbPaths';



ReactDOM.render(
  <MenuLateral />,
  document.getElementById("menuLateral")
);

ReactDOM.render(
  <BarraMenu />,
  document.getElementById("barraMenu")
);

ReactDOM.render(
  <MenuDesllisant autenticat='0'/>,
  document.getElementById("menuDesllisant")
);

ReactDOM.render(
  <Peu />,
  document.getElementById("peu")
);

ReactDOM.render(
  <MollaPa />,
  document.getElementById("mollaPa")
);

ReactDOM.render(
  <Breadcrumb items={breadcrumbPaths.Inici} autenticat='0'/>,
  document.getElementById("mollaPa")
);

ReactDOM.render(
  <Inici autenticat='0'/>,
  document.getElementById("contingut")
);


newIniciReact  = function createReactCompInici(nomComponent, param) {
    ReactDOM.render(<Inici autenticat={param}/>, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Inici} autenticat={param}/>, document.getElementById("mollaPa"));
};

newAccessibilitatReact  = function createReactCompAccessibilitat(nomComponent, param) {
    ReactDOM.render(<Accessibilitat />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Accessibilitat} autenticat={param}/>, document.getElementById("mollaPa"));
};

newTramitsPendentsReact  = function createReactCompTramitsPendents(nomComponent, param) {
    ReactDOM.render(<TramitsPendents />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.TramitsPendents} autenticat={param}/>, document.getElementById("mollaPa"));
};

newRegistresReact  = function createReactCompRegistres(nomComponent, param) {
    ReactDOM.render(<Registres />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Registres} autenticat={param}/>, document.getElementById("mollaPa"));
};

newDetallRegistreReact  = function createReactCompDetallRegistre(nomComponent, param) {
    ReactDOM.render(<DetallRegistre id={param} />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.DetallRegistre} autenticat={param}/>, document.getElementById("mollaPa"));
};

newNotificacionsReact  = function createReactCompNotificacions(nomComponent, param) {
    ReactDOM.render(<Notificacions />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.Notificacions} autenticat={param}/>, document.getElementById("mollaPa"));
};

newMapaWebReact  = function createReactCompMapaWeb(nomComponent, param) {
    ReactDOM.render(<MapaWeb />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.MapaWeb} autenticat={param}/>, document.getElementById("mollaPa"));
};

newDadesPersonalsReact  = function createReactCompDadesPersonals(nomComponent, param) {
    ReactDOM.render(<DadesPersonals />, document.getElementById(nomComponent));
	ReactDOM.render(<Breadcrumb items={breadcrumbPaths.DadesPersonals} autenticat={param}/>, document.getElementById("mollaPa"));
};

newMenuDesllisantReact  = function createReactCompMenuDesllisant(nomComponent, param) {
    ReactDOM.render(<MenuDesllisant autenticat={param}/>, document.getElementById(nomComponent));
};
