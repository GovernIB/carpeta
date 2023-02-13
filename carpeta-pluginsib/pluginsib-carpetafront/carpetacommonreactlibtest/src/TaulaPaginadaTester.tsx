/**
 * @author anadal
 * @email [example@mail.com]
 * @create date 2023-02-01 09:19:13
 * @modify date 2023-02-01 09:19:13
 * @desc [description]
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import { TemplatePageCarpeta, PaginationCarpetaProps, RenderPaginationTable } from "carpetacommonreactlib";

interface TaulaPaginadaTesterProps extends WithTranslation {}

type TaulaPaginadaTesterState = {
  isLoaded: boolean;

  expedientresposta: any;
  error: string | null;

  elementsPerPagina: number; // 0 ==> No mostrar deplegable
  totalElements: number;
  paginaActual: number;
  enableElementsPerPagina: boolean;
  selectElementsPerPagina: number[] | undefined;
  allExpedients: any[];
};

class TaulaPaginadaTester extends React.Component<TaulaPaginadaTesterProps, TaulaPaginadaTesterState> {
  titles = { es: "Pruebas", ca: "Proves" };
  subtitles = { es: "Pruebas Subtitle", ca: "Proves Subtitle" };

  constructor(props: TaulaPaginadaTesterProps) {
    super(props);
    console.log("  CONSTRUCTOR TaulaPaginadaTester !!!!!");

    var expedients = [];

    for (let i = 0; i < 67; i++) {
      let num = i + 1;
      expedients.push({
        expedientObertura: num + "/02/2023",
        expedientNom: "Expedient " + num,
        nomProcediment: "NOm proc " + num,
        codiSia: " SIA0" + num,
        expedientOrgans: "CAIB",
        expedientEstat: num % 2 == 0 ? "Obert" : "Tancat",
      });
    }

    this.state = {
      isLoaded: false,
      expedientresposta: null,
      allExpedients: expedients,
      error: null,
      paginaActual: 0,
      totalElements: 17,
      elementsPerPagina: 5,
      enableElementsPerPagina: true,
      selectElementsPerPagina: [5,10,15],
    };

    this.onClickCanviDePagina = this.onClickCanviDePagina.bind(this);

    this.onClickCanviTotalElements = this.onClickCanviTotalElements.bind(this);

    this.onClickElementsPerPagina = this.onClickElementsPerPagina.bind(this);

    this.onClickConjuntElementsPerPagina = this.onClickConjuntElementsPerPagina.bind(this);

    this.canviaConfiguracio = this.canviaConfiguracio.bind(this);
  }

  componentDidMount() {
    this.canviaConfiguracio(
      this.state.totalElements,
      this.state.enableElementsPerPagina,
      this.state.elementsPerPagina,
      this.state.paginaActual
    );
  }

  canviaConfiguracio(
    totalElements: number,
    enableElementsPerPagina: boolean,
    elementsPerPagina: number,
    paginaActual: number,
  ) {
    var expedients: any[] = [];

    var all: any[] = this.state.allExpedients;

    const start: number = paginaActual * elementsPerPagina;
    const end: number = Math.min(totalElements, start + elementsPerPagina);

    for (let i = start; i < end; i++) {
      expedients.push(all[i]);
    }

    var expedientsResposta = {
      paginaActual: paginaActual,
      elementsPerPagina: elementsPerPagina,
      totalRegistres: totalElements,

      totalPagines: Math.floor(totalElements / elementsPerPagina) + (totalElements % elementsPerPagina == 0 ? 0 : 1),
      registresRetornats: expedients.length,
      expedients: expedients,
    };

    this.setState({
      ...this.state,
      paginaActual: paginaActual,
      elementsPerPagina: elementsPerPagina,
      totalElements: totalElements,
      enableElementsPerPagina: enableElementsPerPagina,
      expedientresposta: expedientsResposta,
      error: null,
      isLoaded: true
    });

  }

  onClickCanviDePagina(novaPagina: number) {
    console.log("onClickPagination a => " + novaPagina);
    this.canviaConfiguracio(
      this.state.totalElements,
      this.state.enableElementsPerPagina,
      this.state.elementsPerPagina,
      novaPagina
    );
  }

  onClickCanviTotalElements(totalElements: number) {
    console.log("onClickCanviTotalElements a => " + totalElements);
    const paginaActual: number = 0;
    this.canviaConfiguracio(
      totalElements,
      this.state.enableElementsPerPagina,
      this.state.elementsPerPagina,
      paginaActual
    );
  }

  onClickElementsPerPagina(elementsPerPagina: number) {
    console.log("onClickElementsPerPagina a => " + elementsPerPagina);
    const paginaActual: number = 0;
    this.canviaConfiguracio(
      this.state.totalElements,
      this.state.enableElementsPerPagina,
      elementsPerPagina,
      paginaActual,
    );
  }

  onCheckElementsPerPagina(enableElementsPerPagina: boolean) {
    this.canviaConfiguracio(
      this.state.totalElements,
      enableElementsPerPagina,
      this.state.elementsPerPagina,
      this.state.paginaActual
    );
  }

  onClickConjuntElementsPerPagina(llista: number[] | undefined) {
    console.log("onClickConjuntElementsPerPagina::llista => " + llista);

    let elementsPerPagina;
    if (llista == undefined) {
      elementsPerPagina = 5;
    } else {
      elementsPerPagina = llista[0];
    }

    console.log("onClickConjuntElementsPerPagina::elementsPerPagina => " + elementsPerPagina);

    this.setState({
      ...this.state,
      selectElementsPerPagina: llista,
      paginaActual: 0,
      elementsPerPagina: elementsPerPagina,
    });

    this.canviaConfiguracio(this.state.totalElements, this.state.enableElementsPerPagina, elementsPerPagina, 0, llista);
  }

  render() {
    console.log("  RENDER TaulaPaginadaTester !!!!!");

    const { i18n } = this.props;

    let content;

    if (!this.state.isLoaded) {
      console.log(" No esta carregat ... ");
      content = (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );
    } else if (this.state.error) {
      console.log(" Error: ]" + this.state.error + "|");
      content = (
        <div className="alert alert-danger" role="alert">
          {this.state.error}
        </div>
      );
    } else {
      console.log("Render OK: Imprimint Expedients ...!");

      var expedients = this.state.expedientresposta.expedients;

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

      const paginationInfo: PaginationCarpetaProps = {
        paginaActual: this.state.expedientresposta.paginaActual,
        elementsPerPagina: this.state.expedientresposta.elementsPerPagina,
        totalPagines: this.state.expedientresposta.totalPagines,
        registresRetornats: this.state.expedientresposta.registresRetornats,
        totalRegistres: this.state.expedientresposta.totalRegistres,
        selectElementsByPage: this.state.selectElementsPerPagina,
        onClickPagination: this.onClickCanviDePagina,
        onClickSelectElementsByPage: this.state.enableElementsPerPagina ? this.onClickElementsPerPagina : undefined,
      };

      content = (
        <RenderPaginationTable
          tableData={expedients}
          columnNames={columnsNom}
          columnTitles={columnsTitols}
          mobileIcon={"oi-clipboard"}
          paginationInfo={paginationInfo}
          i18n={i18n}
        />
      );
    }

    return (
      <>
        Numero d'Elements Total:
        <button onClick={() => this.onClickCanviTotalElements(0)}> 0</button>
        <button onClick={() => this.onClickCanviTotalElements(3)}> 3</button>
        <button onClick={() => this.onClickCanviTotalElements(10)}> 10</button>
        <button onClick={() => this.onClickCanviTotalElements(17)}> 17</button>
        <button onClick={() => this.onClickCanviTotalElements(39)}> 39</button>
        <button onClick={() => this.onClickCanviTotalElements(46)}> 46</button>
        <button onClick={() => this.onClickCanviTotalElements(59)}> 59</button>
        <button onClick={() => this.onClickCanviTotalElements(97)}> 97</button>
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
          {content}
        </TemplatePageCarpeta>
      </>
    );
  }
}

export default withTranslation()(TaulaPaginadaTester);
