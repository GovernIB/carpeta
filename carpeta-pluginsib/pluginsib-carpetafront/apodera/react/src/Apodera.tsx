/**
 * @author anadal, jpernia
 * @create date 2022-08-29 08:51:48
 * @modify date 2022-08-29 08:51:48
 */
import React from "react";
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
import i18n from "i18next";
import * as reactdetect from "react-device-detect";
import {
  TemplatePageCarpeta,
  RowTypeUtils,
  RowType,
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
  ReturnPaginationData,
  RenderPaginationTableData,
  PaginationInfo,
  RenderPaginationTable,
} from "carpetacommonreactlib";


interface ApoderaProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

type ApoderaListItem = {
  apoderaTipus: string;
  apoderaAmbit: string;
  apoderaEstat: string;
  apoderaEstatActual?: string;
  apoderaPoderdante: JSX.Element;
  apoderaApoderado: JSX.Element;
  apoderaVigencia: string;
  apoderaBoto?: JSX.Element;
  apoderaOrganismes?: JSX.Element[];
  apoderaProcediments?: JSX.Element[];
  apoderaTipusFila?: JSX.Element;
};

class Apodera extends React.Component<ApoderaProps> {
  private columnsNom: string[];
  private columnsNomAddicionals: string[];
  private columnsTitols: string[];
  private columnsTitolsAddicionals: string[];

  private urlApodera: string = "";

  // Apoderat => 0 || Poderdant => 1
  private filter_vista: number;
  private filter_startDate: Date | null;
  private filter_endDate: Date | null;
  private filter_estat: number;

  private errorEnFiltre: boolean;

  constructor(props: ApoderaProps) {
    super(props);

    {
      this.columnsNom = [
        "apoderaTipus",
        "apoderaAmbit",
        "apoderaEstat",
        "apoderaPoderdante",
        "apoderaApoderado",
        "apoderaVigencia",
      ];

      this.columnsNomAddicionals = ["apoderaEstatActual", "apoderaOrganismes", "apoderaProcediments", "apoderaBoto"];
      this.columnsTitols = [
        i18n.t("apoderaTipus"),
        i18n.t("apoderaAmbit"),
        i18n.t("apoderaEstat"),
        i18n.t("apoderaPoderdante"),
        i18n.t("apoderaApoderado"),
        i18n.t("apoderaVigencia"),
      ];

      this.columnsTitolsAddicionals = [
        i18n.t("apoderaEstatActual"),
        i18n.t("apoderaOrganismes"),
        i18n.t("apoderaProcediments"),
        "", // "apoderaBoto" si es buit no es mostren els ":"
      ];
    }

    const startDateObj = null;
    const endDateObj = null;
    /*
    const startDateObj = new Date();
    const endDateObj = new Date();
    startDateObj.setMonth(endDateObj.getMonth() - 1);

    // XYZ ZZZ
    startDateObj.setFullYear(endDateObj.getFullYear() - 3);
    */

    this.filter_estat = 0; // Tots
    this.filter_vista = 0;
    this.filter_startDate = startDateObj;
    this.filter_endDate = endDateObj;
    this.errorEnFiltre = false;

    this.validate = this.validate.bind(this);

    this.onChangeVista = this.onChangeVista.bind(this);
    this.onChangeEstat = this.onChangeEstat.bind(this);
    /*
    this.onChangeStartDate = this.onChangeStartDate.bind(this);
    this.onChangeEndDate = this.onChangeEndDate.bind(this);
  */
    this.loadPaginatedData = this.loadPaginatedData.bind(this);

    this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
  }

  componentDidMount() {}

  handleSubmitSearcher(e: any): boolean {
    console.log("Apodera::handleSubmitSearcher() inici");

    e.preventDefault();

    if (this.errorEnFiltre) {
      const t = this.props.i18n.t;
      window.alert(t("errorEnFiltre"));
      return false;
    }

    console.log("Apodera::handleSubmitSearcher() final");

    this.forceUpdate();

    return true;
  }
  /*
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
*/
  onChangeVista(novaVista: number) {
    this.filter_vista = novaVista;
  }

  onChangeEstat(nouEstat: number) {
    this.filter_estat = nouEstat;
  }

  validate(startDate: Date, endDate: Date) {
    console.log("Apodera::validate() ... Entra");

    let errorInput = document.getElementById("errorContainer");

    if (startDate.getTime() > endDate.getTime()) {
      console.log("Apodera::validate() error");

      if (errorInput) {
        errorInput.style.display = "block";
      }

      this.errorEnFiltre = true;
      return false;
    } else {
      console.log("Apodera::validate() ok");
      if (errorInput) {
        errorInput.style.display = "none";
      }
      this.errorEnFiltre = false;
      return true;
    }
  }

