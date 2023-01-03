/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */

import React from "react";
//import { WithTranslation, withTranslation } from "react-i18next";
import PaginationCarpetaProps from "./PaginationCarpetaProps";
import initTranslation from './InitTranslation';


interface InternalPaginationCarpetaProps /*extends WithTranslation */ {
  paginationInfo: PaginationCarpetaProps;
  i18n: any;
}

class PaginationCarpeta extends React.Component<InternalPaginationCarpetaProps> {
  constructor(props: InternalPaginationCarpetaProps) {
    super(props);
    console.log("  CONSTRUCTOR PaginationCarpeta !!!!!");

    console.log("PaginationCarpeta().paginaActual  => " + this.props.paginationInfo.paginaActual);
    console.log("PaginationCarpeta().elementsPerPagina  => " + this.props.paginationInfo.elementsPerPagina);
    console.log("PaginationCarpeta().totalPagines  => " + this.props.paginationInfo.totalPagines);
    console.log("PaginationCarpeta().registresRetornats  => " + this.props.paginationInfo.registresRetornats);
    console.log("PaginationCarpeta().totalRegistres  => " + this.props.paginationInfo.totalRegistres);


    initTranslation(this.props.i18n);


  }

  render() {
    console.log("  RENDER PaginationCarpeta !!!!!");

    let pagines: any = [];
    {
      for (let i = 0; i < this.props.paginationInfo.totalPagines; i++) {
        pagines.push(
          <li key={i} className={`page-item ${this.props.paginationInfo.paginaActual == i ? "active" : ""}`}>
            <a
              className="page-link"
              onClick={() => {
                this.props.paginationInfo.onClickPagination(i);
              }}
            >
              {i + 1}
            </a>
          </li>
        );
      }
    }


    const current:number = this.props.paginationInfo.registresRetornats;
    const total:number = this.props.paginationInfo.totalRegistres;
    const from:number = this.props.paginationInfo.paginaActual * this.props.paginationInfo.elementsPerPagina + 1;
    const to:number = Math.min(
      this.props.paginationInfo.totalRegistres,
      (1 + this.props.paginationInfo.paginaActual) * this.props.paginationInfo.elementsPerPagina
    );

    return (

      <center>
        <small>
          { /* XYZ ZZZ Mostrant {current} entrades de {total} (De la posició {from} fins a la {to}) */}
          {this.props.i18n.t("paginacioLabel", {current: current, total: total, from: from, to: to})}
        </small>
        <nav aria-label="Page navigation">
          <ul className="pagination justify-content-center">
            <li className="page-item">
              <a
                className="page-link"
                onClick={() => {
                  this.props.paginationInfo.onClickPagination(0);
                }}
              >
                <b>&lt;&lt;</b>
              </a>
            </li>
            {pagines}
            <li className="page-item">
              <a
                className="page-link"
                onClick={() => {
                  this.props.paginationInfo.onClickPagination(this.props.paginationInfo.totalPagines - 1);
                }}
              >
                <b>&gt;&gt;</b>
              </a>
            </li>
          </ul>
        </nav>
      </center>
    );
  }
}

//export default withTranslation()(PaginationCarpeta);
export default PaginationCarpeta;
