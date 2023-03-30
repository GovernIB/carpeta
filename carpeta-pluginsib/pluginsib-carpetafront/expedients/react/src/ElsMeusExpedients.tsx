/**
 * @author anadal
 * @create date 2022-11-16 10:30:40
 * @modify date 2023-02-22 08:04:20
 */

import React, { Component } from "react";
import { WithTranslation, withTranslation } from "react-i18next";
import axios from "axios";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import i18n from "i18next";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
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

type ElsMeusExpedientsState = {
  filter_startDate: Date | undefined;
  filter_endDate: Date | undefined;
  filter_nom: string | undefined;
  filter_estat: string | undefined;
};

class ElsMeusExpedients extends React.Component<ElsMeusExpedientsProps, ElsMeusExpedientsState> {
  constructor(props: ElsMeusExpedientsProps) {
    super(props);

    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    // debug
    //startDateObj.setFullYear(endDateObj.getFullYear() - 3);

    this.state = {
      filter_startDate: startDateObj,
      filter_endDate: endDateObj,
      filter_nom: undefined,
      filter_estat: "E04",
    };

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    this.handleNomFilterParam = this.handleNomFilterParam.bind(this);
    this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
    this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
    this.handleStateFilterParam = this.handleStateFilterParam.bind(this);
  }

  handleSubmitSearcher() {
    const { t } = this.props;

    //let validatFormulari = this.validaFormulari();
    this.validaFormulari();

    // Agafar valor del input text i ficarho a una
    let filtre_nom = this.state.filter_nom;
    let filtre_estat = this.state.filter_estat;
    let filter_startDate = this.state.filter_startDate;
    let filter_endDate = this.state.filter_endDate;

    this.setState({
      filter_startDate: filter_startDate,
      filter_endDate: filter_endDate,
      filter_nom: filtre_nom,
      filter_estat: filtre_estat,
    });
  }

  validaFormulari() {
    const { t } = this.props;

    if (
      (this.state.filter_startDate != null && !this.validaFecha(this.state.filter_startDate)) ||
      (this.state.filter_endDate != null && !this.validaFecha(this.state.filter_endDate))
    ) {
      return false;
    }

    if (
      this.state.filter_startDate != null &&
      this.state.filter_endDate != null &&
      this.state.filter_startDate > this.state.filter_endDate
    ) {
      const labelError = t("sistraDataIniciError");
      $("#errorMsg").html(labelError == null ? "sistraDataIniciError" : labelError);
      $("#errorContainer").removeClass("ocult");
      return false;
    }
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

  handleNomFilterParam(e: string) {
    this.setState({
      ...this.state,
      filter_nom: e,
    });
  }

  handleStartDateFilterParam(e: Date) {
    this.setState({
      ...this.state,
      filter_startDate: e,
    });
  }

  handleEndDateFilterParam(e: Date) {
    this.setState({
      ...this.state,
      filter_endDate: e,
    });
  }

  handleStateFilterParam(e: string) {
    this.setState({
      ...this.state,
      filter_estat: e,
    });
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    let elementsByPage: number = loadData.elementsByPage;

    console.log("ElsMeusExpedients:loadPaginatedData() => Page: " + page + " | elementsByPage: " + elementsByPage);
    console.log("ElsMeusExpedients:loadPaginatedData() => Props: " + this.props);

    let url = this.props.pathtoservei;

    let params: { [key: string]: string | number } = {
      pagina: page,
      elementsperpagina: elementsByPage,
    };

    if (this.state.filter_nom != null) {
      params.filtreNom = this.state.filter_nom;
    }

    if (this.state.filter_startDate != null) {
      params.filtreDataInici = this.formatDate(this.state.filter_startDate);
    }

    if (this.state.filter_endDate != null) {
      params.filtreDataFi = this.formatDate(this.state.filter_endDate);
    }

    if (this.state.filter_estat != null) {
      params.filtreEstat = this.state.filter_estat;
    }

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

    //const { i18n } = this.props;
    const { t } = this.props;

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
          <Row style={{ width: "95%", marginLeft: "-15px", marginRight: "-15px" }} className="ampleTotalApp">
            <Col className="col-xs-12 mb-3 campFormApp">
              <Form.Group>
                <Form.Label>{t("expedient_nom")}</Form.Label>
                <Form.Control
                  type="text"
                  id="nom"
                  placeholder="Nom"
                  maxLength={25}
                  tabIndex={501}
                  value={this.state.filter_nom}
                  onChange={(e) => {
                    this.handleNomFilterParam(e.target.value);
                  }}
                  className="form-control form-control-sm focusIn font1App"
                />
              </Form.Group>
            </Col>
            <Col className="col-xs-12 mb-3 campFormApp">
              <Form.Group>
                <Form.Label>{t("expedient_fecha_inicio")}</Form.Label>
                <DatePicker
                  portalId="root-portal"
                  selected={this.state.filter_startDate}
                  onChange={(startDate: Date) => this.handleStartDateFilterParam(startDate)}
                  selectsStart
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
                <Form.Label>{t("expedient_fecha_fin")}</Form.Label>
                <DatePicker
                  portalId="root-portal"
                  selected={this.state.filter_endDate}
                  onChange={(endDate: Date) => this.handleEndDateFilterParam(endDate)}
                  selectsEnd
                  minDate={this.state.filter_startDate}
                  name="fechaFin"
                  id="fechaFin"
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
                <Form.Label>{t("expedient_estat")}</Form.Label>
                <Form.Select
                  id="estado"
                  name="estado"
                  className="form-control form-control-sm focusIn font1App"
                  value={this.state.filter_estat}
                  tabIndex={504}
                  aria-labelledby="estado"
                  onChange={(e) => {
                    this.handleStateFilterParam(e.target.value);
                  }}
                >
                  <option value="E04" className="form-control form-control-sm selectMobil" selected={true}>
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
                </Form.Select>
              </Form.Group>
            </Col>
          </Row>
          <Row className="col-md-3 pl-3 row botoFormApp" style={{ zIndex: "4", marginBottom: "20px" }}>
            <Button
              type="submit"
              className="btn btn-primary carpeta-btn mt-2"
              onClick={(e) => {
                this.handleSubmitSearcher();
              }}
              tabIndex={505}
            >
              {t("carpeta_buscar")}
            </Button>
          </Row>

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

    date = date.toString().padStart(2, "0");

    month = month.toString().padStart(2, "0");

    return `${date}/${month}/${year}`;
  }
}

export default withTranslation()(ElsMeusExpedients);
