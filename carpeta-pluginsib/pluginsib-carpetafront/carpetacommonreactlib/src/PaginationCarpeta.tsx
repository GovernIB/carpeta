/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */

import React from "react";

import initTranslation from "./InitTranslation";
import { PaginationItemCarpeta as Item } from "./PaginationItemCarpeta";
import * as reactdetect from "react-device-detect";
import PaginationCarpetaPropsWithTranslation from "./PaginationCarpetaPropsWithTranslation";

class PaginationCarpeta extends React.Component<PaginationCarpetaPropsWithTranslation> {
  constructor(props: PaginationCarpetaPropsWithTranslation) {
    super(props);
    console.log("  CONSTRUCTOR PaginationCarpeta !!!!!");

    console.log(
      "PaginationCarpeta().paginaActual  => " +
        this.props.paginationInfo.paginaActual
    );
    console.log(
      "PaginationCarpeta().elementsPerPagina  => " +
        this.props.paginationInfo.elementsPerPagina
    );
    console.log(
      "PaginationCarpeta().totalPagines  => " +
        this.props.paginationInfo.totalPagines
    );
    console.log(
      "PaginationCarpeta().registresRetornats  => " +
        this.props.paginationInfo.registresRetornats
    );
    console.log(
      "PaginationCarpeta().totalRegistres  => " +
        this.props.paginationInfo.totalRegistres
    );

    initTranslation(this.props.i18n);

    console.log("\n\nCONSTRUCTOR  XXXXXX: " + this.props.paginationInfo.elementsByPage + "\n\n")

    if (this.props.paginationInfo.elementsByPage == undefined) {
      this.props.paginationInfo.elementsByPage = [5, 10, 25];
    }
  }

  render() {
    console.log("  RENDER PaginationCarpeta !!!!!");

    const current: number = this.props.paginationInfo.registresRetornats;
    const total: number = this.props.paginationInfo.totalRegistres;
    const from: number =
      this.props.paginationInfo.paginaActual *
        this.props.paginationInfo.elementsPerPagina +
      1;
    const to: number = Math.min(
      this.props.paginationInfo.totalRegistres,
      (1 + this.props.paginationInfo.paginaActual) *
        this.props.paginationInfo.elementsPerPagina
    );

    let pagines: any = [];

    let onClick: Function = this.props.paginationInfo.onClickPagination;
    let onClickElementsByPage: Function =
      this.props.paginationInfo.onClickElementsByPage;

    //Si el parametre onClickElementsByPage te valor, existeix una funcio per canviar el nº de elements
    // per tant s'ha de incloure l'element per canviar el nº de elements.

    // Si el parametre elementsByPage te valors, agafarlos, si no, agafar 5, 10 i 25 per defecte.

    // First
    pagines.push(
      <Item value={0} onClick={onClick}>
        «
      </Item>
    );
    // Previous
    pagines.push(
      <Item
        value={Math.max(0, this.props.paginationInfo.paginaActual - 1)}
        onClick={onClick}
      >
        ‹
      </Item>
    );

    // All pagination numbers
    {
      for (let i = 0; i < this.props.paginationInfo.totalPagines; i++) {
        pagines.push(
          <Item
            key={i}
            value={i}
            onClick={onClick}
            active={this.props.paginationInfo.paginaActual == i}
          >
            {"" + (i + 1)}
          </Item>
        );
      }
    }

    // next
    pagines.push(
      <Item
        value={Math.min(
          this.props.paginationInfo.paginaActual + 1,
          this.props.paginationInfo.totalPagines - 1
        )}
        onClick={onClick}
      >
        ›
      </Item>
    );
    // Last
    pagines.push(
      <Item
        value={this.props.paginationInfo.totalPagines - 1}
        onClick={onClick}
      >
        »
      </Item>
    );

    //Options per numero de elements a pintar per pagina
    let numElements: JSX.Element;
    if (onClickElementsByPage) {
      let numElementOptions: any = [];

      //this.props.paginationInfo.elementsByPage.map((element) => numElementOptions.push(<option>{element}</option>));
      /*for (var i in this.props.paginationInfo.elementsByPage) {
        console.log("XYZ ZZZ Assignant valor numElements.");
        numElementOptions.push(
          <option key={i}>{this.props.paginationInfo.elementsByPage[i]}</option>
        );
      }
      */

      for (
        var i = 0;
        i < this.props.paginationInfo.elementsByPage.length;
        i++
      ) {
        numElementOptions.push(
          <option key={i} selected={this.props.paginationInfo.elementsByPage[i]==this.props.paginationInfo.elementsPerPagina}>{this.props.paginationInfo.elementsByPage[i]}</option>
        );
      }

      numElements = (
        <div
          className="pagination"
          style={{
            float: "none",
            alignItems: "center",
            justifyContent: "center",
            marginRight: "10px"
          }}
        >
          {this.props.i18n.t("registroMostra")}
          <select
            onChange={(e) => {
              onClickElementsByPage(e.target.value);
            }}
          >
            {numElementOptions}
          </select>
          {this.props.i18n.t("registroRegistres")}
        </div>
      );
    } else {
      console.log("No s'ha definit la funció onClickElementsByPage.");
    }

    /* Mostrant elements del {{from}} al {{to}} d'un total de {{total}} elements*/
    let message: string = this.props.i18n.t("paginacioLabel", {
      current: current,
      total: total,
      from: from,
      to: to,
    });

    let pagination: JSX.Element = (
      <nav aria-label="Page navigation">
        <ul
          key="pn_1"
          className="pagination"
          style={{
            float: "none",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          {pagines}
        </ul>
      </nav>
    );

    if (reactdetect.isMobileOnly) {
      if (onClickElementsByPage) {
        return (
          <center>
            <div className="container-fluid mt-1">{message}</div>
            <br />
            <div className="container-fluid mt-1">{pagination}</div>
            <br />
            <div className="container-fluid mt-1"> {numElements}</div>
          </center>
        );
      } else {
        return (
          <center>
            {message}
            <br />
            <div className="pagination justify-content-center">
              {pagination}
            </div>
          </center>
        );
      }
    } else {
      if (onClickElementsByPage) {
        return (
          <>
            <div className="container-fluid">
              <div className="row d-flex">
                <div className="col">{message}</div>
                <div className="pagination col justify-content-center" style={{marginRight: "10px"}}>{pagination}</div>
                <div className="pagination col justify-content-end" >{numElements}</div>
              </div>
            </div>
          </>
        );
      } else {
        return (
          <>
            <div style={{ float: "left", marginTop: "9px", width: "50%" }}>
              {message}
            </div>

            {pagination}
          </>
        );
      }

    }
  }
}

export default PaginationCarpeta;
