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
import RenderTableMobile from "./RenderTableMobile";
import RenderTableDesktop from "./RenderTableDesktop";


class RenderTable extends Component {
  constructor(props) {
    super(props);
    console.log("  CONSTRUCTOR RenderTable !!!!!");
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

    console.log("Render OK: Imprimint Data RENDER TABLE...!");

    var data = this.props.dades; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnsNom;

    let columnsTitols = this.props.columnsTitols;



    if (!reactdetect.isMobileOnly) {

      {/*  ============== VERSIO DESKTOP ================= */ }
      return (
        <>
          <RenderTableDesktop dades={data} columnsNom={columnsNom} columnsTitols={columnsTitols} />
        </>
      );

      {/*  ============== FINAL VERSIO DESKTOP ================= */ }

    } else {
      {/*  ============== VERSIO MÒBIL ================= */ }
      return (
        <>
          <RenderTableMobile dades={data} columnsNom={columnsNom} columnsTitols={columnsTitols} />
        </>
      );
      {/*  ============== FINAL VERSIO MÒBIL ================= */ }

    }


  }
}



export default withTranslation()(RenderTable);
