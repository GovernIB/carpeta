/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React from "react";
import RenderTableProps from "./RenderTableProps";

class RenderTableMobile extends React.Component<RenderTableProps> {
  constructor(props: RenderTableProps) {
    super(props);
  }

  render() {
    console.log("Render OK: Imprimint Data RENDER TABLE MOBILE...!");

    var data: any[] = this.props.tableData; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnNames;

    let titols = this.props.columnTitles;

    let content;

    {
      /*  ============== VERSIO MÒBIL ================= */
    }
    content = (
      <>
        <div>
          {data.map((dataInfo, i) => {
            let cardsMobile: any[] = [];
            {
              columnsNom.forEach((clau, c) => {
                cardsMobile.push(
                  <div
                    className="col-sm-10 float-right"
                    onClick={() => {
                      if (this.props.onClickRow) {
                        this.props.onClickRow(i);
                      }
                    }}
                  >
                    <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                      <b>{titols[c]}</b>: {dataInfo[clau]}
                    </p>
                  </div>
                );
              });
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
                      className={`oi ${this.props.mobileIcon ? this.props.mobileIcon : "oi-key"} iconaFormApp`}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  {cardsMobile}
                </div>
              </>
            );
          })}
        </div>
      </>
    );

    return <>{content}</>;
  }
}

export default RenderTableMobile;
