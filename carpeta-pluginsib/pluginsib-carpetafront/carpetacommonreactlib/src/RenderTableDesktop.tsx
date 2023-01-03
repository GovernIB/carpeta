/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React, { Component } from "react";

type RenderTableProps = {
  dades: any[];
  columnsNom: any[];
  columnsTitols: any[];
};

class RenderTableDesktop extends Component<RenderTableProps> {
  constructor(props: RenderTableProps) {
    super(props);
  }

  render() {
    console.log("Render OK: Imprimint Data RENDER TABLE DESKTOP...!");

    var data = this.props.dades; // Aquest valor sera this.props.dades

    let columnsNom: any[] = this.props.columnsNom;

    let titols = this.props.columnsTitols;

    let capTaula: any = [];
    {
      titols.forEach((clau: any, c: number) => {
        //console.log(" HEADER::[" + c + "] -> " + clau + " => " + titols[c]);
        capTaula.push(<th key={c}>{clau}</th>);
      });
    }

    return (
      <>
        <div>
          <table
            id="tableId"
            style={{ width: "99%", border: "0px" }}
            className="table table-striped table-bordered table-hover"
          >
            <thead className="table-success">
              <tr>{capTaula}</tr>
            </thead>
            <tbody>
              {data.map((dataInfo: any, i: number) => {
                let fila: any[] = [];
                columnsNom.forEach((clau: any, c: number) => {
                  let valor = dataInfo[clau];
                  fila.push(<td key={c}>{valor}</td>);
                });

                return (
                  <>
                    <tr key={i} tabIndex={511 + i * 2 - 1}>
                      {fila}
                    </tr>
                  </>
                );
              })}
            </tbody>
          </table>
        </div>
      </>
    );
  }
}

export default RenderTableDesktop;
