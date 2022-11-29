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

    console.log("Render OK: Imprimint Data RENDER TABLE DESKTOP...!");

    var data = this.props.dades; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnsNom;

    let titols = this.props.columnsTitols;


    let content;



      let capTaula = [];
      {
        columnsNom.forEach((clau, c) => {
          console.log(c + " -> " + titols[c]);
          capTaula.push(<th>{titols[c]}</th>);
        })
      }
      content = (
        <>
          <div>
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
                  {capTaula}
                </tr>
              </thead>
              <tbody>
                {data.map(
                  (
                    expedientInfo,
                    i
                  ) => {
                    let fila = [];
                    columnsNom.forEach((clau, c) => {
                      let valor = expedientInfo[clau];
                      fila.push(<td>{valor}</td>);
                    });


                    return (
                      <>
                        <tr key={i} tabIndex={511 + i * 2 - 1}>
                          {fila}
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

      return (
        <>
          {content}
        </>
      );
  }
}



export default withTranslation()(RenderTable);
