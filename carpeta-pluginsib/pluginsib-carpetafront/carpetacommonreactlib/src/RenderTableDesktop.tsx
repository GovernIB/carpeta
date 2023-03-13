/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React, { Component } from "react";
import { RenderInternalTableProps } from "./RenderTableProps";
import { RowType, RowTypeUtils } from "./RowTypeUtils";

class RenderTableDesktop extends Component<RenderInternalTableProps> {
  constructor(props: RenderInternalTableProps) {
    super(props);

    this.onClickTableRow = this.onClickTableRow.bind(this);
    this.mostrarMesInfo = this.mostrarMesInfo.bind(this);
  }

  componentDidMount() {}

  onClickTableRow(i: number) {
    if (this.props.columnNamesAdditionals == undefined) {
      if (this.props.onClickRow) {
        this.props.onClickRow(i, this.props.tableData[i]);
      }
    } else {
      // Mostrar fila addicional
      this.mostrarMesInfo("additional_row_" + i);
    }
  }

  mostrarMesInfo(row: string) {
    var element = document.getElementById(row);
    if (element) {
      if (element.style.display === "none") {
        element.style.display = "table-row";
      } else if (element.style.display === "table-row") {
        element.style.display = "none";
      }
    }
  }

  render() {
    console.log("RenderTableDesktop::render() Start ...");

    var data = this.props.tableData; // Aquest valor sera this.props.dades

    let rowType: RowType = this.props.rowType == undefined ? RowType.NONE : this.props.rowType;

    let columnsNom: any[] = this.props.columnNames;

    let titols = this.props.columnTitles;

    let capTaula: any = [];
    {
      titols.forEach((clau: any, c: number) => {
        //console.log(" HEADER::[" + c + "] -> " + clau + " => " + titols[c]);
        capTaula.push(<th key={"header_" + c}>{clau}</th>);
      });

      if (rowType != RowType.NONE) {
        capTaula.push(<th key={"header_RowType"}>&nbsp;</th>);
      }
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
                  fila.push(<td key={i + "_" + c}>{valor}</td>);
                });

                let columnsLength = columnsNom.length;
                if (rowType != RowType.NONE) {
                  fila.push(
                    <td
                      key={i + "RowType"}
                      title={RowTypeUtils.getLabel(rowType, this.props.i18n)}
                      style={{ textAlign: "center" }}
                    >
                      {RowTypeUtils.getIcon(rowType)}
                    </td>
                  );
                  columnsLength++;
                }

                let filaAddicional = null;
                if (this.props.columnNamesAdditionals != undefined && this.props.columnTitlesAdditionals != undefined) {
                  let filaAddicionalContent: JSX.Element[] = [];
                  let columnsNomAddicionals = this.props.columnNamesAdditionals;
                  let columnsTitolsAddicionals = this.props.columnTitlesAdditionals;

                  columnsNomAddicionals.forEach((clauA: any, ca: number) => {
                    let valor: JSX.Element = (
                      <>
                        <b key={i + "_" + ca + "_add"}>{columnsTitolsAddicionals[ca]}</b>
                        {columnsTitolsAddicionals[ca] == "" ? "" : ": "}
                        {dataInfo[clauA]} <br />
                      </>
                    );
                    filaAddicionalContent.push(valor);
                  });

                  filaAddicional = (
                    <tr key={"add_row_" + i} style={{ display: "none" }} id={"additional_row_" + i}>
                      <td key={"add_col_" + i} colSpan={columnsLength}>
                        {filaAddicionalContent}
                      </td>
                    </tr>
                  );
                } else {
                  filaAddicional = <></>;
                }

                return (
                  <>
                    <tr
                      key={"fila_" + i}
                      tabIndex={511 + i * 2 - 1}
                      onClick={() => {
                        this.onClickTableRow(i);
                      }}
                      onKeyPress={() => {
                        this.onClickTableRow(i);
                      }}
                    >
                      {fila}
                    </tr>
                    {filaAddicional}
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
