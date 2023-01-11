/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */

import React from "react";
import PaginationCarpetaProps from "./PaginationCarpetaProps";
import initTranslation from "./InitTranslation";
import { PaginationItemCarpeta as Item } from "./PaginationItemCarpeta";
import * as reactdetect from "react-device-detect";

interface InternalPaginationCarpetaProps {
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

    const current: number = this.props.paginationInfo.registresRetornats;
    const total: number = this.props.paginationInfo.totalRegistres;
    const from: number = this.props.paginationInfo.paginaActual * this.props.paginationInfo.elementsPerPagina + 1;
    const to: number = Math.min(
      this.props.paginationInfo.totalRegistres,
      (1 + this.props.paginationInfo.paginaActual) * this.props.paginationInfo.elementsPerPagina
    );

    let pagines: any = [];

    let onClick: Function = this.props.paginationInfo.onClickPagination;

    // First
    pagines.push(
      <Item value={0} onClick={onClick}>
        «
      </Item>
    );
    // Previous
    pagines.push(
      <Item value={Math.max(0, this.props.paginationInfo.paginaActual - 1)} onClick={onClick}>
        ‹
      </Item>
    );

    // All pagination numbers
    {
      for (let i = 0; i < this.props.paginationInfo.totalPagines; i++) {
        pagines.push(
          <Item key={i} value={i} onClick={onClick} active={this.props.paginationInfo.paginaActual == i}>
            {"" + (i + 1)}
          </Item>
        );
      }
    }

    // next
    pagines.push(
      <Item
        value={Math.min(this.props.paginationInfo.paginaActual + 1, this.props.paginationInfo.totalPagines - 1)}
        onClick={onClick}
      >
        ›
      </Item>
    );
    // Last
    pagines.push(
      <Item value={this.props.paginationInfo.totalPagines - 1} onClick={onClick}>
        »
      </Item>
    );

    /* Mostrant elements del {{from}} al {{to}} d'un total de {{total}} elements*/
    let message: string = this.props.i18n.t("paginacioLabel", { current: current, total: total, from: from, to: to });

    let pagination: JSX.Element = (
      <nav aria-label="Page navigation">
        <ul
          style={{ float: reactdetect.isMobileOnly ? "none" : "right", paddingRight: "0.7em" }}
          className="pagination justify-content-center"
        >
          {pagines}
        </ul>
      </nav>
    );

    if (reactdetect.isMobileOnly) {
      return (
        <center>
          {message}
          <br />
          {pagination}
        </center>
      );
    } else {
      return (
        <>
          <div style={{ float: "left", marginTop: "9px", width: "60%" }}>{message}</div>

          {pagination}
        </>
      );
    }
  }
}

export default PaginationCarpeta;
