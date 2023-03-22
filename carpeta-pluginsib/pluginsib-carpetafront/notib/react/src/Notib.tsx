import React from "react";
import { WithTranslation, withTranslation } from "react-i18next";

import i18n from "./i18n";
import axios from "axios";

import {
    PaginationInfo,
    RenderPaginationTable,
    RenderPaginationTableData,
    ReturnPaginationData,
    TemplatePageCarpeta,
    CarpetaDatePicker,
    CarpetaFormulariDeFiltre,
    CarpetaFormulariDeFiltreItem,
    RowTypeUtils,
    RowType,
} from "carpetacommonreactlib";

/**
 *  @author jpernia
 */

interface NotibProps extends WithTranslation {
    pathtoservei: string;
    titles: any;
    subtitles: any;
    pathtoserveiPendientesUrl: string;
    pathtoserveiLeidasUrl: string;
    notificacionesTodasUrl: string;
    notificacionesPendientesUrl: string;
    notificacionesLeidasUrl: string;
    comunicacionesTodasUrl: string;
    comunicacionesPendientesUrl: string;
    comunicacionesLeidasUrl: string;
}

interface NotibData {
    dataEnviament: string;
    tipus: string;
    concepte: string;
    estat: string;
    emissor: string;
    organGestor: string;
    procediment: string;
    descripcio: string;
    darreraModificacio: string;
    subestat: string;
    notibBoto?: JSX.Element;
}


class Notib extends React.Component<NotibProps> {


    private dataInici: Date;
    private dataFi: Date;
    private filter_type: number;
    private filter_status: number;
    private errorEnFiltre: boolean;