  loadPaginatedData(loadData: ReturnPaginationData) {
    let page: number = loadData.page;
    //XYZ ZZZ let elementsByPage: number = loadData.elementsByPage;
    console.log("Apodera::loadPaginatedData => ENTRA");

    let url = this.props.pathtoservei + "?lang=" + i18n.language + "&pagina=" + page + "&vista=" + this.filter_vista;

    if (this.filter_estat != 0) {
      url = url + "&estat=" + this.filter_estat;
    }

    if (this.filter_startDate != null) {
      url = url + "&datainici=" + this.filter_startDate.getTime();
    }

    if (this.filter_endDate != null) {
      url = url + "&datafinal=" + this.filter_endDate.getTime();
    }

    console.log("Apodera::loadPaginatedData => CRIDANT A " + url);

    axios
      .get(url)
      .then((response) => {
        this.urlApodera = response.data.urlApodera;

        let paginationInfo: PaginationInfo = {
          paginaActual: response.data.paginacio.paginaActual,
          elementsPerPagina: response.data.paginacio.elementsPerPagina,
          totalPagines: response.data.paginacio.totalPagines,
          elementsRetornats: response.data.paginacio.elementsRetornats,
          totalElements: response.data.paginacio.totalElements,
        };

        let data: RenderPaginationTableData = {
          paginationInfo: paginationInfo,
          tableData: this.processarDadesApoderaments(response.data.apoderaments, response.data.poderdant),
          error: null,
        };

        loadData.returnDataFunction(data);
      })
      .catch((error) => {
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
          errorPantalla = JSON.stringify(error).toString();
        }
        let data: RenderPaginationTableData = {
          paginationInfo: null,
          tableData: null,
          error: errorPantalla,
        };
        loadData.returnDataFunction(data);
      });
  }

  processarDadesApoderaments(apoderamentsRest: any[], dataPoderdant: any): ApoderaListItem[] {
    let apoderaments: ApoderaListItem[] = [];

    const { t } = this.props;

    let accedirLabel: string = t("apoderaAccedirApoderament");

    let isDesktop = !reactdetect.isMobileOnly;

    let nif: string;
    if (dataPoderdant.personaFisica) {
      nif = dataPoderdant.personaFisica.nifNie;
    } else {
      nif = dataPoderdant.personaJuridica.nif;
    }

    let apoderaBotoContent: JSX.Element = (
      <div style={{ width: "auto", float: "right", position: "relative", top: "0px" }} id="accedirApodera">
        <span>
          <button
            className="btn btn-primary carpeta-btn botoAccedirCarpeta"
            title={accedirLabel}
            aria-labelledby="accedirApodera"
            onClick={() => {
              window.open(this.urlApodera, "_blank");
            }}
          >
            {t("apoderaBotoApoderament")}&nbsp;&nbsp;
            {RowTypeUtils.getIcon(RowType.EXTERNAL_LINK, false)}
          </button>
        </span>
      </div>
    );

    {
      apoderamentsRest.map(
        (
          {
            tipus,
            subtipus,
            estatNom,
            estatDesc,
            apoderado,
            poderdante,
            vigencia,
            procediments,
            organismes,
            tramits,
          }: any,
          i: number
        ) => {
          let apoderado2 = this.addNewLine(apoderado, nif, isDesktop);
          let poderdante2 = this.addNewLine(poderdante, nif, isDesktop);

          let apoderaEstatActual: string = estatNom + " - " + estatDesc;

          let element: ApoderaListItem = {
            apoderaTipus: tipus,
            apoderaAmbit: subtipus,
            apoderaEstat: estatNom,
            apoderaEstatActual: apoderaEstatActual,
            apoderaPoderdante: poderdante2,
            apoderaApoderado: apoderado2,
            apoderaVigencia: vigencia,
            apoderaOrganismes: this.list2Html(organismes),
            apoderaProcediments: this.list2Html(procediments),
            apoderaBoto: apoderaBotoContent,
          };

          apoderaments.push(element);
        }
      );
    }

    return apoderaments;
  }

  addBold(str: string, nif: string) {
    if (str.includes(nif)) {
      return <b> {str} </b>;
    } else {
      return str;
    }
  }

  addNewLine(str: string, nif: string, isDesktop: boolean) {
    var index = str.indexOf(" - ");
    var nom = str.substr(0, index);
    var fisicajuridica = str.substr(index + 3);

    var bold;
    if (str.includes(nif)) {
      bold = "bold";
    } else {
      bold = "normal";
    }

    const tabulador = isDesktop ? <></> : <>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</>;
    var tmp = (
      <span style={{ fontWeight: bold }}>
        <p style={{ margin: 0 }}>
          {tabulador} {nom}
        </p>
        <i>
          {tabulador}
          {fisicajuridica}
        </i>
      </span>
    );

    return tmp;
  }

  list2Html(stringList: any): JSX.Element[] {
    let htmlCode: JSX.Element[] = [];
    {
      stringList &&
        stringList.forEach((s: any, x: number) => {
          htmlCode.push(
            <p style={{ margin: 0 }} key={x}>
              - {s}
            </p>
          );
        });
    }
    return htmlCode;
  }

  render() {
    const t = this.props.i18n.t;

    const estatsDisponibles: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 18, 19];
    let options = [];
    let apoderaEstat: string|null = t("apodera.estat");
    let filterVista: string|null = t("filter_vista");
    

    for (let i = 0; i < estatsDisponibles.length; i++) {
      let obj = {
        value: estatsDisponibles[i],
        label: t("apodera.estat.nom." + estatsDisponibles[i]),
      };      
      options.push(obj);
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
            {/*}
            <CarpetaFormulariDeFiltreItem label={t("filter_datainici")}>
              <CarpetaDatePicker
                basename={"dataInici"}
                defaultValue={this.filter_startDate}
                onChangeDate={this.onChangeStartDate}
                i18n={this.props.i18n}
              />
            </CarpetaFormulariDeFiltreItem>
            <CarpetaFormulariDeFiltreItem label={t("filter_datafi")}>
              <CarpetaDatePicker
                basename={"dataFi"}
                defaultValue={this.filter_endDate}
                onChangeDate={this.onChangeEndDate}
                i18n={this.props.i18n}
              />

            </CarpetaFormulariDeFiltreItem>    */}
            {/* XYZ ZZZ Falta tipus apoderament
            <CarpetaFormulariDeFiltreItem label={t("registro_estado")}>
              <select
                id="estado"
                name="estado"
                className="form-control form-control-sm focusIn font1App form-select"
                tabIndex={504}
                aria-labelledby="estado"
                onChange={(e) => {
                  this.onChangeStartDate(e);
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
            </CarpetaFormulariDeFiltreItem>
              */}

            
            <CarpetaFormulariDeFiltreItem label={apoderaEstat != null ? apoderaEstat:""}>
              <>
                <select
                  id="estat"
                  name="estat"
                  className="form-control form-control-sm focusIn font1App form-select"
                  tabIndex={504}
                  aria-labelledby="estat"
                  onChange={(e) => {
                    this.onChangeEstat(parseInt(e.target.value));
                  }}
                  defaultValue={this.filter_estat}
                >
                  <option key={0} value="0" className="form-control form-control-sm" >
                    {t("apodera.estat.qualsevol")}
                  </option>
                  {options.map(({ value, label }, index) => (
                    <option
                      key={value}
                      value={value}
                      className="form-control form-control-sm"                      
                    >
                      {label}
                    </option>
                  ))}
                </select>
              </>
            </CarpetaFormulariDeFiltreItem>

            <CarpetaFormulariDeFiltreItem label={filterVista != null ? filterVista : ""}>
              <>
                <select
                  id="vista"
                  name="vista"
                  className="form-control form-control-sm focusIn font1App form-select"
                  tabIndex={504}
                  aria-labelledby="vista"
                  onChange={(e) => {
                    this.onChangeVista(parseInt(e.target.value));
                  }}
                >
                  <option value="0" className="form-control form-control-sm" selected={this.filter_vista == 0}>
                    {t("vista_apoderat")}
                  </option>
                  <option value="1" className="form-control form-control-sm" selected={this.filter_vista == 1}>
                    {t("vista_poderdant")}
                  </option>
                </select>
              </>
            </CarpetaFormulariDeFiltreItem>
          </>
        </CarpetaFormulariDeFiltre>
      </>
    );

    return (
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        <>
          {formulari}
          <RenderPaginationTable
            loadPaginatedData={this.loadPaginatedData}
            selectElementsByPage={[200]}
            columnNames={this.columnsNom}
            columnTitles={this.columnsTitols}
            columnNamesAdditionals={this.columnsNomAddicionals}
            columnTitlesAdditionals={this.columnsTitolsAddicionals}
            rowType={RowType.SHOW_ADDITIONAL_INFO}
            i18n={i18n}
          />
        </>
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(Apodera);
