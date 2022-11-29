/**
 * @author fbosch
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React, { Component } from "react";
import { withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import Table from "react-bootstrap/Table";
import * as reactdetect from "react-device-detect";

class RenderTable extends Component {
  constructor(props) {
    super(props);
  }


  render() {

    const { t } = this.props;


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

    console.log("Render OK: Imprimint Data RENDER TABLE MOBILE...!");

    var data = this.props.dades; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnsNom;

    let titols = this.props.columnsTitols;


    let content;


    {/*  ============== VERSIO MÒBIL ================= */ }
    content = (
        <>
          <div>
            {data.map(
              (
                expedientInfo,
                i
              ) => {

                let cardsMobile = [];
                {
                  columnsNom.forEach((clau, c) => {
                    cardsMobile.push(<div className="col-sm-10 float-right">
                      <p
                        className="card-text pl-1 mt-0"
                        style={{ color: "rgb(102, 102, 102)" }}
                      >
                        <b>{titols[c]}</b>: {expedientInfo[clau]}
                      </p>
                    </div>);
                  })
                }
                return (
                  <>
                    <div
                      className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5  visioMobil cardAppVerd"
                      key={i}
                      tabIndex={511 + i}
                    >
                      <div className="col-sm-1 float-left">
                        <span
                          className="oi oi-key iconaFormApp"
                          style={{ verticalAlign: "sub" }}
                        />
                      </div>
                      {cardsMobile}
                    </div>
                  </>
                );
              }
            )}
          </div>
        </>
    );


    return (
      <>
        {content}
      </>
    );
  }
}



export default withTranslation()(RenderTable);
