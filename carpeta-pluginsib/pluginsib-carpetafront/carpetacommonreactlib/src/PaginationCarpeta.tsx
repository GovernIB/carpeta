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
import { PaginationCarpetaProps, PaginationInfo } from "./PaginationCarpetaProps";
import RenderPaginationTable from "./RenderPaginationTable";

interface PaginationCarpetaState  {
  paginationInfo: PaginationInfo | null;
}


class PaginationCarpeta extends React.Component<PaginationCarpetaProps, PaginationCarpetaState> {


  constructor(props: PaginationCarpetaProps) {
    super(props);
    
    console.log("  CONSTRUCTOR PaginationCarpeta !!!!!");
/*
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
*/
    initTranslation(this.props.i18n);
    this.updatePaginationInfo = this.updatePaginationInfo.bind(this);

    this.state = {
       paginationInfo : null
    }

  }




  updatePaginationInfo(paginationInfo: PaginationInfo | null) {
    
    if (paginationInfo == null) {
      console.log("PaginationCarpeta::updatePaginationInfo(CARREGANT...)");
    } else {
      console.log("PaginationCarpeta::updatePaginationInfo(CARREGAR DADES ...)");
    }
    this.setState({ paginationInfo: paginationInfo });
  }



  render() {
    console.log("PaginationCarpeta::render() =>  START");

    let paginationInfo: PaginationInfo | null = this.state.paginationInfo;

    if (paginationInfo == null || paginationInfo.totalElements == 0) {
      //console.log("PaginationCarpeta::render() =>  paginationInfo == null");
      return <></>;
    }


    
    //console.log("PaginationCarpeta::render() =>  this.props.paginationInfo ...");
    const current: number = paginationInfo.elementsRetornats;
    const total: number = paginationInfo.totalElements;
    const from: number = paginationInfo.paginaActual * paginationInfo.elementsPerPagina + 1;
    const to: number = Math.min(
      paginationInfo.totalElements,
      (1 + paginationInfo.paginaActual) * paginationInfo.elementsPerPagina
    );

        /* Mostrant elements del {{from}} al {{to}} d'un total de {{total}} elements*/
    //console.log("PaginationCarpeta::render() =>  Desplegable elementsPerPàgina ...");
    
    let message: string = this.props.i18n.t("paginacioLabel", {
      current: current,
      total: total,
      from: from,
      to: to,
    });

    let onClick: Function = this.props.onClickPagination;
    let onClickSelectElementsByPage: Function | undefined = undefined;

    let pagination:JSX.Element = <></>;
    let numElements: JSX.Element = <></>;


    if (paginationInfo.totalPagines != 1) {

    //console.log("PaginationCarpeta::render() =>  selectElementsByPage ...");
    onClickSelectElementsByPage = this.props.onClickSelectElementsByPage;

    




    //console.log("PaginationCarpeta::render() =>  Functions ...");
    



    //Si el parametre onClickSelectElementsByPage te valor, existeix una funcio per canviar el nº de elements
    // per tant s'ha de incloure l'element per canviar el nº de elements.

    // Si el parametre elementsByPage te valors, agafarlos, si no, agafar 5, 10 i 25 per defecte.
    //console.log("PaginationCarpeta::render() =>  pagines ...");
    let pagines: any = [];

    // First
    pagines.push(
      <Item value={0} onClick={onClick}>
        «
      </Item>
    );
    // Previous
    pagines.push(
      <Item value={Math.max(0, paginationInfo.paginaActual - 1)} onClick={onClick}>
        ‹
      </Item>
    );

    // All pagination numbers
    {
      for (let i = 0; i < paginationInfo.totalPagines; i++) {
        pagines.push(
          <Item key={i} value={i} onClick={onClick} active={paginationInfo.paginaActual == i}>
            {"" + (i + 1)}
          </Item>
        );
      }
    }

    // next
    pagines.push(
      <Item
        value={Math.min(paginationInfo.paginaActual + 1, paginationInfo.totalPagines - 1)}
        onClick={onClick}
      >
        ›
      </Item>
    );
    // Last
    pagines.push(
      <Item value={paginationInfo.totalPagines - 1} onClick={onClick}>
        »
      </Item>
    );

    //Options per numero de elements a pintar per pagina
    //console.log("PaginationCarpeta::render() =>  onClickSelectElementsByPage ...");

    let selectElementsByPage =  this.props.selectElementsByPage
    if (selectElementsByPage == undefined) {
      selectElementsByPage = RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE;
    }
    
    if (selectElementsByPage != null) {
      let numElementOptions: any = [];

      //this.props.paginationInfo.elementsByPage.map((element) => numElementOptions.push(<option>{element}</option>));
      /*for (var i in this.props.paginationInfo.elementsByPage) {
        console.log("XYZ ZZZ Assignant valor numElements.");
        numElementOptions.push(
          <option key={i}>{this.props.paginationInfo.elementsByPage[i]}</option>
        );
      }
      */
      for (var i = 0; i < selectElementsByPage.length; i++) {
        numElementOptions.push(
          <option key={i} value={selectElementsByPage[i]}>
            {selectElementsByPage[i]}
          </option>
        );
      }

      numElements = (
        <div
          className="pagination"
          style={{
            float: "none",
            alignItems: "center",
            justifyContent: "center",
            marginRight: "10px",
          }}
        >
          {this.props.i18n.t("registroMostra")}
          <select
            defaultValue={paginationInfo.elementsPerPagina}
            onChange={(e) => {
              if (onClickSelectElementsByPage) {
                onClickSelectElementsByPage(e.target.value);
              }
            }}
          >
            {numElementOptions}
          </select>
          {this.props.i18n.t("registroRegistres")}
        </div>
      );
    }



    pagination = (
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

        }



    if (reactdetect.isMobileOnly) {
      if (onClickSelectElementsByPage) {
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
            <div className="pagination justify-content-center">{pagination}</div>
          </center>
        );
      }
    } else {
      if (onClickSelectElementsByPage) {
        return (
          <>
            <div className="container-fluid">
              <div className="row d-flex">
                <div className="col">{message}</div>
                <div className="pagination col justify-content-center" style={{ marginRight: "10px" }}>
                  {pagination}
                </div>
                <div className="pagination col justify-content-end">{numElements}</div>
              </div>
            </div>
          </>
        );
      } else {
        return (
          <>
            <div style={{ float: "left", marginTop: "9px", width: "50%" }}>{message}</div>

            {pagination}
          </>
        );
      }
    }
  }
}

export default PaginationCarpeta;
