/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2023-02-22 08:04:20
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";

import {
  TemplatePageCarpeta,
  RenderPaginationTable,
  RenderPaginationTableData,
  PaginationInfo,
  ReturnPaginationData,
  CarpetaDatePicker,
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
  CarpetaInputText,
} from "carpetacommonreactlib";

interface ElsMeusExpedientsProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

class ElsMeusExpedients extends React.Component<ElsMeusExpedientsProps> {
  private filter_startDate: Date;
  private filter_endDate: Date;
  private filter_nom: string;
  private filter_estat: string;
  private filter_codiSia: string;

  private errorEnFiltre: boolean;

  constructor(props: ElsMeusExpedientsProps) {
    super(props);

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    // debug
    //startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;
    this.filter_nom = "";
    this.filter_codiSia = "";
    this.filter_estat = "";

    this.errorEnFiltre = false;

    this.formatDate = this.formatDate.bind(this);
    this.nomEstat = this.nomEstat.bind(this);

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
    this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
    this.handleStateFilterParam = this.handleStateFilterParam.bind(this);
    this.handleCodiSiaFilterParam = this.handleCodiSiaFilterParam.bind(this);
    this.handleNomFilterParam = this.handleNomFilterParam.bind(this);

    this.validate = this.validate.bind(this);
  }

  handleSubmitSearcher(e: any): boolean {
    console.log("ElsMeusExpedients::handleSubmitSearcher() inici");

    e.preventDefault();

    if (this.errorEnFiltre) {
      const t = this.props.i18n.t;
      window.alert(t("errorEnFiltre"));
      return false;
    }

    console.log("ElsMeusExpedients::handleSubmitSearcher() final");

    this.forceUpdate();

    return true;
  }

  validate(startDate: Date, endDate: Date) {
    console.log("ElsMeusExpedients::validate() ... Entra");

    let errorInput = document.getElementById("errorContainer");

    if (startDate.getTime() > endDate.getTime()) {
      console.log("ElsMeusExpedients::validate() error");

      if (errorInput) {
        errorInput.style.display = "block";
      }

      this.errorEnFiltre = true;
      return false;
    } else {
      console.log("ElsMeusExpedients::validate() ok");
      if (errorInput) {
        errorInput.style.display = "none";
      }
      this.errorEnFiltre = false;
      return true;
    }
  }

  handleNomFilterParam(newText: string) {
    this.filter_nom = newText;
  }

  handleCodiSiaFilterParam(newText: string) {
    this.filter_codiSia = newText;
  }

  handleStartDateFilterParam(newDate: Date, _oldDate: Date) {
    if (this.validate(newDate, this.filter_endDate)) {
      this.filter_startDate = newDate;
      return true;
    } else {
      return false;
    }
  }

  handleEndDateFilterParam(newDate: Date, _oldDate: Date) {
    if (this.validate(this.filter_startDate, newDate)) {
      this.filter_endDate = newDate;
      return true;
    } else {
      return false;
    }
  }

