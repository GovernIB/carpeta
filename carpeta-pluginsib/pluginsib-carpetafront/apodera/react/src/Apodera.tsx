/**
 * @author anadal, jpernia
 * @create date 2022-08-29 08:51:48
 * @modify date 2022-08-29 08:51:48
 */
import React from "react";
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import * as reactdetect from "react-device-detect";
import { TemplatePageCarpeta, RenderTable, RowTypeUtils, RowType } from "carpetacommonreactlib";

//import * as $ from 'jquery';

interface ApoderaProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

type ApoderaState = {
  isLoaded: boolean;
  dataApoderaments: any;
  dataPoderdant: any;
  totalComPoderdant: number;
  totalComApoderat: number;
  error: string | null;
  urlApodera: string | URL | undefined;
  entitat: string | null;
};

type ApoderaListItem = {
  apoderaTipus: string;
  apoderaAmbit: string;
  apoderaEstat: string;
  apoderaEstatActual?: JSX.Element;
  apoderaPoderdante: JSX.Element;
  apoderaApoderado: JSX.Element;
  apoderaVigencia: string;
  apoderaBoto?: JSX.Element;
  apoderaOrganismes?: JSX.Element[];
  apoderaProcediments?: JSX.Element[];
  apoderaTipusFila?: JSX.Element;
};

class Apodera extends React.Component<ApoderaProps, ApoderaState> {
  constructor(props: ApoderaProps) {
    super(props);

    this.state = {
      isLoaded: false,
      dataApoderaments: null,
      dataPoderdant: null,
      totalComPoderdant: 0,
      totalComApoderat: 0,
      error: null,
      urlApodera: undefined,
      entitat: null,
    };

    this.nomEstat = this.nomEstat.bind(this);
    this.nomEstatActual = this.nomEstatActual.bind(this);
    this.descripcioEstat = this.descripcioEstat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    this.carregaDadesApoderaments = this.carregaDadesApoderaments.bind(this);

    i18n.on("languageChanged", this.canviatIdioma);
  }

  canviatIdioma(lng: string) {
    console.log("Apodera::PASSA PER LISTENER canviatIdioma() ...");
    this.carregaDadesApoderaments();
  }

  nomEstat(estat: string) {
    let estatNom = i18n.t("apoderaEstadoDesconocido");
    if (estat != null) {
      switch (estat) {
        case "1":
          estatNom = i18n.t("apoderaEstat1");
          break;
        case "2":
          estatNom = i18n.t("apoderaEstat2");
          break;
        case "3":
          estatNom = i18n.t("apoderaEstat3");
          break;
        case "4":
          estatNom = i18n.t("apoderaEstat4");
          break;
        case "5":
          estatNom = i18n.t("apoderaEstat5");
          break;
        case "6":
          estatNom = i18n.t("apoderaEstat6");
          break;
      }
    }
    return estatNom;
  }

  nomEstatActual(estat: string) {
    return (
      <>
        {this.nomEstat(estat)} - {this.descripcioEstat(estat)}
      </>
    );
  }

  descripcioEstat(estat: string) {
    let descripcioNom = i18n.t("apoderaEstadoDesconocido");
    if (estat != null) {
      switch (estat) {
        case "1":
          descripcioNom = i18n.t("apoderaDescripcioEstat1");
          break;
        case "2":
          descripcioNom = i18n.t("apoderaDescripcioEstat2");
          break;
        case "3":
          descripcioNom = i18n.t("apoderaDescripcioEstat3");
          break;
        case "4":
          descripcioNom = i18n.t("apoderaDescripcioEstat4");
          break;
        case "5":
          descripcioNom = i18n.t("apoderaDescripcioEstat5");
          break;
        case "6":
          descripcioNom = i18n.t("apoderaDescripcioEstat6");
          break;
      }
    }
    return descripcioNom;
  }

  componentDidMount() {
    this.carregaDadesApoderaments();
  }

