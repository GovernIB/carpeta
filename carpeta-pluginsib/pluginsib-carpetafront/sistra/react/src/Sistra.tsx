/**
 * @author anadal
 * @email anadal@fundaciobit.org
 * @create date 2023-04-05 07:47:53
 * @modify date 2023-04-05 07:47:53
 * @desc [description]
 */

import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "./i18n";

import { DetallRegistre } from "regwebdetallcomponentlib";
import {
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
  RenderTable,
  RenderTableData,
  RowType,
  TemplatePageCarpeta,
} from "carpetacommonreactlib";

interface SistraProps extends WithTranslation {
  titles: any;
  subtitles: any;
  pathtoservei: string;
  detallpathtoservei: string;
  mesosFiltre: string;
}

interface SistraState {
  numeroRegistro: string | null;
}

interface Tramit {
  index: number;
  descripcionTramite: string;
  fechaInicio: string;
  pendiente: string;
  numero: string;
  url: string;
  versionSistra: string;
  mostraModal: boolean;
  tipo: string;
  estat: string;
}

class Sistra extends React.Component<SistraProps, SistraState> {
  private childRenderTable: React.RefObject<RenderTable> = React.createRef();

  private filter_status: string;
  private filter_startDate: Date;
  private filter_endDate: Date;

  private errorEnFiltre: boolean;

  private columnsNom: string[];

  constructor(props: SistraProps) {
    super(props);

    console.log("SISTRA:: constructor ...");

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    // XYZ ZZZ
    //startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.columnsNom = ["index", "descripcionTramite", "fechaInicio", "estat"];

    this.filter_status = "A";
    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;
    this.errorEnFiltre = false;

    this.state = {
      numeroRegistro: null,
    };

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

    this.onChangeState = this.onChangeState.bind(this);
    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);


    this.formatDate = this.formatDate.bind(this);

    this.processarTramits = this.processarTramits.bind(this);

    this.carregaDadesSistra = this.carregaDadesSistra.bind(this);

    this.tornarDeDetallRegistre = this.tornarDeDetallRegistre.bind(this);

    this.onClickRow = this.onClickRow.bind(this);

