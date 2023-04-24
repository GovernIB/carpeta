import React from "react";
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
import i18n from "./i18n";
import Row from "react-bootstrap/Row";
import * as reactdetect from "react-device-detect";
import {
  CarpetaDatePicker,
  CarpetaFormulariDeFiltre,
  CarpetaFormulariDeFiltreItem,
  CarpetaInputText,
  TemplatePageCarpeta,
} from "carpetacommonreactlib";

/**
 *  @author anadal
 *  @author jpernia
 */

interface DatosMatriculaProps extends WithTranslation {
  pathtoservei: string;
  titles: any;
  subtitles: any;
}

type DatosMatriculaState = {
  isLoaded: boolean;
  data: DatosMatriculaRespuesta | null;
  mostrarInfo: boolean;
  error: boolean;
  radioSelectedOption: string;
};

type DatosMatriculaRespuesta = {
  codRespuesta: string;
  fechaProceso: Date;
  alumno: DatosMatriculaAlumno;
  cursoMatriculaVigente: string;
  cursoMatriculaFutura: string;
  error: string;
};

type DatosMatriculaAlumno = {
  nombre: string;
  apellido1: string;
  apellido2: string;
  idTitular: DatosAlumnoIdTitular;
  fechaNacimiento: Date;
};

type DatosAlumnoIdTitular = {
  documentacion: string;
  tipoDocumentacion: string;
};

class DatosMatricula extends React.Component<DatosMatriculaProps, DatosMatriculaState> {
  private documentAutenticat: string | null;
  private documentTitular: string;

  private nomTitular: string;
  private primerLlinatgeTitular: string;
  private segonLlinatgeTitular: string;
  private dataNaixementTitular: Date | null;

  private tipusDocumentTitular: string;

  private tipusDocument: string;

  constructor(props: DatosMatriculaProps) {
    super(props);

    this.state = {
      ...this.state,
      isLoaded: true,
      data: null,
      radioSelectedOption: "option1",
      mostrarInfo: false,
      error: false,
    };

    this.documentAutenticat = sessionStorage.getItem("usuariDNI");
    this.documentTitular = "";
    this.dataNaixementTitular = null;
    this.nomTitular = "";
    this.primerLlinatgeTitular = "";
    this.segonLlinatgeTitular = "";
    this.tipusDocumentTitular = "1";
    this.tipusDocument = "1";

    this.handleChangeDataNaixement = this.handleChangeDataNaixement.bind(this);
    this.handleChangeDocument = this.handleChangeDocument.bind(this);
    this.handleChangeTipusDocument = this.handleChangeTipusDocument.bind(this);
    this.handleChangeNom = this.handleChangeNom.bind(this);
    this.handleChangePrimerLlinatge = this.handleChangePrimerLlinatge.bind(this);
    this.handleChangeSegonLlinatge = this.handleChangeSegonLlinatge.bind(this);
    this.handleChangeRadio = this.handleChangeRadio.bind(this);
    this.mostrarDocument = this.mostrarDocument.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleSubmitSync = this.handleSubmitSync.bind(this);

    this.isToday = this.isToday.bind(this);

    this.dateFormat = this.dateFormat.bind(this);

    this.canviatIdioma = this.canviatIdioma.bind(this);
    i18n.on("languageChanged", this.canviatIdioma);
  }

  componentDidMount() {}

  canviatIdioma() {
    this.forceUpdate();
  }

  handleChangeDataNaixement(newDate: Date, _oldDate: Date): boolean {
    $("#fechaNacimiento").removeClass("borderError");
    this.dataNaixementTitular = newDate;
    return true;
  }

  handleChangeTipusDocument(e: React.ChangeEvent<HTMLSelectElement>) {
    this.tipusDocumentTitular = e.target.value;
  }

  handleChangeDocument(nouValor: string) {
    $("#dni").removeClass("borderError");
    this.documentTitular = nouValor;
  }

  handleChangeNom(nouValor: string) {
    $("#nomTitular").removeClass("borderError");
    this.nomTitular = nouValor; // e.target.value;
  }

  handleChangePrimerLlinatge(nouValor: string) {
    $("#primerLliTitular").removeClass("borderError");
    this.primerLlinatgeTitular = nouValor; //e.target.value;
  }