  carregaDadesApoderaments() {
    console.log("Apodera::carregaDadesApoderaments => ENTRA");

    const url = this.props.pathtoservei + "?lang=" + i18n.language;

    console.log("Apodera::carregaDadesApoderaments => CRIDANT A " + url);

    axios
      .get(url)
      .then((res) => {
        this.setState({
          ...this.state,
          isLoaded: true,
          dataApoderaments: this.processarDadesApoderaments(res.data.apoderaments, res.data.poderdant),
          dataPoderdant: res.data.poderdant,
          totalComPoderdant: res.data.totalComPoderdant,
          totalComApoderat: res.data.totalComApoderat,
          error: null,
          urlApodera: res.data.urlApodera,
          entitat: res.data.entitat,
        });
      })
      .catch((error) => {
        // console.log("ERROR: " + JSON.stringify(error));
        if (error.response) {
          console.log("error.response.data: " + error.response.data);
          console.log("error.response.status: " + error.response.status);
          console.log("error.response.headers: " + error.response.headers);
        }
        if (JSON.stringify(error).toString().includes("Request failed with status code 500")) {
          var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", "");
          errorPantalla = errorPantalla.replace("</body></html>", "");
          this.setState({
            isLoaded: true,
            dataApoderaments: null,
            dataPoderdant: null,
            totalComPoderdant: 0,
            totalComApoderat: 0,
            error: errorPantalla,
            urlApodera: undefined,
            entitat: null,
          });
        } else {
          this.setState({
            dataApoderaments: null,
            dataPoderdant: null,
            totalComPoderdant: 0,
            totalComApoderat: 0,
            error: JSON.stringify(error).toString(),
            isLoaded: true,
            urlApodera: undefined,
            entitat: null,
          });
        }
      });
  }

  processarDadesApoderaments(apoderamentsRest: any[], dataPoderdant: any): ApoderaListItem[] {
    let apoderaments: ApoderaListItem[] = [];

    const { t } = this.props;

    let accedirLabel: string = t("apoderaAccedirApoderament");

    let isDesktop = !reactdetect.isMobileOnly;

    let nif: string;
    if (dataPoderdant.personaFisica) {
      nif = dataPoderdant.personaFisica.nifNie;
    } else {
      nif = dataPoderdant.personaJuridica.nif;
    }

    let apoderaBotoContent: JSX.Element = (
      <div style={{ width: "auto", float: "right", position: "relative", top: "0px" }} id="accedirApodera">
        <span>
          <button
            className="btn btn-primary carpeta-btn botoAccedirCarpeta"
            title={accedirLabel}
            aria-labelledby="accedirApodera"
            onClick={() => {
              window.open(this.state.urlApodera, "_blank");
            }}
          >
            {t("apoderaBotoApoderament")}&nbsp;&nbsp;
            {RowTypeUtils.getIcon(RowType.EXTERNAL_LINK, false)}
          </button>
        </span>
      </div>
    );

    {
      apoderamentsRest.map(
        (
          { tipus, subtipus, estat, apoderado, poderdante, vigencia, procediments, organismes, tramits }: any,
          i: number
        ) => {
          let apoderado2 = this.addNewLine(apoderado, nif, isDesktop);
          let poderdante2 = this.addNewLine(poderdante, nif, isDesktop);

          let element: ApoderaListItem = {
            apoderaTipus: tipus,
            apoderaAmbit: subtipus,
            apoderaEstat: this.nomEstat(estat),
            apoderaEstatActual: this.nomEstatActual(estat),
            apoderaPoderdante: poderdante2,
            apoderaApoderado: apoderado2,
            apoderaVigencia: vigencia,
            apoderaOrganismes: this.list2Html(organismes),
            apoderaProcediments: this.list2Html(procediments),
            apoderaBoto: apoderaBotoContent,
          };

          apoderaments.push(element);
        }
      );
    }

    return apoderaments;
  }

  addBold(str: string, nif: string) {
    if (str.includes(nif)) {
      return <b> {str} </b>;
    } else {
      return str;
    }
  }