    constructor(props: NotibProps) {
        super(props);

        let startDateObj = new Date();

        const endDateObj = new Date();
        startDateObj.setMonth(endDateObj.getMonth() - 1);
        startDateObj.setFullYear(endDateObj.getFullYear() - 3);


        this.dataInici = startDateObj;
        this.dataFi = endDateObj;
        this.filter_type = 0;
        this.filter_status = 0;
        this.errorEnFiltre = false;

        this.handleChangeDataInici = this.handleChangeDataInici.bind(this);
        this.handleChangeDataFi = this.handleChangeDataFi.bind(this);
        this.handleTypeFilterParam = this.handleTypeFilterParam.bind(this);
        this.handleStatusFilterParam = this.handleStatusFilterParam.bind(this);
        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
        this.loadPaginatedData = this.loadPaginatedData.bind(this);


        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on("languageChanged", this.canviatIdioma);
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

    onChangeStartDate(newDate: Date, _oldDate: Date): boolean {
        if (this.validate(newDate, this.dataFi)) {
            this.dataInici = newDate;
            return true;
        } else {
            return false;
        }
    }

    onChangeEndDate(newDate: Date, _oldDate: Date): boolean {
        if (this.validate(this.dataInici, newDate)) {
            this.dataFi = newDate;
            return true;
        } else {
            return false;
        }
    }

    canviatIdioma(_lng: string) {
        this.forceUpdate();
    }

    handleChangeDataInici(newDate: Date, _oldDate: Date): boolean {
        if (this.validate(newDate, this.dataFi)) {
            this.dataInici = newDate;
            return true;
        } else {
            return false;
        }
    }
    handleChangeDataFi(newDate: Date, _oldDate: Date): boolean {

        if (this.validate(this.dataInici, newDate)) {
            this.dataFi = newDate;
            return true;
        } else {
            return false;
        }
    }

    handleTypeFilterParam(type: number) {
        this.filter_type = type;
    }

    handleStatusFilterParam(status: number) {
        this.filter_status = status;
    }

    loadPaginatedData(loadData: ReturnPaginationData) {
        let page: number = loadData.page;
        let elementsByPage: number = loadData.elementsByPage;

        let url3: string = "";
        if (this.filter_type === 0) {
            if (this.filter_status === 0) {
                url3 = this.props.pathtoservei;
            } else if (this.filter_status === 1) {
                url3 = this.props.pathtoserveiPendientesUrl;
            } else if (this.filter_status === 2) {
                url3 = this.props.pathtoserveiLeidasUrl;
            }
        } else if (this.filter_type === 1) {
            if (this.filter_status === 0) {
                url3 = this.props.notificacionesTodasUrl;
            } else if (this.filter_status === 1) {
                url3 = this.props.notificacionesPendientesUrl;
            } else if (this.filter_status === 2) {
                url3 = this.props.notificacionesLeidasUrl;
            }
        } else if (this.filter_type === 2) {
            if (this.filter_status === 0) {
                url3 = this.props.comunicacionesTodasUrl;
            } else if (this.filter_status === 1) {
                url3 = this.props.comunicacionesPendientesUrl;
            } else if (this.filter_status === 2) {
                url3 = this.props.comunicacionesLeidasUrl;
            }
        }

        const params = {
            dataInici: this.dataInici,
            dataFi: this.dataFi,
            registrosPorPagina: elementsByPage,
            pageNumber: page,
            tipo: this.filter_type,
            estado: this.filter_status,
        };
        if (url3) {

            axios
                .get(url3, { params: params })
                .then((response) => {
                    if (response.data != null) {
                        let totalPagines = Math.floor(response.data.totalRegistres / elementsByPage) + ((response.data.totalRegistres % elementsByPage == 0) ? 0 : 1);
                        let paginationInfo: PaginationInfo = {
                            paginaActual: page,
                            elementsPerPagina: elementsByPage,
                            totalPagines: totalPagines,
                            elementsRetornats: response.data.registresPagina,
                            totalElements: response.data.totalRegistres,
                        };

                        let notibDades = this.processarDadesApoderaments(
                            response.data.comunicacions,
                            response.data.urldetallbase,
                            response.data.urldetallbase2,
                            response.data.urldetallbase3
                        );

                        let data: RenderPaginationTableData = {
                            paginationInfo: paginationInfo,
                            tableData: notibDades,
                            error: null,
                        };
                        loadData.returnDataFunction(data);

                    } else {
                        let data: RenderPaginationTableData = {
                            paginationInfo: null,
                            tableData: null,
                            error: "Les dades retornades son buides",
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
                    if (
                        JSON.stringify(error)
                            .toString()
                            .includes("Request failed with status code 500")
                    ) {
                        errorPantalla = error.response.data.replace(
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
    }

    processarDadesApoderaments(
        dataComunicacions: any,
        urldetallbase: string,
        urldetallbase2: string,
        urldetallbase3: string
    ): NotibData[] {
        let apoderaments: NotibData[] = [];

        const { t } = this.props;

        {
            dataComunicacions.map((obj: any, i: number) => {
                let tipus: string = obj.tipus;
                let transmissio: any = obj.transmissio;
                let tipusStr: string =
                    tipus === "notificacio"
                        ? i18n.t("notibNotificacio")
                        : i18n.t("notibComunicacio");

                //TODO XYZ Substituir ternaris per Ifs

                let strAux: string = urldetallbase
                    ? urldetallbase
                    : "";
                let strAux2: string = urldetallbase2
                    ? urldetallbase2
                    : "";
                let strAux3: string = urldetallbase3
                    ? urldetallbase3
                    : "";
                let urlNotib: string =
                    tipus === "notificacio"
                        ? transmissio.estat.codi === "FINALIZADA" ||
                            transmissio.estat.codi === "FINALITZADA" ||
                            transmissio.estat.codi === "PROCESADA" ||
                            transmissio.estat.codi === "PROCESSADA"
                            ? strAux2
                            : strAux
                        : strAux3;

                let apoderaBotoContent: JSX.Element = (
                    <div
                        style={{
                            width: "auto",
                            float: "right",
                            position: "relative",
                            top: "0px",
                        }}
                        id="accedirApodera"
                    >
                        <span>
                            <button
                                className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                title={t("notibAccedirComunicacio")}
                                tabIndex={511 + i * 2}
                                aria-labelledby="accedirNotib"
                                onClick={() => {
                                    window.open(urlNotib, "_blank");
                                }}
                            >
                                {t("apoderaBotoApoderament")}&nbsp;&nbsp;
                                {RowTypeUtils.getIcon(RowType.EXTERNAL_LINK, false)}
                            </button>
                        </span>
                    </div>
                );

                let element: NotibData = {
                    dataEnviament: this.dateFormat(transmissio.dataEnviament),
                    tipus: tipusStr,
                    concepte: transmissio.concepte,
                    estat: transmissio.estat.codi,
                    emissor: transmissio.emisor,
                    organGestor: transmissio.organGestor.nom,
                    procediment: transmissio.procediment.codi,
                    descripcio: transmissio.descripcio,
                    darreraModificacio: transmissio.dataEstat,
                    subestat: transmissio.subestat,
                    notibBoto: apoderaBotoContent,
                };

                apoderaments.push(element);
            });
        }

        return apoderaments;
    }


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

    componentDidMount() {

    }

    validaFecha(date: string) {
        if (isNaN(Date.parse(date))) {
            $("#errorMsg").html("Error de data");
            $("#errorContainer").removeClass("ocult");
            return false;
        } else {
            $("#errorContainer").addClass("ocult");
            return true;
        }
    }


    dateOrder = function (dateObject: string) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var hour = d.getHours();
        var minute = d.getMinutes();
        if (day < 10) {
            day = 0 + day;
        }
        if (month < 10) {
            month = 0 + month;
        }
        if (hour < 10) {
            hour = 0 + hour;
        }
        if (minute < 10) {
            minute = 0 + minute;
        }
        return (
            year.toString() +
            month.toString() +
            day.toString() +
            hour.toString() +
            minute.toString()
        );
    };

    dateFormat = function (dateObject: string) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var hour = d.getHours();
        var minute = d.getMinutes();
        if (day < 10) {
            day = 0 + day;
        }
        if (month < 10) {
            month = 0 + month;
        }
        if (hour < 10) {
            hour = 0 + hour;
        }
        if (minute < 10) {
            minute = 0 + minute;
        }
        return day + "/" + month + "/" + year + " " + hour + ":" + minute;
    };

    dateFormatCerca = function (dateObject: Date) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        if (day < 10) {
            day = 0 + day;
        }
        if (month < 10) {
            month = 0 + month;
        }
        return day + "-" + month + "-" + year;
    };

    render() {
        //const isLoaded = this.state.isLoaded;

        const { t } = this.props;

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
                        <CarpetaFormulariDeFiltreItem label={t("notibDataInici")}>
                            <CarpetaDatePicker
                                basename={"dataInici"}
                                defaultValue={this.dataInici}
                                onChangeDate={this.onChangeStartDate}
                                i18n={this.props.i18n}
                            />
                        </CarpetaFormulariDeFiltreItem>
                        <CarpetaFormulariDeFiltreItem label={t("notibDataFi")}>
                            <CarpetaDatePicker
                                basename={"dataFi"}
                                defaultValue={this.dataFi}
                                onChangeDate={this.onChangeEndDate}
                                i18n={this.props.i18n}
                            />
                        </CarpetaFormulariDeFiltreItem>
                        <CarpetaFormulariDeFiltreItem label={t("notibTipus")}>
                            <select
                                id="tipo"
                                name="tipo"
                                className="form-control form-control-sm focusIn font1App form-select"
                                tabIndex={504}
                                aria-labelledby="tipo"
                                onChange={(e) => {
                                    this.handleTypeFilterParam(parseInt(e.target.value));
                                }}
                            >
                                <option
                                    value="0"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_type == 0}
                                >
                                    {t("notibTotes")}
                                </option>
                                <option
                                    value="1"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_type == 1}
                                >
                                    {t("notibNotificacions")}
                                </option>
                                <option
                                    value="2"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_type == 2}
                                >
                                    {t("notibComunicacions")}
                                </option>
                            </select>
                        </CarpetaFormulariDeFiltreItem>
                        <CarpetaFormulariDeFiltreItem label={t("notibComunicacionEstat")}>
                            <select
                                id="estado"
                                name="estado"
                                className="form-control form-control-sm focusIn font1App form-select"
                                tabIndex={504}
                                aria-labelledby="estado"
                                onChange={(e) => {
                                    this.handleStatusFilterParam(parseInt(e.target.value));
                                }}
                            >
                                <option
                                    value="0"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_status == 0}
                                >
                                    {t("notibTotes")}
                                </option>
                                <option
                                    value="1"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_status == 1}
                                >
                                    {t("notibPendents")}
                                </option>
                                <option
                                    value="2"
                                    className="form-control form-control-sm selectMobil"
                                    selected={this.filter_status == 2}
                                >
                                    {t("notibLlegides")}
                                </option>
                            </select>
                        </CarpetaFormulariDeFiltreItem>
                    </>
                </CarpetaFormulariDeFiltre>
            </>
        );








