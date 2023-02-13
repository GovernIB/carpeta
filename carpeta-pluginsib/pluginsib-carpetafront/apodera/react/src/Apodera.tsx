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
import { TemplatePageCarpeta, RenderTable } from "carpetacommonreactlib";
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
    apoderaTipus: string,
    apoderaAmbit: string,
    apoderaEstat: string,
    apoderaPoderdante: JSX.Element,
    apoderaApoderado: JSX.Element,
    apoderaVigencia: string,
    apoderaOrganismes?: JSX.Element[],
    apoderaProcediments?: JSX.Element[],

}


class Apodera extends React.Component<ApoderaProps,ApoderaState>  {
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
      entitat: null
    };

    // TODO: Això es pot eliminar ?????
    //const getLocale = (Locale) => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
    //this.locale = getLocale(this.props.language);

    this.nomEstat = this.nomEstat.bind(this);
    this.descripcioEstat = this.descripcioEstat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    this.carregaDadesApoderaments = this.carregaDadesApoderaments.bind(this);

    this.onClickRowDesktop = this.onClickRowDesktop.bind(this);
    this.onClickRowMobile = this.onClickRowMobile.bind(this);
    this.tancarModal = this.tancarModal.bind(this);

    i18n.on("languageChanged", this.canviatIdioma);
  }

  canviatIdioma(lng:string) {
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


  descripcioEstat(estat:string) {
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
          dataApoderaments: res.data.apoderaments,
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

  addBold(str:string, nif:string) {
    if (str.includes(nif)) {
      return <b> {str} </b>;
    } else {
      return str;
    }
  }

  addNewLine(str:string, nif:string, isDesktop:boolean) {
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
    let htmlCode:JSX.Element[] = [];
    {
      stringList &&
        stringList.forEach((s:any, x:number) => {
          htmlCode.push(
            <p style={{ margin: 0 }} key={x}>
              - {s}
            </p>
          );
        });
    }
    return htmlCode;
  }

  onClickRowDesktop(i:number) {
    //$('#Modal_Info_Apo_' + i).modal("show");

    console.log("Passa per onClickRowDesktop(" + i + ") ...")

    let modal: any = document.getElementById('Modal_Info_Apo_' + i);
    modal.style.display = "block";
    modal.classList.remove("hide");
    modal.classList.add("show");
  }


  tancarModal(modalID:string) {

    console.log("Passa per tancarModal(" + modalID + ") ...")

    let modal: any = document.getElementById(modalID);
    modal.style.display = "none";
    modal.classList.remove("show");
    modal.classList.add("hide");
  }


  onClickRowMobile(i:number) {
    window.open(this.state.urlApodera, "_blank");
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
        if (this.state.dataApoderaments != null && this.state.dataApoderaments.length == 0) {
          content = (
            <div className="alert alert-secondary" role="alert">
              {t("apoderaApoderamentsNoTrobats")}
            </div>
          );
        } else if (
          this.state.totalComApoderat + this.state.totalComPoderdant === 0 &&
          this.state.dataApoderaments !== null
        ) {
          content = (
            <div className="pt-3 alert alert-secondary" style={{ float: "left", width: "95%" }} role="alert">
              {t("apoderaBuid")}
            </div>
          );
        } else {
          // OK Tenim dades

          let nif:string;
          if (this.state.dataPoderdant.personaFisica) {
            nif = this.state.dataPoderdant.personaFisica.nifNie;
          } else {
            nif = this.state.dataPoderdant.personaJuridica.nif;
          }


          let accedirLabel:string = t("apoderaAccedirApoderament");

          let informacioAddicional;

          let isDesktop = !reactdetect.isMobileOnly;

          if (!isDesktop) {
            informacioAddicional = <></>;
          } else {
            informacioAddicional = (
              <>
                {/*  ============== MODAL VERSIO DESKTOP ================= */}

                {this.state.dataApoderaments.map(
                  (
                    { tipus, subtipus, estat, apoderado, poderdante, vigencia, procediments, organismes, tramits }:any,
                    i:number
                  ) => {
                    let llistaOrganismes = this.list2Html(organismes);

                    let llistaProcediments = this.list2Html(procediments);

                    //let llistaTramits = this.list2Html(tramits);

                    return (
                      <>
                        <div className="modal" id={"Modal_Info_Apo_" + i} key = {i} tabIndex={-1} role="dialog" onClick={() => { this.tancarModal("Modal_Info_Apo_" + i); }}>
                          <div className="modal-dialog modal-dialog-centered" role="document">
                            <div className="modal-content">
                              <div className="modal-header" style={{ padding: "0px" }}>
                                <p className="lh15" style={{ margin: "0.4em 0" }}>
                                  &nbsp;&nbsp;{t("apoderaInformacio")}
                                </p>
                                <button type="button" className="close" data-dismiss="modal" aria-hidden="true" aria-label="Close" onClick={() => { this.tancarModal("Modal_Info_Apo_" + i); }}>
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div className="modal-body" style={{ fontSize: "0.8em" }}>
                                <div style={{ width: "90%" }}>
                                  <p>
                                    <b>{t("apoderaEstatActual")}</b>: {this.nomEstat(estat)} -{" "}
                                    {this.descripcioEstat(estat)}
                                  </p>
                                  {organismes && (
                                    <span>
                                      <b>{t("apoderaOrganismes")}</b>: {llistaOrganismes}
                                    </span>
                                  )}
                                  {procediments && (
                                    <span>
                                      <b>{t("apoderaProcediments")}</b>: {llistaProcediments}
                                    </span>
                                  )}
                                  {/*tramits && (
                              <span>
                                <b>{t("apoderaTramits")}</b>:{" "}
                                {llistaTramits}
                              </span>
                            )*/}
                                </div>
                                <br />
                                <br />
                                <div style={{ width: "auto" }} id="accedirApodera">
                                  <span>
                                    <button
                                      className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                      title={accedirLabel}
                                      tabIndex={511 + i * 2}
                                      aria-labelledby="accedirApodera"
                                      onClick={() => {
                                        window.open(this.state.urlApodera, "_blank");
                                      }}
                                    >
                                      <span className="oi oi-external-link" title="" aria-hidden="true" />{" "}
                                      {t("apoderaBotoApoderament")}
                                    </button>
                                  </span>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </>
                    );
                  }
                )}
              </>
            );
          }

          const columnsNom = [
            "apoderaTipus",
            "apoderaAmbit",
            "apoderaEstat",
            "apoderaPoderdante",
            "apoderaApoderado",
            "apoderaVigencia",
          ];

          if (!isDesktop) {
            columnsNom.push("apoderaOrganismes");
            columnsNom.push("apoderaProcediments");
          }

          const columnsTitols = [
            i18n.t("apoderaTipus"),
            i18n.t("apoderaAmbit"),
            i18n.t("apoderaEstat"),
            i18n.t("apoderaPoderdante"),
            i18n.t("apoderaApoderado"),
            i18n.t("apoderaVigencia"),
          ];

          if (!isDesktop) {
            columnsTitols.push(t("apoderaOrganismes"));
            columnsTitols.push(t("apoderaProcediments"));
          }

          let apoderaments:ApoderaListItem[]  = [];

          {
            this.state.dataApoderaments.map(
              ({ tipus, subtipus, estat, apoderado, poderdante, vigencia, procediments, organismes, tramits }:any, i: number) => {

                let apoderado2 = this.addNewLine(apoderado, nif, isDesktop);
                let poderdante2 = this.addNewLine(poderdante, nif, isDesktop);

                let element:ApoderaListItem = {
                  apoderaTipus: tipus,
                  apoderaAmbit: subtipus,
                  apoderaEstat: this.nomEstat(estat),
                  apoderaPoderdante: poderdante2,
                  apoderaApoderado: apoderado2,
                  apoderaVigencia: vigencia
                };

                if (!isDesktop) {
                  element.apoderaOrganismes = this.list2Html(organismes);
                  element.apoderaProcediments = this.list2Html(procediments);
                }

                apoderaments.push(element);
              }
            );
          }

          content = (
            <div className="float-left" style={{ width: "97%", position: "relative" }}>
              <RenderTable
                dades={apoderaments}
                columnsNom={columnsNom}
                columnsTitols={columnsTitols}
                onClickRow={isDesktop ? this.onClickRowDesktop : this.onClickRowMobile}
              />
              {informacioAddicional}
            </div>
          );
        }
      }
    }

    return (
      <>
        <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
          {content}
        </TemplatePageCarpeta>
      </>
    );
  }
}

export default withTranslation()(Apodera);
