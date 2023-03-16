import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";

import { DetallRegistre } from "regwebdetallcomponentlib";
import {
  PaginationInfo,
  RenderPaginationTable,
  RenderPaginationTableData,
  ReturnPaginationData,
  TemplatePageCarpeta,
} from "carpetacommonreactlib";

import CarpetaDatePicker from "./CarpetaDatePicker";

/**
 *  @author jagarcia
 *  @author anadal
 */

interface RegwebProps extends WithTranslation {
  titles: any;
  subtitles: any;
  pathtoservei: string;
  detallpathtoservei: string;
  numeroregistro: string | null;
}

interface RegwebState {
  numeroRegistro: string | null;
}

class Regweb extends React.Component<RegwebProps, RegwebState> {
  private filter_number: string | null;
  private filter_status: number;
  private filter_startDate: Date;
  private filter_endDate: Date;

  constructor(props: RegwebProps) {
    super(props);

    console.log("RegWeb::CONSTRUCTOR => Inici ");

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);
    // XYZ ZZZ
    startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.filter_number = null;
    this.filter_status = 0;

    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;

    this.state = {
      numeroRegistro: null,
    };

    this.handleStateFilterParam = this.handleStateFilterParam.bind(this);

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

    this.tornarDeDetallRegistre = this.tornarDeDetallRegistre.bind(this);

    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    this.nomEstat = this.nomEstat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);

    this.onClickRow = this.onClickRow.bind(this);

    // XYZ ZZZ Canviar-ho per un handler de keyup a numero
    this.guardarDadesFormulari = this.guardarDadesFormulari.bind(this);

    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);

    i18n.on("languageChanged", this.canviatIdioma);

    console.log("RegWeb::CONSTRUCTOR => Final ");
  }

  canviatIdioma(lng: string) {
    //const getLocale = (Locale: any) => require(`date-fns/locale/${lng}/index.js`);
    //this.locale = getLocale(lng);
  }

  handleStateFilterParam(e: any) {
    this.filter_status = e.target.value;
  }

  guardarDadesFormulari() {
    let tmp: any = document.getElementById("numero");

    if (tmp != null) {
      this.filter_number = tmp.value;
    } else {
      this.filter_number = null;
    }
  }

  handleSubmitSearcher(e: any) {
    console.log("RegWeb::handleSubmitSearcher() ...");

    const { t } = this.props;

    let validatFormulari = this.validaFormulari();

    e.preventDefault();
    if (validatFormulari) {
      //   let numeroInput:HTMLElement | null = document.getElementById("numero");
      //numeroInput==null?null: numeroInput.value,
      /*
      if (this.inputNumeroRef.current == null) {
        this.setState({
          ...this.state,
          filter_number: null
        });
      } else {
        this.setState({
          ...this.state,
          filter_number: this.inputNumeroRef.current!.value
        });
      }
      */
      this.forceUpdate();
    }
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    //console.log("regWeb::loadPaginatedData() => page: " + page);
    //console.log("regWeb::loadPaginatedData() => elementsByPage: " + elementsByPage);

    let tmp: any = document.getElementById("numero");

    let filter_number = "";
    if (tmp != null) {
      filter_number = tmp.value;
    }

    const params = {
      numero: filter_number,
      fechaInicio: this.filter_startDate,
      fechaFin: this.filter_endDate,
      estado: this.filter_status,
      registrosPorPagina: elementsByPage,
      pageNumber: page,
    };

    console.log("RegWeb::loadPaginatedData() => Parametres: " + params);

    const url3 = this.props.pathtoservei;

    axios
      .get(url3, { params: params })
      .then((response: any) => {
        /*
        // response.data.registres
        public class Registre {
          private String numeroRegistro;
          private Date fechaRegistro;
          private String extracto;
          private Long estado;
          private String denominacionDestino;
        }

        // response.data.paginacio
        public class Paginacio {

          private int paginaActual;
          private int elementsPerPagina;
          private int totalPagines;
          private int elementsRetornats;
          private int totalElements;
        }

        */
        if (response.data.registres != null && response.data.paginacio != null) {
          //console.log("regWeb::loadPaginatedData() => OK registres? => " + response.data.registres);
          //console.log("regWeb::loadPaginatedData() => OK paginacio? => " + response.data.paginacio);

          let paginationInfo: PaginationInfo = {
            paginaActual: response.data.paginacio.paginaActual,
            elementsPerPagina: response.data.paginacio.elementsPerPagina,
            totalPagines: response.data.paginacio.totalPagines,
            elementsRetornats: response.data.paginacio.elementsRetornats,
            totalElements: response.data.paginacio.totalElements,
          };

          let data: RenderPaginationTableData = {
            paginationInfo: paginationInfo,
            tableData: response.data.registres,
            error: null,
          };
          loadData.returnDataFunction(data);
        } else {
          console.log("regWeb::loadPaginatedData() => Error buit registres? => " + response.data.registres);
          console.log("regWeb::loadPaginatedData() => Error buit paginacio? => " + response.data.paginacio);

          let errorPantalla = "Registres o Paginació és buit .";
          let data: RenderPaginationTableData = {
            paginationInfo: null,
            tableData: null,
            error: errorPantalla,
          };
          loadData.returnDataFunction(data);
        }
      })
      .catch((error) => {
        console.log(JSON.stringify(error));
        if (error.response) {
          console.log("error.response.data: " + error.response.data);
          console.log("error.response.status: " + error.response.status);
          console.log("error.response.headers: " + error.response.headers);
        }
        let errorPantalla;
        if (JSON.stringify(error).toString().includes("Request failed with status code 500")) {
          errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", "");
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

  onClickRow(pos: number, item: any): void {
    console.log("RegWeb::onClickRow(" + pos + "," + item + ") => Numero Registre " + item.numeroRegistro);

    this.guardarDadesFormulari();

    this.setState({
      ...this.state,
      numeroRegistro: item.numeroRegistro,
    });
  }

  validaFormulari() {
    /* XYZ ZZZ
    if (!this.validaFecha(this.state.filter_startDate) || !this.validaFecha(this.state.filter_endDate)) {
      return false;
    }

    if (this.state.filter_startDate.getMilliseconds() > this.state.filter_endDate.getMilliseconds()) {
      $("#errorMsg").html(t("sistraDataIniciError"));
      $("#errorContainer").removeClass("ocult");
      return false;
    }

    return true;
    */
    return true;
  }

  validaFecha(date: string): Date | null {
    if (Date.parse(date)) {
      //$("#errorMsg").html("Error de data");
      let errorMsg = document.getElementById("errorMsg");
      if (errorMsg != null) errorMsg.innerHTML = "Error de data";
      // XYZ ZZZ
      //$("#errorContainer").removeClass("ocult");
      return null;
    } else {
      // XYZ ZZZ
      //$("#errorContainer").addClass("ocult");
      return new Date(Date.parse(date));
    }
  }

  tornarDeDetallRegistre(e: any) {
    console.log("RegWeb::tornarDeDetallRegistre. ");

    this.setState({
      ...this.state,
      numeroRegistro: null,
    });
  }

  dateFormat(dateObject: any) {
    var d = new Date(dateObject);

    let composed = "";

    composed = composed + (d.getDate() < 10 ? "0" : "") + d.getDate() + "-";

    composed = composed + (d.getMonth() < 9 ? "0" : "") + (d.getMonth() + 1) + "-";

    composed = composed + d.getFullYear().toString() + " ";

    composed = composed + (d.getHours() < 10 ? "0" : "") + d.getHours() + ":";

    composed = composed + (d.getMinutes() < 10 ? "0" : "") + d.getMinutes();

    return composed;
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

  onChangeStartDate(newDate: Date, _oldDate: Date) {
    this.filter_startDate = newDate;
  }

  onChangeEndDate(newDate: Date, _oldDate: Date) {
    this.filter_endDate = newDate;
  }

  componentDidMount() {}

  render() {
    const t = this.props.i18n.t;

    if (this.state.numeroRegistro != null) {
      console.log("Regweb:: detallpathtoservei= " + this.props.detallpathtoservei);
      console.log("Regweb:: numeroRegistro= " + this.state.numeroRegistro);

      let numReg: string = this.state.numeroRegistro;

      return (
        <DetallRegistre
          pathtoservei={this.props.detallpathtoservei}
          numero={numReg}
          t={t}
          axios={axios}
          tornarDeDetallRegistreFunc={this.tornarDeDetallRegistre}
        />
      );
    }

    let formulari = <></>;
    formulari = (
      <>
        <form id="fechaBusqueda" style={{ marginBottom: "20px" }}>
          <div style={{ width: "95%", paddingLeft: "0px", margin: "0px" }} className="ampleTotalApp container">
            <div className="row">
              <div className="col-xs-12 mb-3 campFormApp col">
                <div>
                  <label className="form-label">{t("registro_numero")}</label>
                  <input
                    placeholder={"" + t("registro_numero")}
                    /* ref={this.inputNumeroRef} */ maxLength={25}
                    tabIndex={501}
                    type="text"
                    id="numero"
                    readOnly={false}
                    className="form-control form-control-sm focusIn font1App"
                    defaultValue={this.filter_number == null ? "" : this.filter_number}
                  />
                </div>
              </div>
              <div className="col-xs-12 mb-3 campFormApp col">
                <div>
                  <label className="form-label">{t("registro_fecha_inicio")}</label>

                  <CarpetaDatePicker
                    name={"dataInici"}
                    defaultValue={this.filter_startDate}
                    onChangeDate={this.onChangeStartDate}
                  />
                </div>
              </div>
              <div className="col-xs-12 mb-3 campFormApp col">
                <div>
                  <label className="form-label">{t("registro_fecha_fin")}</label>

                  <CarpetaDatePicker
                    name={"dataFi"}
                    defaultValue={this.filter_endDate}
                    onChangeDate={this.onChangeEndDate}
                  />
                </div>
              </div>
              <div className="col-xs-12 mb-3 campFormApp col">
                <div>
                  <label className="form-label">{t("registro_estado")}</label>

                  <select
                    id="estado"
                    name="estado"
                    className="form-control form-control-sm focusIn font1App form-select"
                    tabIndex={504}
                    aria-labelledby="estado"
                    onChange={(e) => {
                      this.handleStateFilterParam(e);
                    }}
                  >
                    <option
                      value="0"
                      className="form-control form-control-sm selectMobil"
                      selected={this.filter_status == 0}
                    >
                      {t("registro_estado_todos")}
                    </option>
                    <option
                      value="1"
                      className="form-control form-control-sm selectMobil"
                      selected={this.filter_status == 1}
                    >
                      {t("registro_estado_1")}
                    </option>
                    <option
                      value="2"
                      className="form-control form-control-sm selectMobil"
                      selected={this.filter_status == 2}
                    >
                      {t("registro_estado_4")}
                    </option>
                    <option
                      value="3"
                      className="form-control form-control-sm selectMobil"
                      selected={this.filter_status == 3}
                    >
                      {t("registro_estado_10")}
                    </option>
                    <option
                      value="4"
                      className="form-control form-control-sm selectMobil"
                      selected={this.filter_status == 4}
                    >
                      {t("registro_estado_11")}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div className="row">
              <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                <div className="alert alert-danger" role="alert" id="errorMsg" />
              </div>
            </div>
            <div className="col-md-3 pl-3 row botoFormApp" style={{ zIndex: "4" }}>
              <button
                type="submit"
                className="btn btn-primary carpeta-btn mt-2"
                onClick={(e) => {
                  this.handleSubmitSearcher(e);
                }}
                tabIndex={505}
              >
                {t("carpeta_buscar")}
              </button>
            </div>
          </div>
        </form>
      </>
    );

    {
      /*
      criteris = (
        <div id="idCriteris">
          <div
            style={{ float: "left", marginTop: "9px;", paddingBottom: "0.7em" }}
            className="col-md-10 pr-3 visioMobil"
          >
            <span className="oi oi-calendar iconaFormApp" title={t("registroDates")} style={{ verticalAlign: "sub" }} />
            <span className="pl-3">
              {this.dateFormatCerca(this.state.filter_startDate) +
                t("carpeta_criterio_6") +
                $.dateFormatCerca(this.state.filter_endDate)}
            </span>
          </div>
          <div
            style={{
              float: "rigth",
              marginTop: "9px;",
              paddingBottom: "0.7em",
              textAlign: "end",
            }}
            onClick={() => {
              this.mostrarForm();
            }}
            className="col-md-1 visioMobil"
          >
            <span className="oi oi-magnifying-glass iconaFormApp" title={t("registroCerca")} />
          </div>
        </div>
      );
*/
    }

    //

    const columnsNom = ["numeroRegistro", "fechaRegistro", "extracto", "estado", "denominacionDestino"];

    const columnsTitols = [
      i18n.t("registro_numero"),
      i18n.t("registro_fecha"),
      i18n.t("registro_extracto"),
      i18n.t("registro_estado"),
      i18n.t("registro_destinatario"),
    ];

    return (
      <TemplatePageCarpeta titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        <>
          {formulari}
          <RenderPaginationTable
            loadPaginatedData={this.loadPaginatedData}
            columnNames={columnsNom}
            columnTitles={columnsTitols}
            mobileIcon={"oi-i-book"}
            i18n={i18n}
            onClickRow={this.onClickRow}
          />
        </>
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(Regweb);
