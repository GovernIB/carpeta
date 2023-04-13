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
  CarpetaDatePicker,
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
  RenderTable,
  RenderTableData,
  RenderTableReturn,
  RowType,
  TemplatePageCarpeta,
} from "carpetacommonreactlib";


interface SistraProps extends WithTranslation {
  titles: any;
  subtitles: any;
  pathtoservei: string;
  detallpathtoservei: string;
}

interface SistraState {
  numeroRegistro: string | null;
}

interface Tramit {
  index:number,
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

    this.filter_status = 'A';
    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;
    this.errorEnFiltre = false;

    this.state = {
      numeroRegistro: null
    };

    /*
    this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
    this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
    this.handleChangeEstat = this.handleChangeEstat.bind(this);
    this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(
      this
    );
    this.handleSubmit = this.handleSubmit.bind(this);
    this.mostrarForm = this.mostrarForm.bind(this);
    */

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

    this.onChangeState = this.onChangeState.bind(this);
    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);

    this.processarTramits = this.processarTramits.bind(this);

    this.carregaDadesSistra = this.carregaDadesSistra.bind(this);

    //this.dateFormat = this.onChangeState.bind(this);

    this.tornarDeDetallRegistre = this.tornarDeDetallRegistre.bind(this);

    this.onClickRow = this.onClickRow.bind(this);

    //this.siNo = this.siNo.bind(this);
    this.estatTramit = this.estatTramit.bind(this);
    //this.nomEstat = this.nomEstat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    i18n.on("languageChanged", this.canviatIdioma);
  }

  /*
  mostrarForm() {
    if (
      document
        .getElementById("fechaBusqueda")
        .classList.contains("ocultarMobil")
    ) {
      document.getElementById("fechaBusqueda").classList.remove("ocultarMobil");
    }
    document.getElementById("idCriteris").style.display = "none";

    this.setState({
      ...this.state,
      formVisible: true,
    });
  }
*/
componentDidMount() {}

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

  /*
  handleChangeDataInici(e) {
    this.setState({
      ...this.state,
      dataInici: e,
    });
  }
  handleChangeDataFi(e) {
    this.setState({
      ...this.state,
      dataFi: e,
    });
  }
  handleChangeEstat(e) {
    this.setState({
      ...this.state,
      estat: e.target.value,
    });
  }
*/
  handleSubmitSearcher(e: any): boolean {
    console.log("Sistra::handleSubmitSearcher() inici");

    e.preventDefault();

    if (this.errorEnFiltre) {
      const t = this.props.i18n.t;
      window.alert(t("errorEnFiltre"));
      return false;
    }

    console.log("Sistra::handleSubmitSearcher() final");

    this.forceUpdate();

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

  carregaDadesSistra(returnData: RenderTableReturn) {

    console.log("Sistra::carregaDadesSistra() => Inici ...");

    const params = {
      dataInici: this.filter_startDate,
      dataFi: this.filter_endDate,
      estat: this.filter_status,
    };

    const url3 = this.props.pathtoservei;

    axios
      .get(url3, { params: params })
      .then((response) => {
        if (response.data == null) {
          console.log(
            "Sistra::carregaDadesSistra() => Error buit tramits? => " + response.data.tramits
          );
          let errorPantalla = "Tràmits és null .";
          let data: RenderTableData = {
            tableData: null,
            error: errorPantalla,
          };
          returnData.returnDataFunction(data);
        } else {
          let data: RenderTableData = {
            tableData: this.processarTramits(response.data.tramits),
            error: null,
          };
          returnData.returnDataFunction(data);
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
        returnData.returnDataFunction(data);
      });
  }

  /*
  handlePagination(event, accio, isNumber, pag) {
    let newPageNumber = event.target.text;
    let pagActiva;

    if (!isNumber) {
      if (accio === 0) {
        pagActiva = newPageNumber;
      }
      if (accio === 1) {
        pagActiva = this.state.pagination_active - 1;
      }
      if (accio === 2) {
        pagActiva = this.state.pagination_active + 1;
      }
    } else {
      pagActiva = pag;
    }

    this.setState({
      ...this.state,
      pagination_active: pagActiva,
      error: null,
      isLoaded: false,
    });

    const params = {
      dataInici: this.state.dataInici,
      dataFi: this.state.dataFi,
      estat: this.state.estat,
      registrosPorPagina: this.state.filter_regPorPagina,
      pageNumber: pagActiva - 1,
    };

    const url3 = this.props.pathtoservei;

    axios
      .get(url3, { params: params })
      .then((response) => {
        if (response.data != null) {
          this.setState({
            ...this.state,
            data: response.data.tramits,
            pagination_active: pagActiva,
            total_items: response.data.totalRegistres,
            pagination_total_items: response.data.registresPagina,
            isLoaded: true,
            error: null,
          });
        }
      })
      .catch((error) => {
        console.log(JSON.stringify(error));
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
  

  handleSubmit(e) {
    const { t } = this.props;
    let validatFormulari = this.validaFormulari();

    if (validatFormulari) {
      this.setState({
        ...this.state,
        isLoaded: false,
        cercaRegistres: this.state.filter_regPorPagina,
        numeroRegistro: null,
        data: null,
      });

      e.preventDefault();

      const url2 = this.props.pathtoservei;

      const params = {
        dataInici: this.state.dataInici,
        dataFi: this.state.dataFi,
        estat: this.state.estat,
        registrosPorPagina: this.state.filter_regPorPagina,
        pageNumber: 0,
      };

      this.fechaInicio = this.state.dataInici;
      this.fechaFin = this.state.dataFi;
      this.estado = this.state.estat;

      axios
        .get(url2, { params: params })
        .then((response) => {
          this.setState({
            ...this.state,
            isLoaded: true,
            pagination_active: 1,
            error: null,
          });

          if (response.data != null) {
            this.setState({
              ...this.state,
              data: response.data.tramits,
              total_items: response.data.totalRegistres,
              pagination_total_items: response.data.registresPagina,
            });
          } else {
            this.setState({
              ...this.state,
              data: null,
              total_items: 0,
              pagination_total_items: 10,
            });
          }
        })
        .catch((error) => {
          console.log(JSON.stringify(error));
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
    } else {
      e.preventDefault();
    }
  }
  */



  obrirTramit(url: string) {
    var win = window.open(url, "_blank");
    if (win) {
      win.focus();
    } else {
      alert("Permeteu finestres emergents per a aquest lloc web");
    }
  }
  /*
  validaFormulari() {
    const { t } = this.props;

    if (
      !this.validaFecha(this.state.dataInici) ||
      !this.validaFecha(this.state.dataFi)
    ) {
      return false;
    }

    if (Date.parse(this.state.dataInici) > Date.parse(this.state.dataFi)) {
      $("#errorMsg").html(t("sistraDataIniciError"));
      $("#errorContainer").removeClass("ocult");
      // $('#fechaInicio').css('border','1px solid red !important');
      return false;
    }

    return true;
  }

  validaFecha(date) {
    if (isNaN(Date.parse(date))) {
      $("#errorMsg").html("Error de data");
      $("#errorContainer").removeClass("ocult");
      return false;
    } else {
      $("#errorContainer").addClass("ocult");
      return true;
    }
  }
*/
  /*
  componentDidUpdate() {
    
    $("#dataInici").attr("tabindex", "501");
    $("#dataFi").attr("tabindex", "502");

    const { t } = this.props;

    let taulaTramits;
    var tamanyTaula = { width: "99%" };
    var tamanyData = { width: "120px !important" };
    var cursorPointer = { cursor: "pointer" };

    if (
      this.state.data &&
      typeof this.state.total_items !== undefined &&
      typeof this.state.data !== undefined &&
      this.state.total_items !== 0
    ) {
      let paginationNumbers = [];
      let showMax = 5;
      let endPage;
      let startPage;
      let pageNumbers = Math.ceil(
        this.state.total_items / this.state.cercaRegistres
      );
      if (pageNumbers <= showMax) {
        startPage = 1;
        endPage = pageNumbers;
      } else {
        if (this.state.pagination_active < 4) {
          startPage = 1;
        } else {
          startPage = this.state.pagination_active - 2;
        }
        if (this.state.pagination_active + 2 < pageNumbers) {
          endPage = this.state.pagination_active + 2;
        } else {
          endPage = pageNumbers;
        }
      }

      for (let number = startPage; number <= endPage; number++) {
        if (number === startPage && startPage > 1) {
          paginationNumbers.push(
            <Pagination.Ellipsis key={number} className="muted" />
          );
        }
        paginationNumbers.push(
          <Pagination.Item
            key={number}
            active={
              number.toString() === this.state.pagination_active.toString()
            }
            activeLabel=""
            onClick={(event) => this.handlePagination(event, 0, false, 0)}
          >
            {number}
          </Pagination.Item>
        );
        if (number === endPage && endPage < pageNumbers) {
          paginationNumbers.push(
            <Pagination.Ellipsis key={number} className="muted" />
          );
        }
      }

      taulaTramits = (
        <>
          <Table responsive striped bordered hover style={tamanyTaula}>
            <thead className="table-success">
              <tr>
                <th>{t("sistraTramit")}</th>
                <th style={{ whiteSpace: "nowrap" }}>{t("sistraData")}</th>
                <th style={{ whiteSpace: "nowrap" }}>{t("sistraEstat")}</th>
              </tr>
            </thead>
            <tbody>
              {this.state.data.map(
                ({
                  descripcionTramite,
                  fechaInicio,
                  pendiente,
                  numero,
                  url,
                  versionSistra,
                  mostraModal,
                  tipo,
                }) => {
                  return (
                    <tr
                      className="clickableRow"
                      data-numero={numero}
                      data-url={url}
                      onClick={() => this.obrirTramit(url)}
                      data-version={versionSistra}
                      style={cursorPointer}
                      data-mostramodal={$.siNo(mostraModal)}
                      data-pending={$.siNo(pendiente)}
                      tabIndex="511"
                    >
                      <td>{descripcionTramite}</td>
                      <td
                        data-order={$.dateOrder(fechaInicio)}
                        style={{ whiteSpace: "nowrap" }}
                      >
                        {$.dateFormat(fechaInicio)}
                      </td>
                      <td style={{ whiteSpace: "nowrap" }}>
                        {$.estatTramit(pendiente, mostraModal, tipo, numero)}
                      </td>
                    </tr>
                  );
                }
              )}
            </tbody>
          </Table>
          <Pagination>{paginationNumbers}</Pagination>
        </>
      );
    } else if (this.state.total_items === 0 && this.state.data !== null) {
      taulaTramits = (
        <div
          className="pt-3 alert alert-secondary margeBuid"
          style={{ float: "left", width: "95%" }}
          role="alert"
        >
          {t("sistraBuid")}
        </div>
      );
    }

    $("#veureError").click(function() {
      $("#ocultaError").removeClass("ocult");
      $("#veureError").addClass("ocult");
      $("#divOcult").css("display", "block");
    });

    $("#ocultaError").click(function() {
      $("#ocultaError").addClass("ocult");
      $("#veureError").removeClass("ocult");
      $("#divOcult").css("display", "none");
    });

    $("tr").each(function(i) {
      $(this).removeAttr("role");
    });
*/
  /*
        let detallRegistreContainer;
        if ( this.state.numeroRegistro != null ){
            detallRegistreContainer = <DetallRegistre pathtoservei={props.detallpathtoservei} numero={this.state.numeroRegistro} />;
            ReactDOM.render(detallRegistreContainer, document.getElementById('sistracontainer'));
        }
        */
  // }
  /*
  handleItemClick(numeroRegistro:string) {
    this.setState({
      numeroRegistro: numeroRegistro,
    });
  }
*/
  tornarDeDetallRegistre(_e: any) {
    //this.handleSubmit(e);
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

  /*
  siNo(variable: any) {
    if (variable) {
      return "S";
    } else {
      return "N";
    }
  }
  */

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

  /*
  nomEstat(estat:string) {
    if (estat === "A") {
      return this.props.i18n.t("sistraTots");
    } else if (estat === "S") {
      return this.props.i18n.t("sistraFinalitzat");
    } else if (estat === "N") {
      return this.props.i18n.t("sistraNoFinalitzat");
    } else if (estat === "P") {
      return this.props.i18n.t("sistraNoFinalizatPresencial");
    } else if (estat === "R") {
      return this.props.i18n.t("sistraRegistrat");
    } else {
      return "--Valor Desconegut [" + estat + "]";
    }
  };
*/

  render() {
    console.log("SISTRA:: constructor ...");

    /*
    $.dateOrder = function(dateObject) {
      var d = new Date(dateObject);
      var day = d.getDate();
      var month = d.getMonth() + 1;
      var year = d.getFullYear();
      var hour = d.getHours();
      var minute = d.getMinutes();
      if (day < 10) {
        day = "0" + day;
      }
      if (month < 10) {
        month = "0" + month;
      }
      if (hour < 10) {
        hour = "0" + hour;
      }
      if (minute < 10) {
        minute = "0" + minute;
      }
      return (
        year.toString() +
        month.toString() +
        day.toString() +
        hour.toString() +
        minute.toString()
      );
    };

    $.dateFormat = function(dateObject) {
      var d = new Date(dateObject);
      var day = d.getDate();
      var month = d.getMonth() + 1;
      var year = d.getFullYear();
      var hour = d.getHours();
      var minute = d.getMinutes();
      if (day < 10) {
        day = "0" + day;
      }
      if (month < 10) {
        month = "0" + month;
      }
      if (hour < 10) {
        hour = "0" + hour;
      }
      if (minute < 10) {
        minute = "0" + minute;
      }
      return day + "-" + month + "-" + year + " " + hour + ":" + minute;
    };

    
    $.dateFormatCerca = function(dateObject) {
      var d = new Date(dateObject);
      var day = d.getDate();
      var month = d.getMonth() + 1;
      var year = d.getFullYear();
      if (day < 10) {
        day = "0" + day;
      }
      if (month < 10) {
        month = "0" + month;
      }
      return day + "-" + month + "-" + year;
    };
*/
    const t = this.props.i18n.t;
    let formulari;
    /*
    const isLoaded = this.state.isLoaded;

    

    var tamanyData = { width: "120px !important" };
    var cursorPointer = { cursor: "pointer" };
    var tamanyTaula = { width: "99%" };

    let content;
    

    let taulaTramits;
    let detallRegistreContainer;
    let selectRegistres;
    let criteris;

    let cardTramits = [];
    */

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

    formulari = (
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
            <CarpetaFormulariDeFiltreItem label={t("sistraDataInici")}>
              <CarpetaDatePicker
                basename={"dataInici"}
                defaultValue={this.filter_startDate}
                onChangeDate={this.onChangeStartDate}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("sistraDataFi")}>
              <CarpetaDatePicker
                basename={"dataFi"}
                defaultValue={this.filter_endDate}
                onChangeDate={this.onChangeEndDate}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("sistraEstat")}>
              <select
                id="estado"
                name="estado"
                className="form-control form-control-sm focusIn font1App form-select"
                tabIndex={504}
                aria-labelledby="estado"
                onChange={(e) => {
                  this.onChangeState(e);
                }}
                value={this.filter_status}
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

    /*
              <Col className="col-xs-12 mb-3 campFormApp">
                <Form.Group>
                  <Form.Label>{t("sistraDataInici")}</Form.Label>

                  <DatePicker
                    portalId="root-portal"
                    selected={this.state.dataInici}
                    onChange={(startDate) =>
                      this.handleChangeDataInici(startDate)
                    }
                    selectsStart
                    // startDate={this.state.dataInici}
                    // endDate={this.state.dataFi}
                    name="dataInici"
                    id="dataInici"
                    dateFormat="dd/MM/yyyy"
                    className="form-control form-control-sm estilCalendar focusIn font1App"
                    locale={this.locale}
                    showYearDropdown={true}
                    preventOpenOnFocus={true}
                    popperPlacement="bottom"
                    popperModifiers={{
                      flip: {
                        behavior: ["bottom"], // don't allow it to flip to be above
                      },
                      preventOverflow: {
                        enabled: false, // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                      },
                      hide: {
                        enabled: false, // turn off since needs preventOverflow to be enabled
                      },
                    }}
                  />
                </Form.Group>
              </Col>
              <Col className="col-xs-12 mb-3 campFormApp">
                <Form.Group>
                  <Form.Label>{t("sistraDataFi")}</Form.Label>
                  <DatePicker
                    portalId="root-portal"
                    selected={this.state.dataFi}
                    onChange={(endDate) => this.handleChangeDataFi(endDate)}
                    selectsEnd
                    // startDate={this.state.dataInici}
                    // endDate={this.state.dataFi}
                    minDate={this.state.dataInici}
                    name="dataFi"
                    id="dataFi"
                    dateFormat="dd/MM/yyyy"
                    className="form-control form-control-sm estilCalendar focusIn font1App"
                    locale={this.locale}
                    showYearDropdown={true}
                    preventOpenOnFocus={true}
                    popperPlacement="bottom"
                    popperModifiers={{
                      flip: {
                        behavior: ["bottom"], // don't allow it to flip to be above
                      },
                      preventOverflow: {
                        enabled: false, // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                      },
                      hide: {
                        enabled: false, // turn off since needs preventOverflow to be enabled
                      },
                    }}
                  />
                </Form.Group>
              </Col>
              <Col className="col-xs-12 mb-3 campFormApp">
                <Form.Group>
                  <Form.Label id="estado">{t("sistraEstat")}</Form.Label>
                  <Form.Select
                    id="tramiteFinalizado"
                    name="tramiteFinalizado"
                    className="form-control form-control-sm focusIn font1App"
                    value={this.state.estat}
                    tabindex="503"
                    aria-labelledby="estado"
                    onChange={(e) => {
                      this.handleChangeEstat(e);
                    }}
                  >
                    <option
                      value="A"
                      className="form-control form-control-sm selectMobil"
                      selected={this.state.estat === "A"}
                    >
                      {t("sistraTots")}
                    </option>
                    <option
                      value="S"
                      className="form-control form-control-sm selectMobil"
                      selected={this.state.estat === "S"}
                    >
                      {t("sistraFinalitzat")}
                    </option>
                    <option
                      value="N"
                      className="form-control form-control-sm selectMobil"
                      selected={this.state.estat === "N"}
                    >
                      {t("sistraNoFinalitzat")}
                    </option>
                    <option
                      value="P"
                      className="form-control form-control-sm selectMobil"
                      selected={this.state.estat === "P"}
                    >
                      {t("sistraNoFinalizatPresencial")}
                    </option>
                    
                  </Form.Select>
                </Form.Group>
              </Col>
            </Row>
            
            <Row>
              <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                <div
                  className="alert alert-danger"
                  role="alert"
                  id="errorMsg"
                />
              </div>
            </Row>
            <Row
              className="col-md-3 pl-3 row botoFormApp"
              style={{ zIndex: "4" }}
            >
              <Button
                type="submit"
                className="btn btn-primary carpeta-btn mt-2"
                onClick={(e) => {
                  this.handleSubmit(e);
                }}
                tabindex="504"
              >
                {t("sistraCercaBoto")}
              </Button>
            </Row>
          </Container>
        </Form>
              */

    /*
    if (!isLoaded) {
      taulaTramits = "";

      content = (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );

      selectRegistres = "";
      criteris = "";
    } else {
      criteris = (
        <div id="idCriteris">
          <div
            style={{ float: "left", marginTop: "9px;", paddingBottom: "0.7em" }}
            className="col-md-10 pr-3 visioMobil"
          >
            <span
              className="oi oi-calendar iconaFormApp"
              title={t("sistraDates")}
              style={{ verticalAlign: "sub" }}
            />
            <span className="pl-3">
              {$.dateFormatCerca(this.state.dataInici) +
                t("carpeta_criterio_5") +
                $.dateFormatCerca(this.state.dataFi)}
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
            <span
              className="oi oi-magnifying-glass iconaFormApp"
              title={t("sistraCerca")}
            />
          </div>
        </div>
      );

      if (
        this.state.data &&
        typeof this.state.total_items !== undefined &&
        typeof this.state.data !== undefined &&
        this.state.total_items !== 0
      ) */ {
      /*
        let paginationNumbers = [];

        let showMax = 5;
        let endPage;
        let startPage;
        let pageNumbers = Math.ceil(
          this.state.total_items / this.state.cercaRegistres
        );
        if (pageNumbers <= showMax) {
          startPage = 1;
          endPage = pageNumbers;
        } else {
          if (this.state.pagination_active < 4) {
            startPage = 1;
          } else {
            startPage = this.state.pagination_active - 2;
          }
          if (this.state.pagination_active + 2 < pageNumbers) {
            endPage = this.state.pagination_active + 2;
          } else {
            endPage = pageNumbers;
          }
        }

        for (let number = startPage; number <= endPage; number++) {
          if (number === startPage && startPage > 1) {
            paginationNumbers.push(
              <Pagination.Ellipsis key={number} className="muted" />
            );
          }
          paginationNumbers.push(
            <Pagination.Item
              key={number}
              active={
                number.toString() === this.state.pagination_active.toString()
              }
              activeLabel=""
              onClick={(event) => this.handlePagination(event, 0, false, 0)}
            >
              {number}
            </Pagination.Item>
          );
          if (number === endPage && endPage < pageNumbers) {
            paginationNumbers.push(
              <Pagination.Ellipsis key={number} className="muted" />
            );
          }
        }

        selectRegistres = (
          <div className="col-md-12 border-0 p-0">
            <div className="col-sd-1 pb-2 margRegSelect">
              <p className="lh15 mb-1 mRegSelApp">
                {t("sistraMostra")}
                <Form.Select
                  id="rPP"
                  name="rPP"
                  className="focusIn"
                  value={this.state.filter_regPorPagina}
                  tabindex="510"
                  aria-labelledby="rPP"
                  style={{ color: "#666", borderRadius: "0.2rem" }}
                  onChange={(e) => {
                    this.handleRegPorPaginaFilterParam(e);
                  }}
                >
                  <option
                    value="5"
                    className="form-control form-control-sm selectMobil"
                  >
                    5
                  </option>
                  <option
                    value="10"
                    className="form-control form-control-sm selectMobil"
                  >
                    10
                  </option>
                  <option
                    value="25"
                    className="form-control form-control-sm selectMobil"
                  >
                    25
                  </option>
                </Form.Select>
                {t("sistraRegistres")}
              </p>
            </div>
          </div>
        );

        taulaTramits = (
          <>
            <Table
              responsive
              striped
              bordered
              hover
              style={tamanyTaula}
              className="ocultarMobil"
            >
              <thead className="table-success">
                <tr>
                  <th>{t("sistraTramit")}</th>
                  <th style={{ whiteSpace: "nowrap" }}>{t("sistraData")}</th>
                  <th style={{ whiteSpace: "nowrap" }}>{t("sistraEstat")}</th>
                </tr>
              </thead>
              <tbody>
                {this.state.data.map(
                  (
                    {
                      descripcionTramite,
                      fechaInicio,
                      pendiente,
                      numero,
                      url,
                      versionSistra,
                      mostraModal,
                      tipo,
                    },
                    i
                  ) => {
                    if (numero !== "")
                      return (
                        <>
                          <tr
                            className="clickableRow"
                            data-numero={numero}
                            data-url={url}
                            data-version={versionSistra}
                            style={cursorPointer}
                            data-mostramodal={$.siNo(mostraModal)}
                            data-pending={$.siNo(pendiente)}
                            tabIndex="511"
                            onClick={(e) => this.handleItemClick(numero)}
                          >
                            <td>{descripcionTramite}</td>
                            <td
                              data-order={$.dateOrder(fechaInicio)}
                              style={{ whiteSpace: "nowrap" }}
                            >
                              {$.dateFormat(fechaInicio)}
                            </td>
                            <td style={{ whiteSpace: "nowrap" }}>
                              {$.estatTramit(
                                pendiente,
                                mostraModal,
                                tipo,
                                numero
                              )}
                            </td>
                          </tr>
                        </>
                      );

                    if (numero === "" && pendiente && mostraModal)
                      return (
                        <>
                          <tr
                            className="clickableRow"
                            data-numero={numero}
                            data-url={url}
                            data-version={versionSistra}
                            style={cursorPointer}
                            data-mostramodal={$.siNo(mostraModal)}
                            data-pending={$.siNo(pendiente)}
                            tabIndex="511"
                            onClick={(e) => this.openModalConfirm(url)}
                          >
                            <td>{descripcionTramite}</td>
                            <td
                              data-order={$.dateOrder(fechaInicio)}
                              style={{ whiteSpace: "nowrap" }}
                            >
                              {$.dateFormat(fechaInicio)}
                            </td>
                            <td style={{ whiteSpace: "nowrap" }}>
                              {$.estatTramit(
                                pendiente,
                                mostraModal,
                                tipo,
                                numero
                              )}
                            </td>
                          </tr>
                        </>
                      );

                    if (numero === "" && (!pendiente || !mostraModal))
                      return (
                        <>
                          <tr
                            className="clickableRow"
                            data-numero={numero}
                            data-url={url}
                            data-version={versionSistra}
                            style={cursorPointer}
                            data-mostramodal={$.siNo(mostraModal)}
                            data-pending={$.siNo(pendiente)}
                            tabIndex="511"
                            onClick={(e) => window.open(url, "_blank")}
                          >
                            <td>{descripcionTramite}</td>
                            <td
                              data-order={$.dateOrder(fechaInicio)}
                              style={{ whiteSpace: "nowrap" }}
                            >
                              {$.dateFormat(fechaInicio)}
                            </td>
                            <td style={{ whiteSpace: "nowrap" }}>
                              {$.estatTramit(
                                pendiente,
                                mostraModal,
                                tipo,
                                numero
                              )}
                            </td>
                          </tr>
                        </>
                      );
                  }
                )}
              </tbody>
            </Table>
            <div
              style={{ float: "left", marginTop: "9px;", width: "60%" }}
              className="ocultarMobil"
            >
              {t("carpeta_paginacion_1") +
                ((parseInt(this.state.pagination_active, 10) - 1) *
                  parseInt(this.state.cercaRegistres, 10) +
                  1) +
                t("carpeta_paginacion_2") +
                (parseInt(this.state.pagination_active, 10) *
                  parseInt(this.state.cercaRegistres, 10) <=
                parseInt(this.state.total_items, 10)
                  ? parseInt(this.state.pagination_active, 10) *
                    parseInt(this.state.cercaRegistres, 10)
                  : this.state.total_items) +
                t("carpeta_paginacion_3") +
                this.state.total_items +
                t("carpeta_paginacion_4")}
            </div>
            <Pagination
              style={{ float: "right", paddingRight: "0.7em" }}
              className="ocultarMobil"
            >
              {pageNumbers > showMax && (
                <Pagination.First
                  onClick={(event) => this.handlePagination(event, 0, true, 1)}
                  disabled={this.state.pagination_active === 1}
                />
              )}
              {pageNumbers > showMax && (
                <Pagination.Prev
                  onClick={(event) => this.handlePagination(event, 1, false, 0)}
                  disabled={this.state.pagination_active === 1}
                />
              )}
              {paginationNumbers}
              {pageNumbers > showMax && (
                <Pagination.Next
                  onClick={(event) => this.handlePagination(event, 2, false, 0)}
                  disabled={this.state.pagination_active === pageNumbers}
                />
              )}
              {pageNumbers > showMax && (
                <Pagination.Last
                  onClick={(event) =>
                    this.handlePagination(event, 0, true, pageNumbers)
                  }
                  disabled={this.state.pagination_active === pageNumbers}
                />
              )}
            </Pagination>
          </>
        );

        this.state.data.map(
          (
            {
              descripcionTramite,
              fechaInicio,
              pendiente,
              numero,
              url,
              versionSistra,
              mostraModal,
              tipo,
            },
            i
          ) => {
            if (numero !== "")
              cardTramits.push(
                <div
                  className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                  key={i}
                  tabIndex={502 + i}
                  data-numero={numero}
                  data-url={url}
                  data-version={versionSistra}
                  style={cursorPointer}
                  data-mostramodal={$.siNo(mostraModal)}
                  data-pending={$.siNo(pendiente)}
                  onClick={(e) => this.handleItemClick(numero)}
                >
                  <div className="col-sm-1 float-left">
                    <span
                      className="oi oi-circle-check iconaFormApp"
                      title={t("sistraDates")}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  <div className="col-sm-10 float-right">
                    <p
                      className="card-text pl-1 mt-0 font-weight-bold"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {descripcionTramite}
                    </p>
                    <p
                      className="card-text pl-1"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {$.dateFormat(fechaInicio)}
                    </p>
                    <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">
                      {$.estatTramit(pendiente, mostraModal, tipo, numero)}
                    </h3>
                  </div>
                </div>
              );

            if (numero === "" && pendiente && mostraModal)
              cardTramits.push(
                <div
                  className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                  key={i}
                  tabIndex={502 + i}
                  data-numero={numero}
                  data-url={url}
                  data-version={versionSistra}
                  style={cursorPointer}
                  data-mostramodal={$.siNo(mostraModal)}
                  data-pending={$.siNo(pendiente)}
                  onClick={(e) => this.openModalConfirm(url)}
                >
                  <div className="col-sm-1 float-left">
                    <span
                      className="oi oi-pencil iconaFormApp"
                      title={t("sistraDates")}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  <div className="col-sm-10 float-right">
                    <p
                      className="card-text pl-1 mt-0 font-weight-bold"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {descripcionTramite}
                    </p>
                    <p
                      className="card-text pl-1"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {$.dateFormat(fechaInicio)}
                    </p>
                    <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">
                      {$.estatTramit(pendiente, mostraModal, tipo, numero)}
                    </h3>
                  </div>
                </div>
              );

            if (numero === "" && (!pendiente || !mostraModal))
              cardTramits.push(
                <div
                  className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                  key={i}
                  tabIndex={502 + i}
                  data-numero={numero}
                  data-url={url}
                  data-version={versionSistra}
                  style={cursorPointer}
                  data-mostramodal={$.siNo(mostraModal)}
                  data-pending={$.siNo(pendiente)}
                  onClick={(e) => window.open(url, "_blank")}
                >
                  <div className="col-sm-1 float-left">
                    <span
                      className="oi oi-pencil iconaFormApp"
                      title={t("sistraDates")}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  <div className="col-sm-10 float-right">
                    <p
                      className="card-text pl-1 mt-0 font-weight-bold"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {descripcionTramite}
                    </p>
                    <p
                      className="card-text pl-1"
                      style={{ color: "rgb(102, 102, 102)" }}
                    >
                      {$.dateFormat(fechaInicio)}
                    </p>
                    <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">
                      {$.estatTramit(pendiente, mostraModal, tipo, numero)}
                    </h3>
                  </div>
                </div>
              );
          }
        );

        cardTramits.push(
          <div className="visioMobil">
            <div
              style={{ float: "left", marginTop: "9px;", width: "100%" }}
              className="visioMobil pb-4"
            >
              {t("carpeta_paginacion_1_App") +
                ((parseInt(this.state.pagination_active, 10) - 1) *
                  parseInt(this.state.cercaRegistres, 10) +
                  1) +
                t("carpeta_paginacion_2") +
                (parseInt(this.state.pagination_active, 10) *
                  parseInt(this.state.cercaRegistres, 10) <=
                parseInt(this.state.total_items, 10)
                  ? parseInt(this.state.pagination_active, 10) *
                    parseInt(this.state.cercaRegistres, 10)
                  : this.state.total_items) +
                t("carpeta_paginacion_3_App") +
                this.state.total_items +
                t("carpeta_paginacion_4")}
            </div>
            <Pagination
              style={{ float: "left", paddingRight: "0.7em", width: "100%" }}
              size="lg"
            >
              {paginationNumbers}
            </Pagination>
          </div>
        );
      } else if (this.state.total_items === 0 && this.state.data !== null) {
        taulaTramits = (
          <>
            <div
              className="pt-3 alert alert-secondary"
              style={{ float: "left", width: "95%" }}
              role="alert"
            >
              {t("sistraBuid")}
            </div>
          </>
        );
      }
    }
    */

      const columnsTitols = ['#', i18n.t("sistraTramit"), i18n.t("sistraData"), i18n.t("sistraEstat")];

      return (
        <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
          <>
            {formulari}
            <div className="float-left" style={{ width: "97%", position: "relative" }}>
              <RenderTable
                loadData={this.carregaDadesSistra}
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
}
/*
        <div className="titolPaginaApp visioMobil">
          {this.props.titles[i18n.language]}
        </div>
        <div className="infoNoMenu">
          {this.state.numeroRegistro == null && (
            <>
              <h2 className="titol h2 ocultarMobil">
                {this.props.titles[i18n.language]}
              </h2>
              <div className="col-md-12 border-0 float-left p-0">
                <p className="lh15 ocultarMobil">
                  {this.props.subtitles[i18n.language]}{" "}
                </p>
                <div className="infoNoMenu">
                  <div className="col-md-12 border-0 float-left p-0">
                    {!this.state.formVisible && criteris}
                    {formulari}
                    {this.state.error && (
                      <div className="alert alert-danger hide" role="alert">
                        {this.state.error}
                      </div>
                    )}
                    {content}
                  </div>
                </div>
              </div>
              <div
                className="float-left"
                style={{ width: "97%", position: "relative" }}
              >
                {selectRegistres}
                {taulaTramits}
                {cardTramits}
              </div>
              <div
                className="col-md-12 border-0 float-left p-0"
                id="botoTornarSistra"
                style={{ marginTop: "20px" }}
              >
                <button
                  type="button"
                  data-toggle="modal"
                  onClick={() => {
                    window.location.href = sessionStorage.getItem("pagTornar");
                    sessionStorage.setItem(
                      "pagTornar",
                      sessionStorage.getItem("contextPath")
                    );
                  }}
                  className="botoSuport botoTornauApp"
                  tabIndex="520"
                  aria-labelledby="botoTornarSistra"
                >
                  {t("sistraTornar")}
                </button>
              </div>
            </>
          )}
          {this.state.numeroRegistro != null && detallRegistreContainer}
        </div>
                */

export default withTranslation()(Sistra);