  handleStateFilterParam(e: string) {
    this.filter_estat = e;
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    console.log("ElsMeusExpedients:loadPaginatedData() => Page: " + page + " | elementsByPage: " + elementsByPage);
    console.log("ElsMeusExpedients:loadPaginatedData() => Props: " + this.props);

    let url = this.props.pathtoservei;

    const params = {
      pagina: page,
      elementsperpagina: elementsByPage,
      filtreNom: this.filter_nom,
      filtreCodiSia: this.filter_codiSia,
      filtreDataInici: this.formatDate(this.filter_startDate),
      filtreDataFi: this.formatDate(this.filter_endDate),
      filtreEstat: this.filter_estat,
    };

    console.log("ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST URL: " + url);
    console.log("ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST PARAMS: " + params);

    console.log(
      "ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST PARAM[filterCodiSia]: ]" +
        params.filtreCodiSia +
        "["
    );
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

  nomEstat(estat: number) {
    const t = this.props.i18n.t;
    switch (estat.toString()) {
      default:
      case "0":
        return t("registro_estado_todos");
      case "1":
        return t("registro_estado_1");
      case "2":
        return t("registro_estado_4");
      case "3":
        return t("registro_estado_10");
      case "4":
        return t("registro_estado_11");
    }
  }

  formatDate(inputDate: Date) {
    let date, month, year;

    date = inputDate.getDate();
    month = inputDate.getMonth() + 1;
    year = inputDate.getFullYear();

    date = date.toString().padStart(2, "0");

    month = month.toString().padStart(2, "0");

    return `${date}/${month}/${year}`;
  }

  render() {
    console.log("ElsMeusExpedients:loadPaginatedData() =>  RENDER ElsMeusExpedients !!!!!");

    const t = this.props.i18n.t;

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
        <div>
          <div className="row">
            <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
              <div className="alert alert-danger" role="alert" id="errorMsg">
                {t("errorIniciMajorFinal")}
              </div>
            </div>
          </div>

          <CarpetaFormulariDeFiltre handleSubmitSearcher={this.handleSubmitSearcher} i18n={this.props.i18n}>
            <>
              <CarpetaFormulariDeFiltreItem label={t("expedientNom")}>
                {/*}
                  maxLength={25}
                  tabIndex={501}
    */}
                <CarpetaInputText
                  id="nom"
                  placeHolder={t("expedientNom")}
                  onChangedText={this.handleNomFilterParam}
                  defaultValue={this.filter_nom}
                />
              </CarpetaFormulariDeFiltreItem>

              <CarpetaFormulariDeFiltreItem label={t("expedient_codiSia")}>
                {/*}
                  maxLength={25}
                  tabIndex={501}
    */}
                <CarpetaInputText
                  id="codiSIA"
                  placeHolder={t("codiSia")}
                  onChangedText={this.handleCodiSiaFilterParam}
                  defaultValue={this.filter_codiSia}
                />
              </CarpetaFormulariDeFiltreItem>

              <CarpetaFormulariDeFiltreItem label={t("expedient_fecha_inicio")}>
                <CarpetaDatePicker
                  basename={"dataInici"}
                  defaultValue={this.filter_startDate}
                  onChangeDate={this.handleStartDateFilterParam}
                  i18n={this.props.i18n}
                />
              </CarpetaFormulariDeFiltreItem>

              <CarpetaFormulariDeFiltreItem label={t("expedient_fecha_fin")}>
                <CarpetaDatePicker
                  basename={"dataFi"}
                  defaultValue={this.filter_endDate}
                  onChangeDate={this.handleEndDateFilterParam}
                  i18n={this.props.i18n}
                />
              </CarpetaFormulariDeFiltreItem>

              <CarpetaFormulariDeFiltreItem label={t("expedient_estat")}>
                <select
                  id="estado"
                  name="estado"
                  className="form-control form-control-sm focusIn font1App form-select"
                  defaultValue={this.filter_estat}
                  tabIndex={504}
                  aria-labelledby="estado"
                  onChange={(e) => {
                    this.handleStateFilterParam(e.target.value);
                  }}
                >
                  <option value="" className="form-control form-control-sm selectMobil">
                    {t("expediente_estado_todos")}
                  </option>
                  <option value="E01" className="form-control form-control-sm selectMobil">
                    {t("expediente_estado_1")}
                  </option>
                  <option value="E02" className="form-control form-control-sm selectMobil">
                    {t("expediente_estado_2")}
                  </option>
                  <option value="E03" className="form-control form-control-sm selectMobil">
                    {t("expediente_estado_3")}
                  </option>
                </select>
              </CarpetaFormulariDeFiltreItem>
            </>
          </CarpetaFormulariDeFiltre>

          <RenderPaginationTable
            loadPaginatedData={this.loadPaginatedData}
            columnNames={columnsNom}
            columnTitles={columnsTitols}
            mobileIcon={"oi-clipboard"}
            i18n={i18n}
          />
        </div>
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(ElsMeusExpedients);
