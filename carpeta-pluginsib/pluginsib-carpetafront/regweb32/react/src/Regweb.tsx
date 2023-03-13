import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";


import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";


import { DetallRegistre } from "regwebdetallcomponentlib";
import {
  PaginationInfo,
  RenderPaginationTable,
  RenderPaginationTableData,
  ReturnPaginationData,
  TemplatePageCarpeta,
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
  numeroregistro?: string;
}

interface RegwebState {
  //data: string | null;
  filter_number: string | null;
  filter_startDate: Date;
  filter_endDate: Date;
  filter_status: number;
  //filter_regPorPagina: 5;
  //pagination_active: 1,
  //pagination_total_items: 5,
  numeroRegistro: string | null;
  //error: null,
  criteriosTexto: string;
  //cercaRegistres: 5,
  formVisible: boolean;
}

class Regweb extends React.Component<RegwebProps, RegwebState> {


  private locale:any;

  constructor(props: RegwebProps) {
    super(props);

    console.log("RegWeb::CONSTRUCTOR => Inici ");

    //console.log("RegWeb::CONSTRUCTOR this.props.detallpathtoservei => ]" + this.props.detallpathtoservei + "[");

    //console.log("RegWeb::CONSTRUCTOR this.props.numeroregistro => ]" + this.props.numeroregistro + "[");

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);
    // XYZ ZZZ
    startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.state = {
      //isLoaded: false,
      filter_number: null,
      filter_startDate: startDateObj,
      filter_endDate: endDateObj,
      filter_status: 0,
      //filter_regPorPagina: 5,
      //pagination_active: 1,
      //pagination_total_items: 5,
      numeroRegistro: null,
      //error: null,
      criteriosTexto: "",
      //cercaRegistres: 5,
      formVisible: false,
    };
 
    this.handleNumberFilterParam = this.handleNumberFilterParam.bind(this);
    this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
    this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
    this.handleStateFilterParam = this.handleStateFilterParam.bind(this);

    //this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);
    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
    //this.mostrarForm = this.mostrarForm.bind(this);
    this.tornarDeDetallRegistre = this.tornarDeDetallRegistre.bind(this);

    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    //this.dateFormatCerca = this.dateFormatCerca.bind(this);

