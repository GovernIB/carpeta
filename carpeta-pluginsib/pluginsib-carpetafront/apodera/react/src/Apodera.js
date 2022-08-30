/**
 * @author anadal, jpernia
 * @create date 2022-08-29 08:51:48
 * @modify date 2022-08-29 08:51:48
 */
import React, { Component } from "react";
import { withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import Table from "react-bootstrap/Table";

class Apodera extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoaded: false,
      dataApoderaments: null,
      dataPoderdant: null,
      totalComPoderdant: 0,
      totalComApoderat: 0,
      error: null,
      urlApodera: null,
    };

    // TODO: Això es pot eliminar ?????
    const getLocale = (Locale) =>
      require(`date-fns/locale/${sessionStorage.getItem(
        "langActual"
      )}/index.js`);
    this.locale = getLocale(this.props.language);

    this.nomEstat = this.nomEstat.bind(this);
    this.descripcioEstat = this.descripcioEstat.bind(this);
    this.mostrarMesInfo = this.mostrarMesInfo.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    this.carregaDadesApoderaments = this.carregaDadesApoderaments.bind(this);

    i18n.on("languageChanged", this.canviatIdioma);
  }

  canviatIdioma(lng) {
    console.log("Apodera::PASSA PER LISTENER canviatIdioma() ...");
    this.carregaDadesApoderaments();
  }

  nomEstat(estat) {
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

  mostrarMesInfo(row) {
    if (document.getElementById(row).style.display === "none") {
      document.getElementById(row).style.display = "table-row";
    } else if (document.getElementById(row).style.display === "table-row") {
      document.getElementById(row).style.display = "none";
    }
  }

  descripcioEstat(estat) {
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
        });
      })
      .catch((error) => {
        // console.log("ERROR: " + JSON.stringify(error));
        if (error.response) {
          console.log("error.response.data: " + error.response.data);
          console.log("error.response.status: " + error.response.status);
          console.log("error.response.headers: " + error.response.headers);
        }
        if (
          JSON.stringify(error)
            .toString()
            .includes("Request failed with status code 500")
        ) {
          var errorPantalla = error.response.data.replace(
            "<html><head><title>Error</title></head><body>",
            ""
          );
          errorPantalla = errorPantalla.replace("</body></html>", "");
          this.setState({
            isLoaded: true,
            dataApoderaments: null,
            dataPoderdant: null,
            totalComPoderdant: 0,
            totalComApoderat: 0,
            error: errorPantalla,
            urlApodera: null,
          });
        } else {
          this.setState({
            dataApoderaments: null,
            dataPoderdant: null,
            totalComPoderdant: 0,
            totalComApoderat: 0,
            error: JSON.stringify(error).toString(),
            isLoaded: true,
            urlApodera: null,
          });
        }
      });
  }

  render() {
    const isLoaded = this.state.isLoaded;

    const { t } = this.props;

    let nif;
    let content;
/*
    let resultat1;
    let resultat2;
*/
    let taulaApodera;
    var tamanyTaula = { width: "99%" };
    var tamanyData = { width: "120px !important" };
    let cardApoderaments = [];

    if (!isLoaded) {
      content = (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );

      taulaApodera = "";
      /*
      resultat1 = "";
      resultat2 = "";
      */
    } else {
      if (this.state.error) {
        content = (
          <div className="alert alert-danger" role="alert">
            {this.state.error}
          </div>
        );
      } else {
        content = "";

        if (this.state.dataPoderdant.personaFisica) {
          let nomPoderdant;
          let llinatge1Poderdant;
          let llinatge2Poderdant;
          nif = this.state.dataPoderdant.personaFisica.nifNie;
          if (this.state.dataPoderdant.personaFisica.nombre !== undefined) {
            nomPoderdant = this.state.dataPoderdant.personaFisica.nombre;
          } else {
            nomPoderdant = "";
          }
          if (this.state.dataPoderdant.personaFisica.apellido1 !== undefined) {
            llinatge1Poderdant =
              this.state.dataPoderdant.personaFisica.apellido1;
          } else {
            llinatge1Poderdant = "";
          }
          if (this.state.dataPoderdant.personaFisica.apellido2 !== undefined) {
            llinatge2Poderdant =
              this.state.dataPoderdant.personaFisica.apellido2;
          } else {
            llinatge2Poderdant = "";
          }
          let nomTotal =
            nomPoderdant +
            " " +
            llinatge1Poderdant +
            " " +
            llinatge2Poderdant +
            " (" +
            this.state.dataPoderdant.personaFisica.nifNie +
            ")";

            /*
          resultat1 = t("missatgeTotalComApoderat", {
            totalComApoderat: this.state.totalComApoderat,
            name: nomTotal,
          });
          resultat2 = t("missatgeTotalComPoderdant", {
            totalComPoderdant: this.state.totalComPoderdant,
            name: nomTotal,
          });
          */
        } else {
          nif = this.state.dataPoderdant.personaJuridica.nif;
          let raoSocialPoderdant;
          if (
            this.state.dataPoderdant.personaJuridica.razonSocial !== undefined
          ) {
            raoSocialPoderdant =
              this.state.dataPoderdant.personaJuridica.razonSocial;
          } else {
            raoSocialPoderdant = "";
          }
          let nomTotal =
            raoSocialPoderdant +
            " (" +
            this.state.dataPoderdant.personaJuridica.nif +
            ")";
/*
          resultat1 = t("missatgeTotalComApoderat", {
            totalComApoderat: this.state.totalComApoderat,
            name: nomTotal,
          });
          resultat2 = t("missatgeTotalComPoderdant", {
            totalComPoderdant: this.state.totalComPoderdant,
            name: nomTotal,
          });
*/
        }

        if (
          this.state.dataApoderaments &&
          typeof this.state.dataApoderaments !== undefined
        ) {
          taulaApodera = (
            <>
              <div>
                <Table
                  id="tableId"
                  responsive
                  striped
                  bordered
                  hover
                  style={tamanyTaula}
                  className="ocultarMobil"
                >
                  <thead className="table-success">
                    <tr>
                      <th>{t("apoderaTipus")}</th>
                      <th style={tamanyData}>{t("apoderaAmbit")}</th>
                      <th>{t("apoderaEstat")}</th>
                      <th>{t("apoderaPoderdante")}</th>
                      <th>{t("apoderaApoderado")}</th>                      
                      <th>{t("apoderaVigencia")}</th>
                    </tr>
                  </thead>
                  <tbody>
                    {this.state.dataApoderaments.map(
                      (
                        {
                          tipus,
                          subtipus,
                          estat,
                          apoderado,
                          poderdante,
                          vigencia,
                          procediments,
                          organismes,
                          tramits,
                        },
                        i
                      ) => {

                        let llistaOrganismes = [];
                        {
                          organismes &&
                            organismes.forEach((s, i) => {
                                llistaOrganismes.push(
                                  <p key={i}>- {s}</p>
                                );
                            });
                        }

                        let llistaProcediments = [];
                        {
                          procediments &&
                            procediments.forEach((s, i) => {
                              llistaProcediments.push(<p key={i}>- {s}</p>);
                            });
                        }

                        let llistaTramits = [];
                        {
                          tramits &&
                            tramits.forEach((s, i) => {
                              llistaTramits.push(<p key={i}>- {s}</p>);
                            });
                        }

                        return (
                          <>
                            <tr
                              key={i}
                              className="clickable-row"
                              tabIndex={511 + i * 2 - 1}
                              onClick={() => this.mostrarMesInfo("row" + i)}
                              onKeyPress={() => this.mostrarMesInfo("row" + i)}
                            >
                              <td>{tipus}</td>
                              <td>{subtipus}</td>
                              <td>{this.nomEstat(estat)}</td>
                              <td>
                                {poderdante.includes(nif) ? (
                                  <b>{poderdante}</b>
                                ) : (
                                  poderdante
                                )}
                              </td>
                              <td>
                                {apoderado.includes(nif) ? (
                                  <b>{apoderado}</b>
                                ) : (
                                  apoderado
                                )}
                              </td>
                              <td>{vigencia}</td>
                            </tr>
                            <tr
                              key={10000 + i}
                              style={{ display: "none" }}
                              id={"row" + i}
                            >
                              <td colSpan={6}>
                                <div style={{ float: "left", width: "70%" }}>
                                  <p>
                                    <b>{t("apoderaEstatActual")}</b>:{" "}
                                    {this.nomEstat(estat)} -{" "}
                                    {this.descripcioEstat(estat)}
                                  </p>
                                  {organismes && (
                                    <span>
                                      <b>{t("apoderaOrganismes")}</b>:{" "}
                                      {llistaOrganismes}
                                    </span>
                                  )}
                                  {procediments && (
                                    <span>
                                      <b>{t("apoderaProcediments")}</b>:{" "}
                                      {llistaProcediments}
                                    </span>
                                  )}
                                  {tramits && (
                                    <span>
                                      <b>{t("apoderaTramits")}</b>:{" "}
                                      {llistaTramits}
                                    </span>
                                  )}
                                </div>
                                <div
                                  style={{ float: "right", width: "auto" }}
                                  id="accedirApodera"
                                >
                                  <span>
                                    <button
                                      className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                      title={t("apoderaAccedirApoderament")}
                                      tabIndex={511 + i * 2}
                                      aria-labelledby="accedirApodera"
                                      onClick={() => {
                                        window.open(
                                          this.state.urlApodera,
                                          "_blank"
                                        );
                                      }}
                                    >
                                      <span
                                        className="oi oi-external-link"
                                        title=""
                                        aria-hidden="true"
                                      />{" "}
                                      {t("apoderaBotoApoderament")}
                                    </button>
                                  </span>
                                </div>
                              </td>
                            </tr>
                          </>
                        );
                      }
                    )}
                  </tbody>
                </Table>
              </div>
            </>
          );
          {
            /*
                    this.state.dataApoderaments.map(({   tipus,
                                                         subtipus,
                                                         estat,
                                                         apoderado,
                                                         vigencia,
                                                         procediments,
                                                         organismes
                                                     }, i) => {

                        let valorAmbit2 = "ST" + subtipus;
  

                        let llistaOrganismes2=[];
                        {organismes && organismes.forEach((s, i) => {
                            if(s.codOrganismo) {
                                llistaOrganismes2.push(<p>- {s.codOrganismo}: {s.denomOrganismo}</p>)
                            }else{
                                llistaOrganismes2.push(<p>- {s.denomOrganismo}</p>)
                            }
                        })}

                        let llistaProcediments2=[];
                        {procediments && procediments.forEach((s, i) => {
                            if(s.codProcedimiento) {
                                llistaProcediments2.push(<p>- {s.codProcedimiento}</p>)
                            }
                        })}

                        cardApoderaments.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                                 key={i} tabIndex={511+i} onClick={(e) =>
                                window.open(this.state.urlApodera, "_blank")}>
                                <div className="col-sm-1 float-left">
                                    <span className="oi oi-key iconaFormApp" title={t('apoderaIconaApp')} style={{verticalAlign: 'sub'}}/>
                                </div>
                                <div className="col-sm-10 float-right">
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaTipus')}</b>: {tipus}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaAmbit')}: </b>{valorAmbit2}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaEstatActual')}: </b>{this.nomEstat(estat)} - {this.descripcioEstat(estat)}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaApoderado')}: </b>{apoderado}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaVigencia')}: </b>{vigencia}</p>
                                    {organismes && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaOrganismes')}</b>: {llistaOrganismes2}</p>}
                                    {procediments && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaProcediments')}</b>: {llistaProcediments2}</p>}
                                </div>
                            </div>
                        )


                    })
*/
          }
        } else if (
          this.state.totalComApoderat + this.state.totalComPoderdant === 0 &&
          this.state.dataApoderaments !== null
        ) {
          taulaApodera = (
            <div
              className="pt-3 alert alert-secondary"
              style={{ float: "left", width: "95%" }}
              role="alert"
            >
              {t("apoderaBuid")}
            </div>
          );
        }
      }
    }

    return (
      <>
        <div className="titolPaginaApp visioMobil">
          {this.props.titles[i18n.language]}
        </div>
        <div className="infoNoMenu">
          <h2 className="titol h2 ocultarMobil">
            {this.props.titles[i18n.language]}
          </h2>
          <div className="col-md-12 border-0 float-left p-0">
            <p className="lh15 ocultarMobil">
              {this.props.subtitles[i18n.language]}{" "}
            </p>
            <div className="infoNoMenu">
              <div className="col-md-12 border-0 float-left p-0">{content}</div>
            </div>
          </div>
          <div
            className="float-left"
            style={{ width: "97%", position: "relative" }}
          >
            { /*}
            {this.state.totalComApoderat + this.state.totalComPoderdant !==
              0 && (
              <div className="contenedorInfoPersonal mt-2 pb-2 pad15App">
                <p>{resultat1}</p>
                <p>{resultat2}</p>
              </div>
            )}
              */ }
            {this.state.isLoaded && taulaApodera}
            {this.state.isLoaded && cardApoderaments}
          </div>
          <div
            className="col-md-12 border-0 float-left p-0"
            id="botoTornarApodera"
            style={{ marginTop: "20px" }}
          >
            <button
              type="button"
              data-toggle="modal"
              onClick={() => {
                window.location.href = sessionStorage.getItem("pagTornar");
                sessionStorage.setItem(
                  "pagTornar",
                  sessionStorage.getItem("contextPath")
                );
              }}
              className="botoSuport botoTornauApp"
              tabIndex="550"
              aria-labelledby="botoTornarApodera"
            >
              {t("apoderaTornar")}
            </button>
          </div>
        </div>
      </>
    );
  }
}

export default withTranslation()(Apodera);