  handleChangeSegonLlinatge(nouValor: string) {
    $("#segonLliTitular").removeClass("borderError");
    this.segonLlinatgeTitular = nouValor; //e.target.value;
  }

  handleChangeRadio(e: React.ChangeEvent<HTMLSelectElement>) {
    $("#errorMsg").html("");
    $("#errorContainer").addClass("ocult");

    this.documentTitular = "";
    this.dataNaixementTitular = new Date();
    this.nomTitular = "";
    this.primerLlinatgeTitular = "";
    this.segonLlinatgeTitular = "";
    this.tipusDocumentTitular = "1";

    this.setState({
      ...this.state,
      mostrarInfo: false,
      radioSelectedOption: e.target.value,
    });
  }

  mostrarDocument() {
    return this.state.radioSelectedOption === "option1"
      ? this.documentAutenticat
      : this.state.radioSelectedOption === "option2"
      ? this.documentTitular
      : " " +
        this.nomTitular +
        " " +
        this.primerLlinatgeTitular +
        " " +
        this.segonLlinatgeTitular +
        " " +
        this.dateFormat(this.dataNaixementTitular);
  }

  dateFormat(dateObject: Date | null) {
    if (dateObject != null) {
      var d = new Date(dateObject);
      var day: string;
      var month: string;
      var year: string = d.getFullYear().toString();

      if (d.getDate() < 10) {
        day = "0" + d.getDate().toString();
      } else {
        day = d.getDate().toString();
      }

      if (d.getMonth() < 11) {
        month = "0" + (d.getMonth() + 1).toString();
      } else {
        month = (d.getMonth() + 1).toString();
      }

      return day + "/" + month + "/" + year;
    } else {
      return "";
    }
  }

  handleSubmitSync(e: any): boolean {
    console.log("DatosMatricula::handleSubmitSync() ENTRA");

    if (this.validaFormulari()) {
      console.log("DatosMatricula::handleSubmitSync() Validat !!!!");
      e.preventDefault();
      this.handleSubmit();
      return true;
    } else {
      console.log("DatosMatricula::handleSubmitSync() NO Validat !!!!");
      e.preventDefault();
      return false;
    }
  }

  async handleSubmit() {
    console.log("DatosMatricula::handleSubmit() ENTRA");

    this.setState({
      ...this.state,
      isLoaded: false,
    });

    const url2 = this.props.pathtoservei;

    const params = {
      dataNaixementTitular: this.dataNaixementTitular,
      documentTitular: this.documentTitular,
      documentAutenticat: this.documentAutenticat,
      tipusDocumentTitular: this.tipusDocumentTitular,
      nomTitular: this.nomTitular,
      primerLlinatgeTitular: this.primerLlinatgeTitular,
      segonLlinatgeTitular: this.segonLlinatgeTitular,
      radioSelectedOption: this.state.radioSelectedOption,
    };

    await axios
      .get(url2, { params: params })
      .then((res) => {
        this.setState({
          ...this.state,
          isLoaded: true,
          mostrarInfo: true,
          error: false,
        });

        if (res.data != null) {
          this.setState({
            ...this.state,
            data: res.data,
          });
        } else {
          this.setState({
            ...this.state,
            data: null,
          });
        }
      })
      .catch((error) => {
        console.log("Error axios", error);
        this.setState({
          ...this.state,
          error: true,
        });
      });
  }

