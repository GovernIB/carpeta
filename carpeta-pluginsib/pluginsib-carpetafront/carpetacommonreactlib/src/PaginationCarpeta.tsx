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

interface PaginationCarpetaState {
  paginationInfo: PaginationInfo | null;
}

class PaginationCarpeta extends React.Component<PaginationCarpetaProps, PaginationCarpetaState> {
  private static readonly ROTATE_RIGHT_STYLE: React.CSSProperties = {
    transform: "rotate(90deg)",
  };

  private static readonly ROTATE_LEFT_STYLE: React.CSSProperties = {
    transform: "rotate(-90deg)",

    /* Legacy vendor prefixes that you probably don't need... */

    // Safari
    //-webkit-transform:"rotate(-90deg)",

    /*
      // Firefox 
      -moz-transform: "rotate(-90deg)"
    
      // IE 
      -ms-transform: "rotate(-90deg)",
    
      // Opera 
      -o-transform: "rotate(-90deg)",
    
      // Internet Explorer
      filter: "progid:DXImageTransform.Microsoft.BasicImage(rotation=3)"
      */
  };

  constructor(props: PaginationCarpetaProps) {
    super(props);

    console.log("  CONSTRUCTOR PaginationCarpeta !!!!!");
    /*
    console.log("PaginationCarpeta().paginaActual  => " + this.props.paginationInfo.paginaActual);
    console.log("PaginationCarpeta().elementsPerPagina  => " + this.props.paginationInfo.elementsPerPagina);
    console.log("PaginationCarpeta().totalPagines  => " + this.props.paginationInfo.totalPagines);
    console.log("PaginationCarpeta().registresRetornats  => " + this.props.paginationInfo.registresRetornats);
    console.log("PaginationCarpeta().totalRegistres  => " + this.props.paginationInfo.totalRegistres);
    */
    initTranslation(props.i18n);
    this.updatePaginationInfo = this.updatePaginationInfo.bind(this);

    this.state = {
      paginationInfo: null,
    };
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

    const t = this.props.i18n.t;

    let paginationInfo: PaginationInfo | null = this.state.paginationInfo;

    if (paginationInfo == null || paginationInfo.totalElements == 0) {
      //console.log("PaginationCarpeta::render() =>  paginationInfo == null");
      return <></>;
    }

    const total: number = paginationInfo.totalElements;
    const from: number = paginationInfo.paginaActual * paginationInfo.elementsPerPagina + 1;
    const to: number = Math.min(
      paginationInfo.totalElements,
      (1 + paginationInfo.paginaActual) * paginationInfo.elementsPerPagina
    );

    /* Mostrant elements del {{from}} al {{to}} d'un total de {{total}} elements*/
    //console.log("PaginationCarpeta::render() =>  Desplegable elementsPerPàgina ...");

    let message: string = t("paginacioLabel", {
      total: total,
      from: from,
      to: to,
    });

    let onClick = this.props.onClickPagination;
    let onClickSelectElementsByPage: Function | undefined = undefined;

    let pagination: JSX.Element = <></>;
    let numElements: JSX.Element = <></>;

    if (paginationInfo.totalPagines != 1) {
      //console.log("PaginationCarpeta::render() =>  selectElementsByPage ...");
      onClickSelectElementsByPage = this.props.onClickSelectElementsByPage;


      let maxCasellesPerMostrar: number;
      let w: number = window.innerWidth;

      console.log("PaginationCarpeta::render() => window.innerWidth = " + w);

      // Serveix per ajustar la funció de càlcul de casseles a pintar
      const ajust: number = reactdetect.isMobileOnly ? 0 : -2;
      if (w < 920) {
        maxCasellesPerMostrar = 5;
      } else if (w > 1400) {
        maxCasellesPerMostrar = 13 + ajust;
      } else {
        maxCasellesPerMostrar = Math.floor((9 * w - 5880) / 480) + ajust;
      }

      console.log("PaginationCarpeta::render() => caselles = " + maxCasellesPerMostrar);

      //TODO FALTA MOBILE
      // XYZ ZZZ

      let start: number;
      let end: number;

      let showPoints: boolean;
      console.log("PaginationCarpeta::render() => paginationInfo.totalPagines = " + paginationInfo.totalPagines);
      if (paginationInfo.totalPagines < maxCasellesPerMostrar) {
        start = 0;
        end = paginationInfo.totalPagines;
        showPoints = false;
      } else {
        // start = Math.max(0, paginationInfo.paginaActual - Math.floor(maxCasellesPerMostrar / 2));
        // end = Math.min(start + maxCasellesPerMostrar, paginationInfo.totalPagines);

        start = paginationInfo.paginaActual - Math.floor(maxCasellesPerMostrar / 2);
        end = paginationInfo.paginaActual + Math.floor(maxCasellesPerMostrar / 2);

        if (start < 0) {
          // Passam els numeros negatius a end
          end = end - start;
          start = 0;
        } else {
          // Passam els numeros que passen de totalPagines a start
          if (end > paginationInfo.totalPagines) {
            start = start - (end - paginationInfo.totalPagines);
            end = paginationInfo.totalPagines;
          }
        }

        start = Math.max(0, start);
        end = Math.min(end, paginationInfo.totalPagines);

        showPoints = true;
      }

      console.log("PaginationCarpeta::render() => start = " + start);
      console.log("PaginationCarpeta::render() => end = " + end);
      console.log("PaginationCarpeta::render() => showPoints = " + showPoints);

      //console.log("PaginationCarpeta::render() =>  Functions ...");

      //Si el parametre onClickSelectElementsByPage te valor, existeix una funcio per canviar el nº de elements
      // per tant s'ha de incloure l'element per canviar el nº de elements.

      // Si el parametre elementsByPage te valors, agafarlos, si no, agafar 5, 10 i 25 per defecte.
      //console.log("PaginationCarpeta::render() =>  pagines ...");
      let pagines: any = [];

      // First |<
      if (showPoints && start != 0) {
        pagines.push(
          <Item value={0} onClick={onClick} title={t("pagination.primerapagina")}>
            <div style={PaginationCarpeta.ROTATE_LEFT_STYLE}> &#8892;</div>
          </Item>
        );
      }

      // Retrocedir <<
      let retrocedir: number = Math.floor(start / 2);
      let retrocedirTitle: string = t("pagination.retrocedir", { pagina: retrocedir });

      if (showPoints && start != 0) {
        pagines.push(
          <Item key={-1} value={retrocedir} title={retrocedirTitle} onClick={onClick}>
            &#8810;
          </Item>
        );
      }

      // Previous <
      if (start != 0) {
        pagines.push(
          <Item value={Math.max(0, paginationInfo.paginaActual - 1)} title={t("pagination.anterior")} onClick={onClick}>
            &#60;
          </Item>
        );
      }

      // ... a l'esquerra
      /*
      if (showPoints && start != 0) {
        pagines.push(
          <Item key={-1} value={retrocedir} title={retrocedirTitle} onClick={onClick}>
            {"…"}
          </Item>
        );
      }
      */

      // All pagination numbers
      for (let i = start; i < end; i++) {
        if (i >= 0 && i <= paginationInfo.totalPagines) {
          pagines.push(
            <Item key={i} value={i} onClick={onClick} active={paginationInfo.paginaActual == i}>
              {"" + (i + 1)}
            </Item>
          );
        }
      }

      // ... a la dreta

      let avancar: number = end + Math.floor((paginationInfo.totalPagines - end) / 2);
      let avancarTitle: string = t("pagination.avancar", { pagina: avancar });
      /*
      if (showPoints && end != paginationInfo.totalPagines) {
        pagines.push(
          <Item key={-2} onClick={onClick} value={avancar} title={avancarTitle}>
            {"…"}
          </Item>
        );
      }
      */

      // Seguent >
      if (end != paginationInfo.totalPagines) {
        pagines.push(
          <Item
            value={Math.min(paginationInfo.paginaActual + 1, paginationInfo.totalPagines - 1)}
            onClick={onClick}
            title={t("pagination.seguent")}
          >
            &#62;
          </Item>
        );
      }

      // Avançar >>
      if (showPoints && end != paginationInfo.totalPagines) {
        pagines.push(
          <Item key={-2} onClick={onClick} value={avancar} title={avancarTitle}>
            &#8811;
          </Item>
        );
      }

      // Last  >|
      if (showPoints && end != paginationInfo.totalPagines) {
        pagines.push(
          <Item value={paginationInfo.totalPagines - 1} onClick={onClick} title={t("pagination.darrerapagina")}>
            <div style={PaginationCarpeta.ROTATE_RIGHT_STYLE}>&#8892;</div>
          </Item>
        );
      }

      //Options per numero de elements a pintar per pagina
      //console.log("PaginationCarpeta::render() =>  onClickSelectElementsByPage ...");

      let selectElementsByPage = this.props.selectElementsByPage;
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
            {this.props.i18n.t("elementPerPaginaMostra")} &nbsp;
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
            &nbsp;
            {this.props.i18n.t("elementPerPaginaElements")}
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
            <div className="container-fluid mt-1">{numElements}</div>
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
            <div className="text-nowrap">
              <div className="row d-flex">
                <div className="col" style={{ marginRight: "0px", marginLeft: "0px", paddingLeft: "20px" }}>
                  {message}
                </div>
                <div
                  className="pagination col justify-content-center"
                  style={{ marginRight: "0px", marginLeft: "0px" }}
                >
                  {pagination}
                </div>
                <div className="pagination col justify-content-end" style={{ marginRight: "0px", marginLeft: "0px" }}>
                  {numElements}
                </div>
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