  addNewLine(str: string, nif: string, isDesktop: boolean) {
    var index = str.indexOf(" - ");
    var nom = str.substr(0, index);
    var fisicajuridica = str.substr(index + 3);

    var bold;
    if (str.includes(nif)) {
      bold = "bold";
    } else {
      bold = "normal";
    }

    const tabulador = isDesktop ? <></> : <>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</>;
    var tmp = (
      <span style={{ fontWeight: bold }}>
        <p style={{ margin: 0 }}>
          {tabulador} {nom}
        </p>
        <i>
          {tabulador}
          {fisicajuridica}
        </i>
      </span>
    );

    return tmp;
  }

  list2Html(stringList: any): JSX.Element[] {
    let htmlCode: JSX.Element[] = [];
    {
      stringList &&
        stringList.forEach((s: any, x: number) => {
          htmlCode.push(
            <p style={{ margin: 0 }} key={x}>
              - {s}
            </p>
          );
        });
    }
    return htmlCode;
  }

  render() {
    const { t, i18n } = this.props;
    let content;

    if (!this.state.isLoaded) {
      content = (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );
    } else {
      if (this.state.error) {
        content = (
          <div className="alert alert-danger" role="alert">
            {this.state.error}
          </div>
        );
      } else {
        if (
          this.state.dataApoderaments == null ||
          (this.state.dataApoderaments != null && this.state.dataApoderaments.length == 0)
        ) {
          content = (
            <div className="pt-3 alert alert-secondary" style={{ float: "left", width: "95%" }} role="alert">
              {t("apoderaBuid")}
            </div>
          );
        } else {
          // OK Tenim dades

          let informacioAddicional;

          let columnsNomAddicionals = undefined;
          let columnsTitolsAddicionals = undefined;
          let columnsNom = [
            "apoderaTipus",
            "apoderaAmbit",
            "apoderaEstat",
            "apoderaPoderdante",
            "apoderaApoderado",
            "apoderaVigencia",
          ];

          //if (isDesktop) {

          columnsNomAddicionals = [];
          columnsNomAddicionals.push("apoderaEstatActual");
          columnsNomAddicionals.push("apoderaOrganismes");
          columnsNomAddicionals.push("apoderaProcediments");
          columnsNomAddicionals.push("apoderaBoto");
          /*} else {
            columnsNom.push("apoderaOrganismes");
            columnsNom.push("apoderaProcediments");
          }*/

          let columnsTitols = [
            i18n.t("apoderaTipus"),
            i18n.t("apoderaAmbit"),
            i18n.t("apoderaEstat"),
            i18n.t("apoderaPoderdante"),
            i18n.t("apoderaApoderado"),
            i18n.t("apoderaVigencia"),
          ];

          //if (isDesktop) {
          columnsTitolsAddicionals = [];
          columnsTitolsAddicionals.push(t("apoderaEstatActual"));
          columnsTitolsAddicionals.push(t("apoderaOrganismes"));
          columnsTitolsAddicionals.push(t("apoderaProcediments"));
          columnsTitolsAddicionals.push(""); // "apoderaBoto" si es buit no es mostren els ":"
          /*} else {
            columnsTitols.push(t("apoderaOrganismes"));
            columnsTitols.push(t("apoderaProcediments"));
          }*/

          let apoderaments = this.state.dataApoderaments;

          content = (
            <div className="float-left" style={{ width: "97%", position: "relative" }}>
              <RenderTable
                tableData={apoderaments}
                columnNames={columnsNom}
                columnTitles={columnsTitols}
                columnNamesAdditionals={columnsNomAddicionals}
                columnTitlesAdditionals={columnsTitolsAddicionals}
                rowType={RowType.SHOW_ADDITIONAL_INFO}
                i18n={i18n}
              />
              {informacioAddicional}
            </div>
          );
        }
      }
    }

    return (
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        {content}
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(Apodera);