        const columnsNom = ["dataEnviament", "tipus", "concepte", "estat"];

        const columnsTitols = [
            i18n.t("notibComunicacionFecha"),
            i18n.t("notibTipus"),
            i18n.t("notibComunicacionConcepte"),
            i18n.t("notibComunicacionEstat")

        ];

        const columnsNomAddicionals = ["emissor", "organGestor", "procediment", "descripcio", "darreraModificacio", "subestat", "notibBoto"];

        const columnsTitolsAddicionals = [
            i18n.t("notibComunicacionEmissor"),
            i18n.t("notibComunicacionOrgano"),
            i18n.t("notibProcediment"),
            i18n.t("notibDescripcioNotificacio"),
            i18n.t("notibDarreraModificacio"),
            i18n.t("notibSubestat"),
            "" //Boto link a notib
        ];

        return (


            <TemplatePageCarpeta titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
                <>
                    {formulari}
                    <RenderPaginationTable
                        loadPaginatedData={this.loadPaginatedData}
                        columnNames={columnsNom}
                        columnTitles={columnsTitols}
                        columnNamesAdditionals={columnsNomAddicionals}
                        columnTitlesAdditionals={columnsTitolsAddicionals}
                        mobileIcon={"oi-envelope-closed"}
                        i18n={i18n}
                    />
                </>
            </TemplatePageCarpeta>



        );
    }
}



/*{
  <div className="titolPaginaApp visioMobil">
    {this.props.titles[i18n.language]}
  </div>
  <div className="infoNoMenu">
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
      {taulaNotib}
      {cardNotificacions}
    </div>
    <div
      className="col-md-12 border-0 float-left p-0"
      id="botoTornarNotib"
      style={{ marginTop: "20px" }}
    >
      <button
        type="button"
        data-toggle="modal"
        onClick={() => {
          {
          }
          window.location.href = sessionStorage.getItem("pagTornar");
          sessionStorage.setItem("pagTornar",sessionStorage.getItem("contextPath"));
        }}
        className="botoSuport botoTornauApp"
        tabIndex={520}
        aria-labelledby="botoTornarNotib"
      >
        {t("notibTornar")}
      </button>
    </div>
  </div>*/


export default withTranslation()(Notib);