    this.estatTramit = this.estatTramit.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    i18n.on("languageChanged", this.canviatIdioma);
  }

  componentDidMount() {
    this.carregaDadesSistra();
    if (this.childRenderTable.current != null) this.childRenderTable.current.updateTableData(null);
  }

  /**  dd-MM-yyyy */
  formatDate(today: Date): String {
    let mm = today.getMonth() + 1; // Months start at 0!
    let dd = today.getDate();
    return ((dd < 10) ? "0" + dd : "" + dd) + (mm < 10 ? "-0" + mm : "-" + mm) + "-" + today.getFullYear();
  }

  onClickRow(pos: number, item: any): void {
    console.log("Ssitra::onClickRow(" + pos + "," + item + ")  ");

    let tramit: Tramit = item as Tramit;

    if (tramit.numero !== "") {
      //this.handleItemClick(tramit.numero);
      this.setState({
        numeroRegistro: tramit.numero,
      });
    } else if (tramit.numero === "" && tramit.pendiente && tramit.mostraModal) {
      this.openModalConfirm(tramit.url);
    } else if (tramit.numero === "" && (!tramit.pendiente || !tramit.mostraModal)) {
      window.open(tramit.url, "_blank");
    }
  }

  canviatIdioma(_lng: string) {
    this.forceUpdate();
  }

  handleSubmitSearcher(e: any): boolean {
    console.log("Sistra::handleSubmitSearcher() inici");

    e.preventDefault();

    if (this.errorEnFiltre) {
      const t = this.props.i18n.t;
      window.alert(t("errorEnFiltre"));
      return false;
    }

    this.carregaDadesSistra();
    if (this.childRenderTable.current != null) this.childRenderTable.current.updateTableData(null);

    console.log("Sistra::handleSubmitSearcher() final");

    return true;
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

  onChangeState(e: any) {
    this.filter_status = e.target.value;
  }

  validate(startDate: Date, endDate: Date) {
    console.log("Sistra::validate() ... Entra");

    let errorInput = document.getElementById("errorContainer");

    if (startDate.getTime() > endDate.getTime()) {
      console.log("Sistra::validate() error");

      if (errorInput) {
        errorInput.style.display = "block";
      }

      this.errorEnFiltre = true;
      return false;
    } else {
      console.log("Sistra::validate() ok");
      if (errorInput) {
        errorInput.style.display = "none";
      }
      this.errorEnFiltre = false;
      return true;
    }
  }

  processarTramits(data: any[]): Tramit[] {
    let tramits: Tramit[] = [];

    data.map(({ descripcionTramite, fechaInicio, pendiente, numero, url, versionSistra, mostraModal, tipo }, i) => {
      {
        let nouTramit: Tramit = {
          index: i + 1,
          descripcionTramite: descripcionTramite,
          //fechaInicio:	this.dateFormat(fechaInicio),
          fechaInicio: fechaInicio,
          pendiente: pendiente,
          numero: numero,
          url: url,
          versionSistra: versionSistra,
          mostraModal: mostraModal,
          tipo: tipo,
          estat: this.estatTramit(pendiente, mostraModal, tipo, numero),
        };
        tramits.push(nouTramit);
      }
    });

    return tramits;
  }

  async carregaDadesSistra() {
    console.log("Sistra::carregaDadesSistra() => Inici ...");

    const params = {
      dataInici: this.formatDate(this.filter_startDate),
      dataFi: this.formatDate(this.filter_endDate),
      estat: this.filter_status,
    };

    console.log("Sistra::carregaDadesSistra() => dataInici: " + this.filter_startDate);
    console.log("Sistra::carregaDadesSistra() => dataFi: " + this.filter_endDate);

    const url3 = this.props.pathtoservei;

    axios
      .get(url3, { params: params })
      .then((response) => {
        if (response.data == null) {
          console.log("Sistra::carregaDadesSistra() => Error buit tramits? => " + response.data.tramits);
          let errorPantalla = "Tràmits és null .";
          let data: RenderTableData = {
            tableData: null,
            error: errorPantalla,
          };
          if (this.childRenderTable.current != null) this.childRenderTable.current.updateTableData(data);
        } else {
          let data: RenderTableData = {
            tableData: this.processarTramits(response.data.tramits),
            error: null,
          };
          if (this.childRenderTable.current != null) this.childRenderTable.current.updateTableData(data);
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
        let data: RenderTableData = {
          tableData: null,
          error: errorPantalla,
        };
        if (this.childRenderTable.current != null) this.childRenderTable.current.updateTableData(data);
      });
  }

  obrirTramit(url: string) {
    var win = window.open(url, "_blank");
    if (win) {
      win.focus();
    } else {
      alert("Permeteu finestres emergents per a aquest lloc web");
    }
  }
  tornarDeDetallRegistre(_e: any) {
    this.setState({
      numeroRegistro: null,
    });
  }

  openModalConfirm(url: string) {
    const { t } = this.props;
    // @ts-ignore
    $("body").append(
      '<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
        '<div class="modal-dialog" role="document">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<h3 id="myModalLabel">' +
        t("sistraModalTitol") +
        "</h3>" +
        '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="font-weight: normal;font-size: 20px;" tabindex="590">x</button>' +
        "</div>" +
        '<div class="modal-body">' +
        "<p>" +
        t("sistraModalTexte") +
        "</p>" +
        "</div>" +
        '<div class="modal-footer">' +
        '<button class="btn btn-success" type="button" onclick="window.open(\'' +
        url +
        "', '_blank');$('#myModal').modal('hide');\" tabindex=\"511\">" +
        t("sistraModalBotoContinuar") +
        "</button>" +
        '<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true" tabindex="512">' +
        t("sistraModalBotoCancelar") +
        "</button>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>"
    );

    // @ts-ignore
    $("#myModal").modal("show");
  }

  estatTramit(pendiente: string, mostraModal: boolean, tipo: string, numero: string) {
    if (pendiente) {
      if (mostraModal) {
        return this.props.i18n.t("sistraNoFinalizatPresencial"); //Pendent compareixença
      } else {
        return this.props.i18n.t("sistraNoFinalitzat"); // Inacabat
      }
    } else {
      if (tipo === "REGISTRO" || numero !== "") {
        return this.props.i18n.t("sistraFinalitzat"); // Registrat
      } else {
        return this.props.i18n.t("sistraFinalitzat"); // Finalitzat
      }
    }
  }

  render() {
    console.log("SISTRA:: constructor ...");

    const t = this.props.i18n.t;
    let formulari;

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
    
    let sistraEstat: string | null = t("sistraEstat");
    let sistraMesosCercaLiteral: string | null = t("sistraMesosCercaLiteral",{mesos: this.props.mesosFiltre} );

    formulari = (
      <>
        <div className="row">
          <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
            <div className="alert alert-danger" role="alert" id="errorMsg">
              {t("errorIniciMajorFinal")}
            </div>
          </div>
        </div>

        <div className="row">
          <div id="infoContainer" className="row pb-2 ml-3 mr-0">
            <div className="alert alert-info" role="info" id="infoMsg">
              {sistraMesosCercaLiteral}
            </div>
          </div>
        </div>
        
        <CarpetaFormulariDeFiltre handleSubmitSearcher={this.handleSubmitSearcher} i18n={this.props.i18n}>
          <>
            <CarpetaFormulariDeFiltreItem label={sistraEstat != null ? sistraEstat : ""}>
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
                <option value="A" className="form-control form-control-sm selectMobil">
                  {t("sistraTots")}
                </option>
                <option value="S" className="form-control form-control-sm selectMobil">
                  {t("sistraFinalitzat")}
                </option>
                <option value="N" className="form-control form-control-sm selectMobil">
                  {t("sistraNoFinalitzat")}
                </option>
                <option value="P" className="form-control form-control-sm selectMobil">
                  {t("sistraNoFinalizatPresencial")}
                </option>
              </select>
            </CarpetaFormulariDeFiltreItem>
          </>
        </CarpetaFormulariDeFiltre>
      </>
    );

    const columnsTitols = ["#", i18n.t("sistraTramit"), i18n.t("sistraData"), i18n.t("sistraEstat")];

    return (
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        <>
          {formulari}
          <div className="float-left" style={{ width: "97%", position: "relative" }}>
            <RenderTable
              ref={this.childRenderTable}
              columnNames={this.columnsNom}
              columnTitles={columnsTitols}
              rowType={RowType.EXTERNAL_LINK}
              i18n={i18n}
              onClickRow={this.onClickRow}
            />
          </div>
        </>
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(Sistra);
