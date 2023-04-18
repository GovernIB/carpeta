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
  CarpetaDatePicker,
  CarpetaInputText,
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
} from "carpetacommonreactlib";

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
  private filter_number: string;
  private filter_status: number;
  private filter_startDate: Date;
  private filter_endDate: Date;

  private errorEnFiltre: boolean;

  constructor(props: RegwebProps) {
    super(props);

    console.log("RegWeb::CONSTRUCTOR => Inici ");

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    // XYZ ZZZ
    //startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.filter_number = "";
    this.filter_status = 0;
    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;
    this.errorEnFiltre = false;

    this.state = {
      numeroRegistro: null,
    };

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
    this.tornarDeDetallRegistre = this.tornarDeDetallRegistre.bind(this);
    this.loadPaginatedData = this.loadPaginatedData.bind(this);
    this.canviatIdioma = this.canviatIdioma.bind(this);
    this.onClickRow = this.onClickRow.bind(this);
    this.validate = this.validate.bind(this);

    this.onChangeState = this.onChangeState.bind(this);
    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);
    this.onChangeNumeroRegistre = this.onChangeNumeroRegistre.bind(this);

    i18n.on("languageChanged", this.canviatIdioma);

    console.log("RegWeb::CONSTRUCTOR => Final ");
  }

  canviatIdioma(_lng: string) {
    this.forceUpdate();
  }

  componentDidMount() { }

  handleSubmitSearcher(e: any): boolean {
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

    this.setState({
      ...this.state,
      numeroRegistro: item.numeroRegistro,
    });
  }

  tornarDeDetallRegistre(_e: any) {
    console.log("RegWeb::tornarDeDetallRegistre. ");

    this.setState({
      ...this.state,
      numeroRegistro: null,
    });
  }

  onChangeStartDate(newDate: Date, _oldDate: Date): boolean {
    if (this.validate(newDate, this.filter_endDate)) {
      this.filter_startDate = newDate;
      return true;
    } else {
      return false;
    }
  }

  onChangeEndDate(newDate: Date, _oldDate: Date): boolean {
    if (this.validate(this.filter_startDate, newDate)) {
      this.filter_endDate = newDate;
      return true;
    } else {
      return false;
    }
  }

  onChangeNumeroRegistre(nouNumero: string) {
    this.filter_number = nouNumero;
  }

  onChangeState(e: any) {
    this.filter_status = e.target.value;
  }

  render() {
    console.log("RegWeb::render() => INICI");
    const t = this.props.i18n.t;

    if (this.state.numeroRegistro != null) {
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
            <CarpetaFormulariDeFiltreItem label={t("registro_numero")}>
              <CarpetaInputText
                id={"numero"}
                placeHolder={"" + t("registro_numero")}
                defaultValue={this.filter_number}
                onChangedText={this.onChangeNumeroRegistre}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("registro_fecha_inicio")}>
              <CarpetaDatePicker
                basename={"dataInici"}
                defaultValue={this.filter_startDate}
                onChangeDate={this.onChangeStartDate}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("registro_fecha_fin")}>
              <CarpetaDatePicker
                basename={"dataFi"}
                defaultValue={this.filter_endDate}
                onChangeDate={this.onChangeEndDate}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("registro_estado")}>
              <select
                id="estado"
                name="estado"
                className="form-control form-control-sm focusIn font1App form-select"
                tabIndex={504}
                aria-labelledby="estado"
                onChange={(e) => {
                  this.onChangeState(e);
                }}
                defaultValue={this.filter_status}
              >
                <option
                  value="0"
                  className="form-control form-control-sm selectMobil"                  
                >
                  {t("registro_estado_todos")}
                </option>
                <option
                  value="1"
                  className="form-control form-control-sm selectMobil"
                >
                  {t("registro_estado_1")}
                </option>
                <option
                  value="2"
                  className="form-control form-control-sm selectMobil"
                >
                  {t("registro_estado_4")}
                </option>
                <option
                  value="3"
                  className="form-control form-control-sm selectMobil"
                >
                  {t("registro_estado_10")}
                </option>
                <option
                  value="4"
                  className="form-control form-control-sm selectMobil"
                >
                  {t("registro_estado_11")}
                </option>
              </select>
            </CarpetaFormulariDeFiltreItem>
          </>
        </CarpetaFormulariDeFiltre>
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
