/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2022-11-16 10:30:40
 * @desc [description]
 */

import React, { Component } from "react";
import { withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import Table from "react-bootstrap/Table";
import * as reactdetect from "react-device-detect";

class ElsMeusExpedients extends Component {
  constructor(props) {
    super(props);
    console.log("  CONSTRUCTOR ElsMeusExpedients !!!!!");
    this.state = {
      isLoaded: false,
      expedientresposta: null,
      error: null,
      elementsPerPagina: 100,
      pagina: 0,
    };
  }

  componentDidMount() {
    const url = this.props.pathtoservei;

    const params = {
      elementsPerPagina: this.state.elementsPerPagina,
      pagina: this.state.pagina,
    };

    console.log("Cridant a serveis REST URL: " + url);
    console.log("Cridant a serveis REST PARAMS: " + params);

    axios
      .get(url, { params: params })
      .then((response) => {
        if (response.data != null) {
          // response.data conté un objecte de tipus ExpedientResposta.java
          this.setState({
            ...this.state,
            expedientresposta: response.data,
            error: null,
            isLoaded: true,
          });
        }
      })
      .catch((error) => {
        console.log("Error cridant al servei REST: \n" + JSON.stringify(error));
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
            error: errorPantalla,
            isLoaded: true,
          });
        } else {
          this.setState({
            error: JSON.stringify(error),
            isLoaded: true,
          });
        }
      });
  }

  render() {
    console.log("  RENDER ElsMeusExpedients !!!!!");

    const { t } = this.props;

    const isLoaded = this.state.isLoaded;

    if (!isLoaded) {
      console.log(" No esta carregat ... ");
      return (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );
    }

    if (this.state.error) {
      console.log(" Error: ]" + this.state.error + "|");
      return (
        <div className="alert alert-danger" role="alert">
          {this.state.error}
        </div>
      );
    }

    var tamanyTaula = { width: "99%" };
    var tamanyData = { width: "120px !important" };

    /**
    ExpedientResposta {

        protected int paginaActual;
        protected int elementsPerPagina;
    
        protected int totalPagines;
        protected int registresRetornats;
        protected int totalRegistres;
    
        protected List<ExpedientInfo> expedients;
        */

    console.log("Render OK: Imprimint Expedients ...!");

    var expedients = this.state.expedientresposta.expedients;

    /**
     
     ExpedientInfo {


    protected String expedientNom;
    protected String expedientDesc;
    protected String expedientEstat;
        protected String codiSia;
    protected String nomProcediment;
    protected List<String> expedientOrgans;
    protected Date expedientObertura;
     * 
     */

    let content = (
      <>
        <div>
          {
            <Table
              id="tableId"
              responsive
              striped
              bordered
              hover
              style={tamanyTaula}
            >
              <thead className="table-success">
                <tr>
                  <th>{t("expedientNom")}</th>
                  <th>{t("expedientDesc")}</th>
                  <th>{t("expedientEstat")}</th>
                  <th>{t("codiSia")}</th>
                  <th>{t("nomProcediment")}</th>
                  <th>{t("expedientOrgans")}</th>
                  <th>{t("expedientObertura")}</th>
                </tr>
              </thead>
              <tbody>
                {expedients.map(
                  (
                    {
                      expedientNom,
                      expedientDesc,
                      expedientEstat,
                      codiSia,
                      nomProcediment,
                      expedientOrgans,
                      expedientObertura,
                    },
                    i
                  ) => {
                    return (
                      <>
                        <tr key={i} tabIndex={511 + i * 2 - 1}>
                          <td>{expedientNom}</td>
                          <td>{expedientDesc}</td>
                          <td>{expedientEstat}</td>
                          <td>{codiSia}</td>
                          <td>{nomProcediment}</td>
                          <td>{expedientOrgans}</td>
                          <td>{expedientObertura}</td>
                        </tr>
                      </>
                    );
                  }
                )}
              </tbody>
            </Table>
          }
        </div>
      </>
    );

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

            <div
              className="tab-pane fade show active"
              id="elsmeusexpedients"
              role="tabpanel"
              aria-labelledby="home-tab"
            >
              <div className="infoNoMenu">{content}</div>
              <div
                className="col-md-12 border-0 float-left p-0"
                id="botoTornarDadesP"
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
                  tabIndex="520"
                  aria-labelledby="botoTornarDadesP"
                >
                  {t("tornar")}
                </button>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

export default withTranslation()(ElsMeusExpedients);