    this.nomEstat = this.nomEstat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);

    this.onClickRow = this.onClickRow.bind(this);


    i18n.on("languageChanged", this.canviatIdioma);


    console.log("RegWeb::CONSTRUCTOR => language: " + this.props.i18n.language);

    const getLocale = (Locale:any) =>
      require(`date-fns/locale/${sessionStorage.getItem(
        "langActual"
      )}/index.js`);
    this.locale = getLocale(this.props.i18n.language);

    console.log("RegWeb::CONSTRUCTOR => Final ");
  }

  /*
  private mostrarForm() {
    let fechaBusqueda = document.getElementById("fechaBusqueda");

    if (fechaBusqueda) {
      if (fechaBusqueda.classList.contains("ocultarMobil")) {
        fechaBusqueda.classList.remove("ocultarMobil");
      }
      let idCriteris = document.getElementById("idCriteris");
      if (idCriteris) {
        idCriteris.style.display = "none";
      }

      this.setState({
        ...this.state,
        formVisible: true,
      });
    }
  }
  */

  canviatIdioma(lng: string) {
    //this.componentDidMount();
  }


  handleStartDateFilterParam(startDate: Date) {
    //let date:Date|null =  this.validaFecha(startDate)
    //if (date != null) {
      this.setState({
        ...this.state,
        filter_startDate: startDate,
      });
    //}
  }

  handleEndDateFilterParam(endDate: Date) {
    //let date:Date|null =  this.validaFecha(endDate)
    //if (date != null) {
      this.setState({
        ...this.state,
        filter_endDate: endDate,
      });
    //}
  }

  handleStateFilterParam(e: any) {
    this.setState({
      ...this.state,
      filter_status: e.target.value,
    });
  }

  handleNumberFilterParam(e: any) {
    this.setState({
      ...this.state,
      filter_number: e.target.value,
    });
  }

  /*
  handleRegPorPaginaFilterParam(e) {
    this.setState({
      ...this.state,
      filter_regPorPagina: e.target.value,
      isLoaded: false,
      error: null,
      cercaRegistres: e.target.value,
    });

    const params = {
      numero: this.state.filter_number,
      fechaInicio: this.state.filter_startDate,
      fechaFin: this.state.filter_endDate,
      estado: this.state.filter_status,
      registrosPorPagina: e.target.value,
      pageNumber: 0,
    };

    const url3 = this.props.pathtoservei;

    axios
      .get(url3, { params: params })
      .then((response) => {
        if (response.data.registres != null) {
          this.setState({
            ...this.state,
            data: JSON.parse(response.data.registres),
            pagination_active: 1,
            pagination_total_items: response.data.totalRegistres,
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
  */

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;
    /*
              <tbody>
                {this.state.data.map(
                  ({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino, estado }, i) => {
                    return (
                      <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro)}>
                        <td>{numeroRegistro}</td>
                        <td style={{ whiteSpace: "nowrap" }}>{$.dateFormat(fechaRegistro)}</td>
                        <td>{extracto}</td>
                        <td>{t("registro_estado_" + estado)}</td>
                        <td>{denominacionDestino}</td>
*/

    console.log("regWeb::loadPaginatedData() => page: " + page);
    console.log("regWeb::loadPaginatedData() => elementsByPage: " + elementsByPage);

    const params = {
      numero: this.state.filter_number,
      fechaInicio: this.state.filter_startDate,
      fechaFin: this.state.filter_endDate,
      estado: this.state.filter_status,
      registrosPorPagina: elementsByPage,
      // La paginacio en Regweb comença per 0
      pageNumber: page,
    };

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
          console.log("regWeb::loadPaginatedData() => OK registres? => " + response.data.registres);
          console.log("regWeb::loadPaginatedData() => OK paginacio? => " + response.data.paginacio);

          let paginationInfo: PaginationInfo = {
            // La paginacio en Regweb comença per 1
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


  onClickRow(pos:number, item:any) {

   
    console.log("RegWeb::onClickRow(" + pos + "," + item + ") => Numero Registre " + item.numeroRegistro);

    this.setState({
      ...this.state,
      numeroRegistro: item.numeroRegistro
    });

  }

  handleSubmitSearcher(e: any) {
    const { t } = this.props;

    let validatFormulari = this.validaFormulari();

    if (validatFormulari) {
      e.preventDefault();

      this.setState({
        ...this.state,
        numeroRegistro: null,
      });

      /*
      let criterisCercaText =
        t("carpeta_criterio_1") +
        this.dateFormatCerca(this.state.filter_startDate) +
        t("carpeta_criterio_2") +
        this.dateFormatCerca(this.state.filter_endDate) +
        t("carpeta_criterio_3") +
        this.nomEstat(this.state.filter_status) +
        t("carpeta_criterio_4") +
        (this.state.filter_number != null ? t("carpeta_criterio_5") + this.state.filter_number : "");

      this.setState({
        ...this.state,
        numeroRegistro: null,
        
        data: null,
      });

      const url2 = this.props.pathtoservei;

      const params = {
        numero: this.state.filter_number,
        fechaInicio: this.state.filter_startDate,
        fechaFin: this.state.filter_endDate,
        estado: this.state.filter_status,
        registrosPorPagina: this.state.filter_regPorPagina,
        pageNumber: 0,
      };

      axios
        .get(url2, { params: params })
        .then((response) => {
          if (response.data.registres != null) {
            this.setState({
              ...this.state,
              data: JSON.parse(response.data.registres),
              pagination_active: 1,
              pagination_total_items: response.data.totalRegistres,
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
        */
    } else {
      e.preventDefault();
    }
  }

  componentDidMount() {
    /*
    console.log("componentDidMount::1");

    console.log("RegWeb::componentDidMount props.numeroregistro => ]" + props.numeroregistro + "[");

    var numeroRegistro = props.numeroregistro;

    console.log("RegWeb::componentDidMount numeroRegistro => ]" + numeroRegistro + "[");

    if (numeroRegistro && numeroRegistro == "") {
      numeroRegistro = null;
    }

    console.log("RegWeb::componentDidMount numeroRegistro NETEJAT => ]" + numeroRegistro + "[");

    // DETALL DE REGISTRE via Parametre ...
    if (numeroRegistro) {
      console.log("RegWeb::componentDidMount cridant a Detall de Registre => ]" + numeroRegistro + "[");
      this.setState({
        ...this.state,
        isLoaded: false,
        numeroRegistro: numeroRegistro,
      });
      return;
    }

    const getLocale = (locale) => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
    this.locale = getLocale(this.props.language);

    console.log("componentDidMount::2");

    this.setState({
      ...this.state,
      isLoaded: true,
    });

    console.log("componentDidMount::3");

    const { t } = this.props;

    let validatFormulari = this.validaFormulari();

    console.log("componentDidMount::4");

    if (validatFormulari) {
      console.log("componentDidMount::5");

      let criterisCercaText =
        t("carpeta_criterio_1") +
        $.dateFormatCerca(this.state.filter_startDate) +
        t("carpeta_criterio_2") +
        $.dateFormatCerca(this.state.filter_endDate) +
        t("carpeta_criterio_3") +
        $.nomEstat(this.state.filter_status) +
        t("carpeta_criterio_4") +
        (this.state.filter_number != null ? t("carpeta_criterio_5") + this.state.filter_number : "");

      this.setState({
        ...this.state,
        isLoaded: false,
        error: null,
        numeroRegistro: null,
        criteriosTexto: criterisCercaText,
      });

      const url = this.props.pathtoservei;

      const params = {
        numero: this.state.filter_number,
        fechaInicio: this.state.filter_startDate,
        fechaFin: this.state.filter_endDate,
        estado: this.state.filter_status,
        registrosPorPagina: this.state.filter_regPorPagina,
        pageNumber: this.state.pagination_active - 1,
      };

      console.log("componentDidMount::6");

      axios
        .get(url, { params: params })
        .then((response) => {
          if (response.data.registres != null) {
            this.setState({
              ...this.state,
              data: JSON.parse(response.data.registres),
              pagination_active: 1,
              pagination_total_items: response.data.totalRegistres,
              isLoaded: true,
              error: null,
            });
          }
        })
        .catch((error) => {
          console.log("componentDidMount::7");
          console.log(JSON.stringify(error));
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

    console.log("componentDidMount::8");
    */
  }

  validaFormulari() {
    /*
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

  /*
  componentDidUpdate() {}

  handleItemClick(numeroRegistro: string) {
    console.log("RegWeb:: Seleccionat registre " + numeroRegistro);

    this.setState({
      ...this.state,
      numeroRegistro: numeroRegistro,
    });
  }
*/
  tornarDeDetallRegistre(e: any) {
    this.handleSubmitSearcher(e);
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
    //return day + "-" + month + "-" + year + " " + hour + ":" + minute;
  }

  dateOrder(dateObject: any) {
    var d = new Date(dateObject);

    let composed = d.getFullYear().toString();

    composed = composed + (d.getMonth() < 9 ? "0" : "") + (d.getMonth() + 1);

    composed = composed + (d.getDate() < 10 ? "0" : "") + d.getDate();

    composed = composed + (d.getHours() < 10 ? "0" : "") + d.getHours();

    composed = composed + (d.getMinutes() < 10 ? "0" : "") + d.getMinutes();

    return composed;
  }
  /*
  dateFormatCerca(dateObject: any) {
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
  }
*/
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

  render() {
    const t = this.props.i18n.t;

    const locale: string = this.props.i18n.language;

    let formulari = <></>;

    //let detallRegistreContainer;

    //var tamanyTaula = { width: "99%" };

    if (this.state.numeroRegistro != null) {
      console.log("Regweb:: detallpathtoservei= " + this.props.detallpathtoservei);
      console.log("Regweb:: numeroRegistro= " + this.state.numeroRegistro);

      let numReg: string = this.state.numeroRegistro;

      return <DetallRegistre
        pathtoservei={this.props.detallpathtoservei}
        numero={numReg}
        t={t}
        axios={axios}
        tornarDeDetallRegistreFunc={this.tornarDeDetallRegistre}
      />;
    }

    // XYZ ZZZ
    formulari = (
      <>
        <Form id="fechaBusqueda" style={{ marginBottom: "20px" }} className="ocultarMobil">
          <Container style={{ width: "95%", paddingLeft: "0", margin: "0" }} className="ampleTotalApp">
            <Row>
              <Col className="col-xs-12 mb-3 campFormApp">
                <Form.Group>
                  <Form.Label>{t("registro_numero")}</Form.Label>
                  <Form.Control
                    type="text"
                    id="numero"
                    placeholder="Número"
                    maxLength={25}
                    tabIndex={501}
                    value={this.state.filter_number == null ? "" : this.state.filter_number}
                    onChange={(e) => {
                      this.handleNumberFilterParam(e);
                    }}
                    className="form-control form-control-sm focusIn font1App"
                  />
                </Form.Group>
              </Col>
              <Col className="col-xs-12 mb-3 campFormApp">
                <Form.Group>
                  <Form.Label>{t("registro_fecha_inicio")}</Form.Label>
                  <DatePicker
                    portalId="root-portal"
                    selected={this.state.filter_startDate}
                    onChange={(startDate:Date) => this.handleStartDateFilterParam(startDate)}
                    selectsStart
                    //startDate={this.state.filter_startDate}
                    //endDate={this.state.filter_endDate}
                    name="fechaInicio"
                    id="fechaInicio"
                    dateFormat="dd/MM/yyyy"
                    className="form-control form-control-sm estilCalendar focusIn font1App"
                    locale={this.props.i18n.language}
                    showYearDropdown={true}
                    preventOpenOnFocus={true}
                    popperPlacement="bottom"
                    popperModifiers={{
                      //@ts-ignore
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
                  <Form.Label>{t("registro_fecha_fin")}</Form.Label>
                  <DatePicker
                    portalId="root-portal"
                    selected={this.state.filter_endDate}
                    onChange={(endDate:Date) => this.handleEndDateFilterParam(endDate)}
                    selectsEnd
                    //startDate={this.state.filter_startDate}
                    //endDate={this.state.filter_endDate}
                    minDate={this.state.filter_startDate}
                    name="fechaFin"
                    id="fechaFin"
                    dateFormat="dd/MM/yyyy"
                    className="form-control form-control-sm estilCalendar focusIn font1App"
                    locale={locale}
                    showYearDropdown={true}
                    preventOpenOnFocus={true}
                    popperPlacement="bottom"
                    popperModifiers={{
                      //@ts-ignore
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
                  <Form.Label>{t("registro_estado")}</Form.Label>
                  <Form.Select
                    id="estado"
                    name="estado"
                    className="form-control form-control-sm focusIn font1App"
                    value={this.state.filter_status}
                    tabIndex={504}
                    aria-labelledby="estado"
                    onChange={(e) => {
                      this.handleStateFilterParam(e);
                    }}
                  >
                    <option value="0" className="form-control form-control-sm selectMobil" selected={true}>
                      {t("registro_estado_todos")}
                    </option>
                    <option value="1" className="form-control form-control-sm selectMobil">
                      {t("registro_estado_1")}
                    </option>
                    <option value="2" className="form-control form-control-sm selectMobil">
                      {t("registro_estado_4")}
                    </option>
                    <option value="3" className="form-control form-control-sm selectMobil">
                      {t("registro_estado_10")}
                    </option>
                    <option value="4" className="form-control form-control-sm selectMobil">
                      {t("registro_estado_11")}
                    </option>
                  </Form.Select>
                </Form.Group>
              </Col>
            </Row>
            <Row>
              <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                <div className="alert alert-danger" role="alert" id="errorMsg" />
              </div>
            </Row>
            <Row className="col-md-3 pl-3 row botoFormApp" style={{ zIndex: "4" }}>
              <Button
                type="submit"
                className="btn btn-primary carpeta-btn mt-2"
                onClick={(e) => {
                  this.handleSubmitSearcher(e);
                }}
                tabIndex={505}
              >
                {t("carpeta_buscar")}
              </Button>
            </Row>
          </Container>
        </Form>
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
      /*
      const data = this.state.data;
      

      if (
        this.state.data != null &&
        parseInt(this.state.pagination_total_items, 10) > 0 &&
        this.state.data &&
        typeof this.state.total_items !== undefined &&
        typeof this.state.data !== undefined &&
        this.state.total_items !== 0
      ) {
  

 



       

        this.state.data.map(
          ({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino, estado }, i) => {
            cardRegistres.push(
              <div
                className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                key={i}
                tabIndex={502 + i}
                onClick={(e) => this.handleItemClick(numeroRegistro)}
              >
                <div className="col-sm-1 float-left">
                  <span
                    className="oi oi-book iconaFormApp"
                    title={"" + t("registroDates")}
                    style={{ verticalAlign: "sub" }}
                  />
                </div>
                <div className="col-sm-10 float-right">
                  <p className="card-text pl-1 mt-0 font-weight-bold" style={{ color: "rgb(102, 102, 102)" }}>
                    {numeroRegistro}
                  </p>
                  <p className="card-text pl-1" style={{ color: "rgb(102, 102, 102)" }}>
                    {$.dateFormat(fechaRegistro)}
                  </p>
                  <p className="card-text pl-1" style={{ color: "rgb(102, 102, 102)" }}>
                    {extracto}
                  </p>
                  <p className="card-text pl-1" style={{ color: "rgb(102, 102, 102)" }}>
                    {denominacionDestino}
                  </p>
                  <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{t("registro_estado_" + estado)}</h3>
                </div>
              </div>
            );
          }
        );
        


      }
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
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
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
      /*
      <>
        <div className="titolPaginaApp visioMobil">{this.props.titles[i18n.language]}</div>
        <div className="infoNoMenu">
          {this.state.numeroRegistro == null && (
            <>
              <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
              <div className="col-md-12 border-0 float-left p-0">
                <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>
                <div className="infoNoMenu">
                  <div className="col-md-12 border-0 float-left p-0">
                    {!this.state.formVisible && criteris}
                    {formulari}
                    {this.state.error && (
                      <div className="alert alert-danger hide" role="alert">
                        {this.state.error}
                      </div>
                    )}
                    {this.state.numeroRegistro == null && content}
                  </div>
                </div>
              </div>
              <div className="float-left" style={{ width: "97%", position: "relative" }}>
                {selectRegistres}
                {this.state.numeroRegistro == null && taulaRegistres}
                {this.state.pagination_total_items.toString() === "0" && registresBuid}
                {cardRegistres}
              </div>
              <div className="col-md-12 border-0 float-left p-0" id="botoTornarRegistro" style={{ marginTop: "20px" }}>
                <button
                  type="button"
                  data-toggle="modal"
                  onClick={() => {
                    window.location.href = sessionStorage.getItem("pagTornar");
                    sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"));
                  }}
                  className="botoSuport botoTornauApp"
                  tabIndex="520"
                  aria-labelledby="botoTornarRegistro"
                >
                  {t("reprendreTornar")}
                </button>
              </div>
            </>
          )}
          {this.state.numeroRegistro != null && detallRegistreContainer}
        </div>
      </>
      */
    );
  }
}

export default withTranslation()(Regweb);