  validaFormulari() {
    const { t } = this.props;

    console.log("DatosMatricula::() => this.state.radioSelectedOption = " + this.state.radioSelectedOption);

    switch (this.state.radioSelectedOption) {
      case "option2":
        {
          if (this.documentTitular === "") {
            $("#errorMsg").html(t("pinbalMatriculaErrorDocument"));
            $("#errorContainer").removeClass("ocult");
            $("#dni").addClass("borderError");
            return false;
          }
        }
        break;
      case "option3":
        {
          console.log("DatosMatricula::() => this.nomTitular = ]" + this.nomTitular + "[");
          if (this.nomTitular === "") {
            $("#errorMsg").html(t("pinbalMatriculaErrorNom"));
            $("#errorContainer").removeClass("ocult");
            $("#nomTitular").addClass("borderError");
            return false;
          }

          console.log("DatosMatricula::() => this.primerLlinatgeTitular = ]" + this.primerLlinatgeTitular + "[");
          if (this.primerLlinatgeTitular === "") {
            $("#errorMsg").html(t("pinbalMatriculaErrorPrimerLLi"));
            $("#errorContainer").removeClass("ocult");
            $("#primerLliTitular").addClass("borderError");
            return false;
          }

          if (this.dataNaixementTitular === null || this.isToday(this.dataNaixementTitular)) {
            $("#errorMsg").html(t("pinbalMatriculaErrorData"));
            $("#errorContainer").removeClass("ocult");
            $("#fechaNacimiento").addClass("borderError");
            return false;
          }
        }
        break;
    }

    $("#errorMsg").html("");
    $("#errorContainer").addClass("ocult");

    $("#dni").removeClass("borderError");
    $("#nomTitular").removeClass("borderError");
    $("#primerLliTitular").removeClass("borderError");
    $("#fechaNacimiento").removeClass("borderError");

    return true;
  }

  isToday(date: Date): boolean {
    let today = new Date();

    if (today.getDate() == date.getDate()) {
      if (today.getMonth() == date.getMonth()) {
        if (today.getFullYear() == date.getFullYear()) {
          return true;
        }
      }
    }
    return false;
  }

