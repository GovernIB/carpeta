import ReactDOM from "react-dom";
import Notib from "./Notib";
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
    <Notib
      pathtoservei={props.pathtoservei}
      titles={props.titles}
      subtitles={props.subtitles}
      notificacionesTodasUrl={props.notificacionesTodasUrl}
      comunicacionesTodasUrl={props.comunicacionesTodasUrl}
      notificacionesPendientesUrl={props.notificacionesPendientesUrl}
      notificacionesLeidasUrl={props.notificacionesLeidasUrl}
      comunicacionesPendientesUrl={props.comunicacionesPendientesUrl}
      comunicacionesLeidasUrl={props.comunicacionesLeidasUrl}
      pathtoserveiPendientesUrl={props.pathtoserveiPendientesUrl}
      pathtoserveiLeidasUrl={props.pathtoserveiLeidasUrl}
    />,
    document.getElementById(nomComponent)
  );
};

changeLanguagePlugin = function changeLanguageReactCompAppIntern(lng: string) {
  i18next.changeLanguage(lng);
};
