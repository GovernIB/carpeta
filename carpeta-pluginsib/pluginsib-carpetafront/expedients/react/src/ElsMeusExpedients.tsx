/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2022-11-16 10:30:40
 * @desc [description]
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import { TemplatePageCarpeta, PaginationCarpetaProps, RenderPaginationTable } from "carpetacommonreactlib";

interface ElsMeusExpedientsProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

type ElsMeusExpedientsState = {
  isLoaded: boolean;
  pagina: number;
  numExpedients: number;
  expedientresposta: any;
  error: string | null;
};

class ElsMeusExpedients extends React.Component<ElsMeusExpedientsProps, ElsMeusExpedientsState> {
  constructor(props: ElsMeusExpedientsProps) {
    super(props);
    console.log("  CONSTRUCTOR ElsMeusExpedients !!!!!");
    this.state = {
      isLoaded: false,
      expedientresposta: null,
      error: null,
      pagina: 0,
      numExpedients: 5,
    };
    this.onClickPagination = this.onClickPagination.bind(this);
    this.onClickElementsByPage = this.onClickElementsByPage.bind(this);
    this.realoadData = this.realoadData.bind(this);
  }

  componentDidMount() {
    this.realoadData(0, this.state.numExpedients);
  }

  realoadData(newpage: number, numExpedientsPag: number) {
    const url = this.props.pathtoservei;
    
    this.setState({ pagina: newpage, isLoaded: false, numExpedients: numExpedientsPag});
    var params = {
      pagina: newpage,
      numExpedients: numExpedientsPag,
    };
    

    console.log("Cridant a serveis REST URL: " + url);
    console.log("Cridant a serveis REST PARAMS: " + params);

    axios
      .get(url, { params: params })
      .then((response) => {
        if (response.data != null) {
          // response.data conté un objecte de tipus ExpedientResposta.java
          this.setState({
            ...this.state,
            expedientresposta: response.data,
            error: null,
            isLoaded: true,
          });
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
          this.setState({
            error: errorPantalla,
            isLoaded: true,
          });
        } else {
          this.setState({
            error: JSON.stringify(error),
            isLoaded: true,
          });
        }
      });
  }

  onClickPagination(page: number) {
    console.log("Clicat sobre paginacio ]" + page + "[");

    this.realoadData(page, this.state.numExpedients);
  }

  onClickElementsByPage(numElements: number) {
    console.log("Clicat sobre num de elements per pagina ]" + numElements + "[");

    this.realoadData(0, numElements);
  }

  render() {
    console.log("  RENDER ElsMeusExpedients !!!!!");

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
      /**
        ExpedientResposta {

            protected int paginaActual;
            protected int elementsPerPagina;
        
            protected int totalPagines;
            protected int registresRetornats;
            protected int totalRegistres;
        
            protected List<ExpedientInfo> expedients;
        }
      */

      console.log("Render OK: Imprimint Expedients ...!");

      var expedients = this.state.expedientresposta.expedients;

      const columnsNom = [
        "expedientObertura",
        "expedientNom",
        "nomProcediment",
        "codiSia",
        "expedientOrgans",
        "expedientEstat"
      ];

      const columnsTitols = [
        i18n.t("expedientObertura"),
        i18n.t("expedientNom"),
        i18n.t("nomProcediment"),
        i18n.t("codiSia"),
        i18n.t("expedientOrgans"),
        i18n.t("expedientEstat")
      ];

      const paginationInfo: PaginationCarpetaProps = {
        paginaActual: this.state.expedientresposta.paginaActual,
        elementsPerPagina: this.state.expedientresposta.elementsPerPagina,
        totalPagines: this.state.expedientresposta.totalPagines,
        registresRetornats: this.state.expedientresposta.registresRetornats,
        totalRegistres: this.state.expedientresposta.totalRegistres,
        onClickPagination: this.onClickPagination,
        onClickSelectElementsByPage: this.onClickElementsByPage
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
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        {content}
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(ElsMeusExpedients);
