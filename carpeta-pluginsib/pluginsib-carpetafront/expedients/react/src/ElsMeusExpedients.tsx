/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2023-02-22 08:04:20
 */

import React, { Component } from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import "react-datepicker/dist/react-datepicker.css";
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

  private filtre_nom: string;
  private dataInici: Date;
  private dataFi: Date;
  private filter_state: string;
  private errorEnFiltre: boolean;

  constructor(props: ElsMeusExpedientsProps) {
    super(props);



    let startDateObj = new Date();

    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    this.filtre_nom = "";
    this.dataInici = startDateObj;
    this.dataFi = endDateObj;
    this.errorEnFiltre = false;
    this.filter_state = "E04";


    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    this.handleNomFilterParam = this.handleNomFilterParam.bind(this);
    this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
    this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
    this.handleStateFilterParam = this.handleStateFilterParam.bind(this);

  }

  handleSubmitSearcher(e: any) {

    console.log("RegWeb::handleSubmitSearcher() inici");

    e.preventDefault();

    if (this.errorEnFiltre) {
      const t = this.props.i18n.t;
      window.alert(t("errorEnFiltre"));
      return false;
    }

    console.log("RegWeb::handleSubmitSearcher() final");

    this.forceUpdate();

    return true;
  }

  
  validaFecha(date: Date) {
    if (!date) {
      $("#errorMsg").html("Error de data");
      $("#errorContainer").removeClass("ocult");
      return false;
    } else {
      $("#errorContainer").addClass("ocult");
      return true;
    }
  }

  handleNomFilterParam(nouNom: string) {
    this.filtre_nom = nouNom;
  }

  handleChangeDataInici(newDate: Date, _oldDate: Date) {
    if (this.validate(newDate, this.dataFi)) {
      this.dataInici = newDate;
      return true;
    } else {
      return false;
    }
  }

  handleChangeDataFi(newDate: Date, _oldDate: Date) {
    if (this.validate(this.dataInici, newDate)) {
      this.dataFi = newDate;
      return true;
    } else {
      return false;
    }
  }

  handleStateFilterParam(type: string) {
    this.filter_state = type;
  }

  validate(startDate: Date, endDate: Date) {
    console.log("RegWeb::validate() ... Entra");

    let errorInput = document.getElementById("errorContainer");

    if (startDate.getTime() > endDate.getTime()) {
      console.log("RegWeb::validate() error");

      if (errorInput) {
        errorInput.style.display = "block";
      }

      this.errorEnFiltre = true;
      return false;
    } else {
      console.log("RegWeb::validate() ok");
      if (errorInput) {
        errorInput.style.display = "none";
      }
      this.errorEnFiltre = false;
      return true;
    }
  }


  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    console.log(
      "ElsMeusExpedients:loadPaginatedData() => Page: " +
      page +
      " | elementsByPage: " +
      elementsByPage
    );
    console.log(
      "ElsMeusExpedients:loadPaginatedData() => Props: " + this.props
    );

    let url = this.props.pathtoservei;

    let params: { [key: string]: string | number } = {
      pagina: page,
      elementsperpagina: elementsByPage,
    };

    if (this.filtre_nom != null) {
      params.filtreNom = this.filtre_nom;
    }

    if (this.dataInici != null) {
      params.filtreDataInici = this.formatDate(this.dataInici);
    }

    if (this.dataFi != null) {
      params.filtreDataFi = this.formatDate(this.dataFi);
    }

    if (this.filter_state != null) {
      params.filtreEstat = this.filter_state;
    }

    console.log(
      "ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST URL: " +
      url
    );
    console.log(
      "ElsMeusExpedients:loadPaginatedData() => Cridant a serveis REST PARAMS: " +
      params
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
        if (
          JSON.stringify(error)
            .toString()
            .includes("Request failed with status code 500")
        ) {
          var errorPantalla = error.response.data.replace(
            "<html><head><title>Error</title></head><body>",
            ""
          );
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
    console.log(
      "ElsMeusExpedients:loadPaginatedData() =>  RENDER ElsMeusExpedients !!!!!"
    );

    //const { i18n } = this.props;
    const { t } = this.props;

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

    let formulari = (
      <>
        <div className="row">
          <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
            <div className="alert alert-danger" role="alert" id="errorMsg">
              {t("errorIniciMajorFinal")}
            </div>
          </div>
        </div>
        <CarpetaFormulariDeFiltre handleSubmitSearcher={this.handleSubmitSearcher} i18n={this.props.i18n}>
          <>
            <CarpetaFormulariDeFiltreItem label={t("expedient_nom")}>
              <CarpetaInputText
                id={"nom"}
                placeHolder={"" + t("nomProcediment")}
                defaultValue={this.filtre_nom}
                onChangedText={this.handleNomFilterParam}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("expedient_fecha_inicio")}>
              <CarpetaDatePicker
                basename={"dataInici"}
                onChangeDate={this.handleChangeDataInici}
                defaultValue={this.dataInici}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("expedient_fecha_fin")}>
              <CarpetaDatePicker
                basename={"dataFi"}
                onChangeDate={this.handleChangeDataFi}
                defaultValue={this.dataFi}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("expedient_estat")}>
              <select
                id="estado"
                name="estado"
                className="form-control form-control-sm focusIn font1App form-select"
                tabIndex={504}
                aria-labelledby="tipo"
                onChange={(e) => {
                  this.handleStateFilterParam(e.target.value);
                }}
              >
                <option
                  value="E04"
                  className="form-control form-control-sm selectMobil"
                  selected={this.filter_state == "E04"}
                >
                  {t("expediente_estado_todos")}
                </option>
                <option
                  value="E01"
                  className="form-control form-control-sm selectMobil"
                  selected={this.filter_state == "E01"}
                >
                  {t("expediente_estado_1")}
                </option>
                <option
                  value="E02"
                  className="form-control form-control-sm selectMobil"
                  selected={this.filter_state == "E02"}
                >
                  {t("expediente_estado_2")}
                </option>
                <option
                  value="E03"
                  className="form-control form-control-sm selectMobil"
                  selected={this.filter_state == "E03"}
                >
                  {t("expediente_estado_3")}
                </option>
              </select>
            </CarpetaFormulariDeFiltreItem>
          </>
        </CarpetaFormulariDeFiltre>
      </>
    );

    return (
      <TemplatePageCarpeta
        {...this.props}
        titles={this.props.titles}
        subtitles={this.props.subtitles}
        i18n={i18n}
      >
        <div>
          {formulari}
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

  nomEstat(estat: number) {
    const t = this.props.i18n.t;
    switch (estat.toString()) {
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

    date = date
      .toString()
      .padStart(2, '0');

    month = month
      .toString()
      .padStart(2, '0');

    return `${date}/${month}/${year}`;
  }

}

export default withTranslation()(ElsMeusExpedients);
