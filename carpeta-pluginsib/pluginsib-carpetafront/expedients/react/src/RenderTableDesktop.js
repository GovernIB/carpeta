/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React, { Component } from "react";
import Table from "react-bootstrap/Table";

class RenderTableDesktop extends Component {
  constructor(props) {
    super(props);
  }


  render() {


    var tamanyTaula = { width: "99%" };
    var tamanyData = { width: "120px !important" };


    console.log("Render OK: Imprimint Data RENDER TABLE DESKTOP...!");

    var data = this.props.dades; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnsNom;

    let titols = this.props.columnsTitols;






      let capTaula = [];
      {
        columnsNom.forEach((clau, c) => {
          console.log(c + " -> " + titols[c]);
          capTaula.push(<th>{titols[c]}</th>);
        })
      }


      return (
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
  }
}



export default RenderTableDesktop;
