/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React from "react";
import { RenderInternalTableProps} from "./RenderTableProps";
import { RowType, RowTypeUtils } from "./RowTypeUtils";

class RenderTableMobile extends React.Component<RenderInternalTableProps> {
  constructor(props: RenderInternalTableProps) {
    super(props);
  }

  render() {
    console.log("Render OK: Imprimint Data RENDER TABLE MOBILE...!");

    let rowType:RowType = (this.props.rowType == undefined? RowType.NONE : this.props.rowType);

    var data: any[] = this.props.tableData; // Aquest valor sera this.props.dades

    let columnsNom = this.props.columnNames;


    //console.log("MOBILE[NOM] => " + columnsNom.length);

    //console.log("MOBILE[columnNamesAdditionals] => " + this.props.columnNamesAdditionals);

    if (this.props.columnNamesAdditionals) {
      //console.log("MOBILE[columnNamesAdditionals.len] => " + this.props.columnNamesAdditionals.length);
      columnsNom = columnsNom.concat(this.props.columnNamesAdditionals);
    }
    //console.log("MOBILE[NOM + ADDICIO] => " + columnsNom.length);

    let titols = this.props.columnTitles;
    //console.log("MOBILE[TITOLS] => " + titols.length);
    if (this.props.columnTitlesAdditionals) {
      titols = titols.concat(this.props.columnTitlesAdditionals);
    }
    //console.log("MOBILE[TITOLS + ADDICIO] => " + columnsNom.length);

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
                        this.props.onClickRow(i, this.props.tableData[i]);
                      }
                    }}
                  >
                    <div className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                      <b>{titols[c]}</b> {titols[c] == ""? "":":"} {dataInfo[clau]}
                    </div>
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
                  { (rowType != RowType.NONE && rowType != RowType.SHOW_ADDITIONAL_INFO) && <div style={{position:"absolute", right:"10px"}}>
                    {RowTypeUtils.getIcon(rowType)}
                  </div>
                  }
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
