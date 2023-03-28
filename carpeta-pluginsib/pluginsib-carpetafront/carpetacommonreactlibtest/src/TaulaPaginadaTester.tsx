/**
 * @author anadal
 * @email [example@mail.com]
 * @create date 2023-02-01 09:19:13
 * @modify date 2023-02-01 09:19:13
 * @desc [description]
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import {
  TemplatePageCarpeta,
  RenderPaginationTable,
  RowType,
  RowTypeUtils,
  RenderPaginationTableData,
  PaginationInfo,
  ReturnPaginationData,
} from "carpetacommonreactlib";

interface TaulaPaginadaTesterProps extends WithTranslation {}

type TaulaPaginadaTesterState = {
  totalElements: number;
  enableElementsPerPagina: boolean;
  selectElementsPerPagina: number[] | undefined | null;
};

class TaulaPaginadaTester extends React.Component<TaulaPaginadaTesterProps, TaulaPaginadaTesterState> {

  private static MAXVALUES:number = 97;

  private titles = { es: "Pruebas", ca: "Proves" };
  private subtitles = { es: "Pruebas Subtitle", ca: "Proves Subtitle" };

  private allExpedients: any[];

  constructor(props: TaulaPaginadaTesterProps) {
    super(props);
    console.log("  CONSTRUCTOR TaulaPaginadaTester !!!!!");

    var expedients = [];

    const apoderaBotoContent: JSX.Element = (
      <div style={{ width: "auto", float: "right", position: "relative", top: "0px" }} id="accedirApodera">
        <span>
          <button
            className="btn btn-primary carpeta-btn botoAccedirCarpeta"
            title="accedirLabel"
            aria-labelledby="accedirApodera"
            onClick={() => {
              window.open("https://otae.fundaciobit.org", "_blank");
            }}
          >
            Consulti la pàgina web&nbsp;{RowTypeUtils.getIcon(RowType.EXTERNAL_LINK, false)}
          </button>
        </span>
      </div>
    );

    for (let i = 0; i < TaulaPaginadaTester.MAXVALUES; i++) {
      let num = i + 1;
      expedients.push({
        expedientObertura: num + "/02/2023",
        expedientNom: "Expedient " + num,
        nomProcediment: "NOm proc " + num,
        codiSia: " SIA0" + num,
        expedientOrgans: "CAIB",
        expedientEstat: num % 2 == 0 ? "Obert" : "Tancat",
        codiSia2: " SIA_22_0" + num,
        expedientOrgans2: "Govern de les Illes",
        expedientEstat2: num % 2 == 0 ? "Obert amb explicació" : "Tancat amb explicació",
        boto: apoderaBotoContent,
      });
    }

    this.allExpedients = expedients;

    this.state = {
      totalElements: 17,
      enableElementsPerPagina: true,
      selectElementsPerPagina: RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE,
    };

    this.onClickCanviTotalElements = this.onClickCanviTotalElements.bind(this);
    this.onClickConjuntElementsPerPagina = this.onClickConjuntElementsPerPagina.bind(this);

    this.canviaConfiguracio = this.canviaConfiguracio.bind(this);

    this.loadPaginatedData = this.loadPaginatedData.bind(this);
    this.loadPaginatedDataAsync = this.loadPaginatedDataAsync.bind(this);
  }

  componentDidMount() {
    this.canviaConfiguracio(
      this.state.totalElements,
      this.state.enableElementsPerPagina,
      this.state.selectElementsPerPagina
    );
  }

  canviaConfiguracio(
    totalElements: number,
    enableElementsPerPagina: boolean,
    llistatElementsPerPagina: number[] | undefined | null
  ) {
    console.log("TaulaPaginadaTester::canviaConfiguracio() => Inici");
    var expedients: any[] = [];

    if (!enableElementsPerPagina) {
      llistatElementsPerPagina = null;
    }

    let elementsPerPagina: number;
    if (llistatElementsPerPagina == undefined || llistatElementsPerPagina == null) {
      elementsPerPagina = RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE[0];
    } else {
      elementsPerPagina = llistatElementsPerPagina[0];
    }

    let paginaActual: number = 0;

    let start: number = paginaActual * elementsPerPagina;
    let next: number = parseInt(start.toString()) + parseInt(elementsPerPagina.toString());
    let end: number = Math.min(totalElements, next);

    console.log(" ===================CANVIA CONFIGURACIO =========================");
    console.log("totalElements: " + totalElements);
    console.log(" start + elementsPerPagina: " + (start + elementsPerPagina));
    console.log(" Next: " + next);
    console.log(" Pagina: " + paginaActual);
    console.log(" elementsPerPagina: " + elementsPerPagina);
    console.log(" START: " + start);
    console.log(" END: " + end);

    for (let i = start; i < end; i++) {
      expedients.push(this.allExpedients[i]);
    }

    console.log("Elements per Pagina Conjunt => " + llistatElementsPerPagina);
    this.setState({
      totalElements: totalElements,
      enableElementsPerPagina: enableElementsPerPagina,
      selectElementsPerPagina: llistatElementsPerPagina,
    });
  }

  onClickCanviTotalElements(totalElements: number) {
    console.log("TaulaPaginadaTester::onClickCanviTotalElements() => totalElements=" + totalElements);
    this.canviaConfiguracio(totalElements, this.state.enableElementsPerPagina, this.state.selectElementsPerPagina);
  }

  onCheckElementsPerPagina(enableElementsPerPagina: boolean) {
    this.canviaConfiguracio(this.state.totalElements, enableElementsPerPagina, this.state.selectElementsPerPagina);
  }

  onClickConjuntElementsPerPagina(llista: number[] | undefined) {
    console.log("onClickConjuntElementsPerPagina::llista => " + llista);

    this.canviaConfiguracio(this.state.totalElements, this.state.enableElementsPerPagina, llista);
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let that = this;
    setTimeout(function () {
      that.loadPaginatedDataAsync(loadData);
    }, 1250);
  }

  loadPaginatedDataAsync(loadData: ReturnPaginationData) {
    console.log("TaulaPaginadaTester::loadPaginatedData() ENTRA " + new Date());

    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    let totalElements = this.state.totalElements;

    let start: number = page * elementsByPage;
    let next: number = parseInt(start.toString()) + parseInt(elementsByPage.toString());
    let end: number = Math.min(totalElements, next);

    /*
    console.log("totalElements: " + totalElements);
    console.log(" start + elementsPerPagina: " + (start + elementsByPage));
    console.log(" Next: " + next);
    */
    console.log("TaulaPaginadaTester::loadPaginatedData() => Pagina: " + page);
    console.log("TaulaPaginadaTester::loadPaginatedData() => elementsPerPagina: " + elementsByPage);
    /*
    console.log(" START: " + start);
    console.log(" END: " + end);
    */

    var expedients: any[] = [];
    for (let i = start; i < end; i++) {
      expedients.push(this.allExpedients[i]);
    }

    let paginationInfo: PaginationInfo = {
      paginaActual: page,
      elementsPerPagina: elementsByPage,
      totalPagines: Math.floor(totalElements / elementsByPage) + (totalElements % elementsByPage == 0 ? 0 : 1),
      elementsRetornats: expedients.length,
      totalElements: totalElements,
    };

    let returnData: RenderPaginationTableData = {
      tableData: expedients,
      error: null,
      paginationInfo: paginationInfo,
    };

    console.log("TaulaPaginadaTester::loadPaginatedData() => paginationInfo:" + paginationInfo);
    console.log("TaulaPaginadaTester::loadPaginatedData() => expedients:" + expedients);

    console.log("TaulaPaginadaTester::loadPaginatedData() SURT " + new Date());

    // Enviam resultat a la taula
    loadData.returnDataFunction(returnData);
  }

  render() {
    console.log("TaulaPaginadaTester::render() => Inici !!!!!");

    const { i18n } = this.props;

    const columnsNom = [
      "expedientObertura",
      "expedientNom",
      "nomProcediment",
      "codiSia",
      "expedientOrgans",
      "expedientEstat",
    ];

    const columnsTitols = [
      i18n.t("expedientObertura"),
      i18n.t("expedientNom"),
      i18n.t("nomProcediment"),
      i18n.t("codiSia"),
      i18n.t("expedientOrgans"),
      i18n.t("expedientEstat"),
    ];

    const columnsNomAddicionals = ["codiSia2", "expedientOrgans2", "expedientEstat2", "boto"];

    const columnsTitolsAddicionals = [
      i18n.t("codiSia2"),
      i18n.t("expedientOrgans2"),
      i18n.t("expedientEstat2"),
      "", // BOTO
    ];

    return (
      <>
        <br />
        <hr />
        Numero d'Elements Total:
        <button onClick={() => this.onClickCanviTotalElements(0)}> 0</button>
        <button onClick={() => this.onClickCanviTotalElements(3)}> 3</button>
        <button onClick={() => this.onClickCanviTotalElements(10)}> 10</button>
        <button onClick={() => this.onClickCanviTotalElements(17)}> 17</button>
        <button onClick={() => this.onClickCanviTotalElements(39)}> 39</button>
        <button onClick={() => this.onClickCanviTotalElements(46)}> 46</button>
        <button onClick={() => this.onClickCanviTotalElements(59)}> 59</button>
        <button onClick={() => this.onClickCanviTotalElements(TaulaPaginadaTester.MAXVALUES)}> {TaulaPaginadaTester.MAXVALUES}</button>
        &nbsp;&nbsp;&nbsp;&nbsp; Mostrar Elements per pàgina:
        <input
          type="checkbox"
          checked={this.state.enableElementsPerPagina}
          onChange={(e) => {
            this.onCheckElementsPerPagina(e.target.checked);
          }}
        />
        <>
          &nbsp;&nbsp;&nbsp;&nbsp; Elements per Pàgina:
          <button onClick={() => this.onClickConjuntElementsPerPagina(undefined)}> [NO DEFINIT]</button>
          <button onClick={() => this.onClickConjuntElementsPerPagina([3, 13, 23, 33])}> [3, 13, 23, 33]</button>
          <button onClick={() => this.onClickConjuntElementsPerPagina([20, 40, 60])}> [20, 40, 60]</button>
        </>
        <hr />
        <TemplatePageCarpeta {...this.props} titles={this.titles} subtitles={this.subtitles} i18n={i18n}>
          <RenderPaginationTable
            loadPaginatedData={this.loadPaginatedData}
            columnNames={columnsNom}
            columnTitles={columnsTitols}
            columnNamesAdditionals={columnsNomAddicionals}
            columnTitlesAdditionals={columnsTitolsAddicionals}
            rowType={RowType.SHOW_ADDITIONAL_INFO}
            mobileIcon={"oi-clipboard"}
            i18n={i18n}
            selectElementsByPage={this.state.selectElementsPerPagina}
          />
        </TemplatePageCarpeta>
      </>
    );
  }
}

export default withTranslation()(TaulaPaginadaTester);