  render() {
    const isLoaded = this.state.isLoaded;

    const { t } = this.props;

    let formulari;
    let content;
    let alerta;

    formulari = (
      <CarpetaFormulariDeFiltre handleSubmitSearcher={this.handleSubmitSync} i18n={this.props.i18n}>
        <>
          <div style={{ marginRight: "15px", marginLeft: "15px" }}>
            <Row className="columnReprendre" style={{ width: "fit-content" }}>
              <CarpetaFormulariDeFiltreItem>
                <select
                  id="radioOpcio"
                  name="radioOpcio"
                  className="form-control form-control-sm focusIn font1App form-select"
                  value={this.state.radioSelectedOption}
                  tabIndex={503}
                  onChange={(e) => {
                    this.handleChangeRadio(e);
                  }}
                >
                  <option value="option1" selected={this.state.radioSelectedOption === "option1"}>
                    {t("pinbalMatriculaDadesPropies")}
                  </option>
                  <option value="option2" selected={this.state.radioSelectedOption === "option2"}>
                    {t("pinbalMatriculaFillDocument")}
                  </option>
                  <option value="option3" selected={this.state.radioSelectedOption === "option3"}>
                    {t("pinbalMatriculaFillData")}
                  </option>
                </select>
              </CarpetaFormulariDeFiltreItem>
            </Row>

            {this.state.radioSelectedOption === "option2" && (
              <Row className="pt-2" style={{ paddingTop: "0.5em" }}>
                <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaTipusDocument")}>
                  <select
                    id="tipusDocument"
                    name="tipusDocument"
                    className="form-control form-control-sm focusIn"
                    defaultValue={this.tipusDocument}
                    tabIndex={504}
                    aria-labelledby="tipoDocum"
                    onChange={(e) => {
                      this.handleChangeTipusDocument(e);
                    }}
                  >
                    <option value="1" selected={this.tipusDocument === "1"}>
                      {t("pinbalMatriculaNIF")}
                    </option>
                    <option value="2" selected={this.tipusDocument === "2"}>
                      {t("pinbalMatriculaNIE")}
                    </option>
                    <option value="3" selected={this.tipusDocument === "3"}>
                      {t("pinbalMatriculaPassaport")}
                    </option>
                    <option value="4" selected={this.tipusDocument === "4"}>
                      {t("pinbalMatriculaComunitari")}
                    </option>
                  </select>
                </CarpetaFormulariDeFiltreItem>

                <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaDNI")}>
                  <CarpetaInputText
                    id="dni"
                    tabIndex={505}
                    defaultValue={this.documentTitular}
                    onChangedText={this.handleChangeDocument}
                  />
                </CarpetaFormulariDeFiltreItem>
              </Row>
            )}
            {this.state.radioSelectedOption === "option3" && (
              <>
                <Row className="pt-2" style={{ paddingTop: "0.5em" }}>
                  <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaNomTitular")}>
                    <CarpetaInputText
                      id="nomTitular"
                      tabIndex={506}
                      defaultValue={this.nomTitular}
                      onChangedText={this.handleChangeNom}
                    />
                  </CarpetaFormulariDeFiltreItem>

                  <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaPrimerLliTitular")}>
                    <CarpetaInputText
                      id="primerLliTitular"
                      tabIndex={507}
                      defaultValue={this.primerLlinatgeTitular}
                      onChangedText={this.handleChangePrimerLlinatge}
                    />
                  </CarpetaFormulariDeFiltreItem>
                  <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaSegonLliTitular")}>
                    <CarpetaInputText
                      id="segonLliTitular"
                      tabIndex={508}
                      defaultValue={this.segonLlinatgeTitular}
                      onChangedText={this.handleChangeSegonLlinatge}
                    />
                  </CarpetaFormulariDeFiltreItem>
                  <CarpetaFormulariDeFiltreItem label={t("pinbalMatriculaNaixement")}>
                    <CarpetaDatePicker
                      basename={"dataNaixementTitular"}
                      defaultValue={this.dataNaixementTitular === null ? new Date() : this.dataNaixementTitular}
                      onChangeDate={this.handleChangeDataNaixement}
                      i18n={this.props.i18n}
                    />
                  </CarpetaFormulariDeFiltreItem>
                </Row>
              </>
            )}
            <Row>
              <div id="errorContainer" className="pb-2 pt-2 ml-3 mr-0 ocult" style={{ width: "100%" }}>
                <div className="alert alert-danger" role="alert" id="errorMsg" />
              </div>
            </Row>
          </div>
        </>
      </CarpetaFormulariDeFiltre>
    );

    if (!isLoaded) {
      alerta = "";
      content = (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );
    } else {
      const data = this.state.data;

      if (this.state.error && data != null) {
        alerta = "";
        content = (
          <div className="alert alert-danger" role="alert">
            {data.error}
          </div>
        );
      } else {
        if (data !== null && this.state.mostrarInfo) {
          if (data.codRespuesta === "0") {
            alerta = (
              <div className="alert alert-success" role="alert">
                <>
                  {t("pinbalMatriculaFecha")} {data.fechaProceso} : {t("pinbalMatriculaCodigoInicio")}{" "}
                  {this.mostrarDocument()} {t("pinbalMatriculaCodigo0")}
                </>
              </div>
            );

            if (!reactdetect.isMobileOnly) {
              // === DESKTOP ===
              let nom;
              let llinatge1;
              let llinatge2;
              let document;
              let dataNaixe;
              let cursVigent;
              let cursFutur;

              if (data.alumno.nombre !== undefined) {
                nom = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaNom")}</dt>
                    <dd className="col-sm-7">{data.alumno.nombre}</dd>
                  </div>
                );
              }
              if (data.alumno.apellido1 !== undefined) {
                llinatge1 = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaApellido1")}</dt>
                    <dd className="col-sm-7">{data.alumno.apellido1}</dd>
                  </div>
                );
              }
              if (data.alumno.apellido2 !== undefined) {
                llinatge2 = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaApellido2")}</dt>
                    <dd className="col-sm-7">{data.alumno.apellido2}</dd>
                  </div>
                );
              }
              if (data.alumno.idTitular.documentacion !== undefined) {
                document = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaDocument")}</dt>
                    <dd className="col-sm-7">
                      {data.alumno.idTitular.tipoDocumentacion} {data.alumno.idTitular.documentacion}
                    </dd>
                  </div>
                );
              }
              if (data.alumno.fechaNacimiento !== undefined) {
                dataNaixe = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaNaixement")}</dt>
                    <dd className="col-sm-7">{data.alumno.fechaNacimiento.toString()}</dd>
                  </div>
                );
              }
              if (data.cursoMatriculaVigente !== undefined) {
                cursVigent = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaVigent")}</dt>
                    <dd className="col-sm-7">{data.cursoMatriculaVigente}</dd>
                  </div>
                );
              }
              if (data.cursoMatriculaFutura !== undefined) {
                cursFutur = (
                  <div className="pt-2">
                    <dt className="col-sm-3">{t("pinbalMatriculaFutura")}</dt>
                    <dd className="col-sm-7">{data.cursoMatriculaFutura}</dd>
                  </div>
                );
              }

