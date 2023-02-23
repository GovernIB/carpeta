/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2023-02-22 08:04:20
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import {
  TemplatePageCarpeta,
  RenderPaginationTable,
  RenderPaginationTableData,
  PaginationInfo,
  ReturnPaginationData,
} from "carpetacommonreactlib";

interface ElsMeusExpedientsProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

class ElsMeusExpedients extends React.Component<ElsMeusExpedientsProps> {
  constructor(props: ElsMeusExpedientsProps) {
    super(props);
    this.loadPaginatedData = this.loadPaginatedData.bind(this);
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    console.log("ElsMeusExpedients:loadPaginatedData() => Page: " + page + " | elementsByPage: " + elementsByPage);

    let url = this.props.pathtoservei;

    let params = {
      pagina: page,
      elementsperpagina: elementsByPage,
    };

    console.log("ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST URL: " + url);
    console.log("ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST PARAMS: " + params);

    axios
      .get(url, { params: params })
      .then((response) => {
        if (response.data != null) {
          let expedientresposta: any = response.data;

          let paginationInfo: PaginationInfo = {
            paginaActual: expedientresposta.paginaActual,
            elementsPerPagina: expedientresposta.elementsPerPagina,
            totalPagines: expedientresposta.totalPagines,
            elementsRetornats: expedientresposta.registresRetornats,
            totalElements: expedientresposta.totalRegistres,
          };

          let data: RenderPaginationTableData = {
            paginationInfo: paginationInfo,
            tableData: expedientresposta.expedients,
            error: null,
          };
          loadData.returnDataFunction(data);
        }
      })
      .catch((error) => {
        console.log("Error cridant al servei REST: \n" + JSON.stringify(error));
        if (error.response) {
          console.log("error.response.data: " + error.response.data);
          console.log("error.response.status: " + error.response.status);
          console.log("error.response.headers: " + error.response.headers);
        }
        if (JSON.stringify(error).toString().includes("Request failed with status code 500")) {
          var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", "");
          errorPantalla = errorPantalla.replace("</body></html>", "");
        } else {
          errorPantalla = JSON.stringify(error);
        }

        let data: RenderPaginationTableData = {
          paginationInfo: null,
          tableData: null,
          error: errorPantalla,
        };

        loadData.returnDataFunction(data);
      });
  }

  render() {
    console.log("ElsMeusExpedients:loadPaginatedData() =>  RENDER ElsMeusExpedients !!!!!");

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

    return (
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        <RenderPaginationTable
          loadPaginatedData={this.loadPaginatedData}
          columnNames={columnsNom}
          columnTitles={columnsTitols}
          mobileIcon={"oi-clipboard"}
          i18n={i18n}
        />
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(ElsMeusExpedients);