              content = (
                <div className="ocultarMobil">
                  {alerta}
                  <div className="contenedorInfoPersonal mt-2">
                    <dl className="row">
                      {nom}
                      {llinatge1}
                      {llinatge2}
                      {document}
                      {dataNaixe}
                      {cursVigent}
                      {cursFutur}
                    </dl>
                  </div>
                </div>
              );
            } else {
              // === MOBILE ====
              content = (
                <div
                  className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto mt-5"
                  tabIndex={510}
                >
                  <div className="col-sm-1 float-left">
                    <span
                      className="oi oi-bell iconaFormApp"
                      title={t("pinbalMatriculaConsulta")}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  <div className="col-sm-10 float-right">
                    {alerta}
                    {data.alumno.nombre && (
                      <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                        <b>{t("pinbalMatriculaNom")}: </b>
                        {data.alumno.nombre} {data.alumno.apellido1} {data.alumno.apellido2}
                      </p>
                    )}
                    {data.alumno.idTitular.documentacion && (
                      <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                        <b>{t("pinbalMatriculaDocument")}: </b>
                        {data.alumno.idTitular.documentacion}
                      </p>
                    )}
                    {data.alumno.fechaNacimiento && (
                      <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                        <>
                          <b>{t("pinbalMatriculaNaixement")}: </b>
                          {data.alumno.fechaNacimiento}
                        </>
                      </p>
                    )}
                    {data.cursoMatriculaVigente && (
                      <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                        <b>{t("pinbalMatriculaVigent")}: </b>
                        {data.cursoMatriculaVigente}
                      </p>
                    )}
                    {data.cursoMatriculaFutura && (
                      <p className="card-text pl-1 mt-0" style={{ color: "rgb(102, 102, 102)" }}>
                        <b>{t("pinbalMatriculaFutura")}: </b>
                        {data.cursoMatriculaFutura}
                      </p>
                    )}
                  </div>
                </div>
              );
            }
          } else {
            if (data.codRespuesta === "") {
              alerta = (
                <div className="alert alert-danger" role="alert">
                  {t("pinbalMatriculaError")} : {data.error}
                </div>
              );
            } else if (data.codRespuesta === "1") {
              alerta = (
                <div className="alert alert-danger" role="alert">
                  <>
                    {t("pinbalMatriculaFecha")} {data.fechaProceso} : {t("pinbalMatriculaCodigoInicio")}{" "}
                    {this.mostrarDocument()} {t("pinbalMatriculaCodigo1")}
                  </>
                </div>
              );
            } else if (data.codRespuesta === "2") {
              alerta = (
                <div className="alert alert-warning" role="alert">
                  <>
                    {t("pinbalMatriculaFecha")} {data.fechaProceso} : {t("pinbalMatriculaCodigo2")}{" "}
                    {this.mostrarDocument()} {t("pinbalMatriculaCodigoFin")}
                  </>
                </div>
              );
            } else if (data.codRespuesta === "3") {
              alerta = (
                <div className="alert alert-warning" role="alert">
                  <>
                    {t("pinbalMatriculaFecha")} {data.fechaProceso} : {t("pinbalMatriculaCodigo3")}{" "}
                    {this.mostrarDocument()} {t("pinbalMatriculaCodigoFin")}
                  </>
                </div>
              );
            }

            if (!reactdetect.isMobileOnly) {
              /*  ============== VERSIO DESKTOP ================= */
              content = <div className="ocultarMobil">{alerta}</div>;
            } else {
              /*  ============== VERSIO MÒBIL =================        */
              content = (
                <div
                  className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto mt-5"
                  tabIndex={510}
                >
                  <div className="col-sm-1 float-left">
                    <span
                      className="oi oi-bell iconaFormApp"
                      title={t("pinbalMatriculaConsulta")}
                      style={{ verticalAlign: "sub" }}
                    />
                  </div>
                  <div className="col-sm-10 float-right">{alerta}</div>
                </div>
              );
            }
          }
        }
      }
    }

    return (
      <TemplatePageCarpeta {...this.props} titles={this.props.titles} subtitles={this.props.subtitles} i18n={i18n}>
        <>
          {formulari}
          {content}
        </>
      </TemplatePageCarpeta>
    );
  }
}

export default withTranslation()(DatosMatricula);
