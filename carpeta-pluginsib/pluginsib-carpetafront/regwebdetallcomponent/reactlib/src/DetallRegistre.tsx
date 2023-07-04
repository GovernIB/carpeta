import React from "react";
//import { WithTranslation} from 'react-i18next';
//import axios from "axios";
//import i18n from './i18n';

/**
 *  @author jagarcia
    @author anadal
 */

interface DetallRegistreProps {
  pathtoservei: string;
  numero: string;
  t: any;
  tornarDeDetallRegistreFunc: Function;
  axios: any;
}

type DetallRegistreState = {
  isLoaded: boolean;
  data: any;
  error: string;
};

class DetallRegistre extends React.Component<DetallRegistreProps, DetallRegistreState> {
  constructor(props: DetallRegistreProps) {
    super(props);

    this.state = {
      isLoaded: false,
      data: null,
      error: "",
    };
  }

  componentDidMount() {
    this.setState({
      ...this.state,
      isLoaded: false,
    });

    const url2 = this.props.pathtoservei;

    const params = { numero: this.props.numero };

    //console.log("DetallRegistre:: Cridant a " + url2 + " amb id de registre " + this.props.numero);

    this.props.axios
      .get(url2, { params: params })
      .then((response:any) => {
        this.setState({
          ...this.state,
          isLoaded: true,
          error: "",
        });

        if (response.data.registre != null) {
          this.setState({
            ...this.state,
            data: response.data,
            isLoaded: true,
          });
          // JSON.parse(response.data.registre.replace(/'/g, '"')),
        } else if (response.data.error != null) {
          this.setState({
            ...this.state,
            isLoaded: true,
            error: response.data.error,
          });
        }
      })
      .catch((error:any) => {
        console.log("Error axios", error);
        this.setState({
          ...this.state,
          error: error,
        });
      });
  }

  descarregarAnnex(annexId: any) {
    fetch(this.state.data.urlAnnexe + annexId, {
      method: "GET",
      headers: {
        "Content-Type": "application/pdf",
      },
    })
      .then((response) => response.blob())
      .then((blob) => {
        const url = window.URL.createObjectURL(new Blob([blob]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", `Annex-${annexId}.pdf`);

        document.body.appendChild(link);
        link.click();
        if (link.parentNode != null) {
          link.parentNode.removeChild(link);
        }
      });
  }

  async generarJustificant(url: any) {
    let justificantLoader = document.getElementById("justificantLoader");
    if (justificantLoader) {
      justificantLoader.style.display = "block";
    }

    await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (justificantLoader) {
          justificantLoader.style.display = "none";
        }
        if (data.error != null) this.setState({ ...this.state, error: data.error });
        else {
          this.downloadDoc(data.justificantData, data.justificantFilename);
        }
      });
  }

  t(cadena: string) {
    return cadena;
  }

  downloadDoc(datafile: any, dataName: any) {
    //const {t} = this.props;

    const linkSource = `data:application/pdf;base64,${datafile}`;
    const downloadLink = document.createElement("a");
    const fileName = typeof dataName !== "undefined" && dataName != "" ? `${dataName}.pdf` : `justificant.pdf`;

    downloadLink.href = linkSource;
    downloadLink.download = fileName;

    /*if (typeof window.navigator.msSaveBlob === 'function') {
            window.navigator.msSaveBlob(
              response.data,
              fileName
            );
        } else */ {
      downloadLink.setAttribute("download", fileName);
    }

    downloadLink.appendChild(document.createTextNode(this.props.t("downloadjustificantText")));
    let just = document.getElementById("justificantEnllaz");
    if (just != null) {
      just.append(downloadLink);
    }
    downloadLink.click();
  }

  mostraTooltip() {
    let toolTip = document.getElementById("descripcionTooltip");

    if (toolTip != null) {
      const estiloElemento = toolTip.style.display;
      toolTip.style.display = estiloElemento == "none" ? "block" : "none";
    }
  }

  dateFormat(dateObject: any) {
    var d = new Date(dateObject);
    var day: any = d.getDate();
    var month: any = d.getMonth() + 1;
    var year: any = d.getFullYear();
    var hour: any = d.getHours();
    var minute: any = d.getMinutes();
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
    return day + "/" + month + "/" + year + " " + hour + ":" + minute;
  }

  teRepresentant(interesados: any){
    var teRepresentant: boolean = false;
    interesados.map((item: any) => (
      typeof item.representante !== "undefined" && (
        teRepresentant = true
      )
    ))
    return teRepresentant;
    }

  render() {
    var tornarDeDetallRegistreFunc: any = this.props.tornarDeDetallRegistreFunc;
    console.log("DetallRegistre:: Render ...");
    const isLoaded = this.state.isLoaded;

    // const { t } = this.props;

    let content;
    let interessatTableContent;

    console.log("DetallRegistre:: Render 2");

    if (!isLoaded) {
      console.log("DetallRegistre:: Render !loaded");

      content = (
        <>
          <div id="carregant" className="loader-container centrat ">
            <div className="loader"></div>
          </div>
        </>
      );

      console.log("DetallRegistre:: Render !loaded FINAL");
    } else {
      console.log("DetallRegistre:: Render Registre");

      if (this.state.data != null) {
        let registre = JSON.parse(this.state.data.registre.replace(/'/g, '"'));
        var hasRepresentant: boolean = this.teRepresentant(registre.interesados);

          interessatTableContent = (
                            <div className="row no-gutters align-items-center">
                              <table className="table table-hover table-sm">
                                <thead>
                                  <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">{this.props.t("registro_interesado_nombre")}</th>
                                    <th scope="col">{this.props.t("registro_interesado_documento")}</th>
                                    {hasRepresentant && 
                                      <>
                                      <th scope="col">{this.props.t("registro_interesado_representante")}</th>
                                      <th scope="col">{this.props.t("registro_representante_documento")}</th>
                                      </>
                                    }
                                    <th scope="col">{this.props.t("registro_interesado_tipo")}</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  {registre.interesados.map((item: any, index: number) => (
                                    <tr>
                                      <td>{index + 1}</td>
                                      <td>
                                        {item.interesado.nombre} {item.interesado.apellido1} {item.interesado.apellido2}
                                      </td>
                                      {/*<td>{item.interesado.razonSocial}</td>*/}
                                      <td>{item.interesado.documento}</td>
                                      {hasRepresentant && <>
                                        <td>{item.representante.nombre} {item.representante.apellido1}</td>
                                        <td>{item.representante.documento}</td>
                                        </>
                                      }
                                      <td>
                                        {this.props.t("registro_tipo_interesado_" + item.interesado.tipoInteresado)}
                                      </td>
                                    </tr>
                                  ))}
                                </tbody>
                              </table>
                            </div>
          );

        let solicitaExponeContainer =
          typeof registre.expone != "undefined" || typeof registre.solicita != "undefined" ? (
            <div className="card border-left-carpeta shadow py-2 mb-3 alert">
              <div className="card-body">
                <div className="col mr-2 font15">
                  {typeof registre.expone != "undefined" && (
                    <div>
                      <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">EXPOSA</h3>
                      <p>{registre.expone}</p>
                    </div>
                  )}
                  {typeof registre.solicita != "undefined" && (
                    <div>
                      <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">SOLICITA</h3>
                      <p>{registre.solicita}</p>
                    </div>
                  )}
                </div>
              </div>
            </div>
          ) : (
            ""
          );

        const iconoDescargar = (
          <span
            className="oi oi-data-transfer-download mr-2"
            title={this.props.t("carpeta_descargar") + " " + this.props.t("registro_justificante")}
            aria-hidden="true"
          ></span>
        );


        content = (
          <>
            <h2 className="titol h2 ocultarMobil">
              {this.props.t("registro_titulo_detalle")} {registre.numeroRegistro}
            </h2>
            <div className="col-md-12 border-0 float-left p-0">
              <div className="card-body pl-0 pr-0" style={{ flexFlow: "row wrap", display: "flex" }}>
                <div className="pri-col-deta-reg col-md-6">
                  <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                    <div className="card-body">
                      <div className="row no-gutters align-items-center">
                        <div className="col mr-2 font15">
                          <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">
                            {this.props.t("registro_entrada")}
                          </h3>
                          <dl className="row colorGrisApp">
                            <dt className="col-sm-3 pb-2">{this.props.t("registro_fecha")}</dt>
                            <dd className="col-sm-7">{this.dateFormat(registre.fechaRegistro)}</dd>

                            <dt className="col-sm-3 pb-2">{this.props.t("registro_numero")}</dt>
                            <dd className="col-sm-7">{registre.numeroRegistro}</dd>

                            {registre.denominacionOficinaOrigen && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_oficina")}</dt>
                                <dd className="col-sm-7">{registre.denominacionOficinaOrigen}</dd>
                              </>
                            )}

                            {registre.denominacionDestino && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_destinatario")}</dt>
                                <dd className="col-sm-7">{registre.denominacionDestino}</dd>
                              </>
                            )}

                            {registre.tipoDocumetacionFisica && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_tipo_doc")}</dt>
                                <dd className="col-sm-7">{registre.tipoDocumetacionFisica}</dd>
                              </>
                            )}

                            {registre.extracto && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_extracto")}</dt>
                                <dd className="col-sm-7">{registre.extracto}</dd>
                              </>
                            )}

                            {registre.idioma && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("carpeta_idioma")}</dt>
                                <dd className="col-sm-7">{this.props.t("registro_idioma_" + registre.idioma)}</dd>
                              </>
                            )}

                            {registre.estado && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_estado")}</dt>
                                <dd className="col-sm-7">
                                  {this.props.t("registro_estado_" + registre.estado)}{" "}
                                  <span className="oi oi-info pl-2" onClick={() => this.mostraTooltip()}></span>{" "}
                                  <span id="descripcionTooltip" style={{ display: "none" }}>
                                    {this.props.t("registro_estado_explicacion_" + registre.estado)}
                                  </span>
                                </dd>
                              </>
                            )}

                            <dt className="col-sm-3 pb-2">{this.props.t("registro_presencial")}</dt>
                            <dd className="col-sm-7">{registre.presencial === true ? "Sí" : "No"}</dd>

                            {registre.codigoSia && (
                              <>
                                <dt className="col-sm-3 pb-2">{this.props.t("registro_codigoSia")}</dt>
                                <dd className="col-sm-7">{registre.codigoSia}</dd>
                              </>
                            )}
                          </dl>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                    <div className="card-body">
                      <div className="row no-gutters">
                        <div className="col mr-2 font15 text-center">
                          <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">
                            {this.props.t("registro_justificante")}
                          </h3>

                          <div
                            id="justificantLoader"
                            className="loader-container centrat"
                            style={{
                              display: "none",
                              textAlign: "center",
                              marginBottom: "20px",
                            }}
                          >
                            <div
                              className="loader"
                              style={{
                                width: "40px",
                                height: "40px",
                                margin: "0 auto",
                              }}
                            />
                          </div>

                          {/* typeof(this.state.data.justificante) != 'undefined' && !this.state.data.justificante.confidencial && <button type="button"  onClick={() => this.handlerJustificant(registre)}>{iconoDescargar} {this.props.t('carpeta_descargar')}</button> */}

                          {this.state.data.justificanteUrl !== "" && (
                            <button
                              type="button"
                              className="btn btn-danger"
                              onClick={() => {
                                window.open(this.state.data.justificanteUrl);
                              }}
                            >
                              {iconoDescargar} {this.props.t("carpeta_descargar")}
                            </button>
                          )}

                          {this.state.data.justificanteUrl === "" &&
                            this.state.data.justificantSenseGenerar !== "" &&
                            this.state.error === "" && (
                              <button
                                type="button"
                                className="btn btn-danger"
                                onClick={() => {
                                  this.generarJustificant(this.state.data.urlGeneracioJustificant);
                                }}
                              >
                                {iconoDescargar} {this.props.t("carpeta_descargar")}
                              </button>
                            )}

                          {this.state.data.justificanteId !== "" &&
                            this.state.data.justificantData !== "" &&
                            this.state.error === "" && (
                              <button
                                type="button"
                                className="btn btn-danger"
                                onClick={() =>
                                  this.downloadDoc(this.state.data.justificantData, this.state.data.justificantFileName)
                                }
                              >
                                {iconoDescargar} {this.props.t("carpeta_descargar")}
                              </button>
                            )}

                          {this.state.data.justificanteUrl === "" &&
                            this.state.data.justificantSenseGenerar === "" &&
                            this.state.data.justificanteId === "" &&
                            this.state.data.error !== "" && (
                              <div className="text-center pt-3 text-danger">
                                <span className="oi oi-warning pr-1"> </span>
                                {this.state.data.error}
                              </div>
                            )}

                          <p id="justificantEnllaz" />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="seg-col-deta-reg col-md-5 pr-0">
                <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                      <div className="card-body">
                        <div className="row no-gutters align-items-center">
                          <div className="col mr-2 font15">
                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">
                              {this.props.t("registro_interesados")}
                            </h3>
                  {interessatTableContent}
                  </div>
                        </div>
                      </div>
                    </div>

                  <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                    <div className="card-body">
                      <div className="row no-gutters align-items-center">
                        <div className="col mr-2 font15">
                          <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">
                            {this.props.t("registro_anexos")}
                          </h3>
                          {registre.anexos && registre.anexos.length > 0 && (
                            <table className="table table-hover table-sm colorGrisApp">
                              <thead>
                                <tr>
                                  <th scope="col">#</th>
                                  <th scope="col">{this.props.t("registro_anexo_name")}</th>
                                  <th scope="col">{this.props.t("registro_anexo_validezdocumento")}</th>
                                  <th scope="col">{this.props.t("registro_anexo_file")}</th>
                                </tr>
                              </thead>
                              <tbody>
                                {registre.anexos.map((item: any, index: number) => (
                                  <tr>
                                    <td>{index + 1}</td>
                                    <td>{item.name}</td>
                                    <td>{this.props.t("registro_anexo_validezdocumento_" + item.validezDocumento)}</td>
                                    <td>
                                      {!item.confidencial ? (
                                        item.mime != "" ? (
                                          <button
                                            type="button"
                                            className="d-sm-inline-block btn btn-sm btn-danger shadow-sm"
                                            onClick={() => this.descarregarAnnex(item.fileID)}
                                          >
                                            <span
                                              className="oi oi-data-transfer-download"
                                              title={this.props.t("registro_anexo_descargar") + " " + item.name}
                                              aria-hidden="true"
                                            ></span>
                                          </button>
                                        ) : (
                                          this.props.t("registro_anexo_nodisponible")
                                        )
                                      ) : (
                                        this.props.t("registro_anexo_confidencial")
                                      )}
                                    </td>
                                  </tr>
                                ))}
                              </tbody>
                            </table>
                          )}
                          {registre.anexos && registre.anexos.length < 1 && (
                            <p className="text-center colorGrisApp">{this.props.t("registro_anexos_vacio")}</p>
                          )}
                          {!registre.anexos && (
                            <p className="text-center colorGrisApp">{this.props.t("registro_anexos_vacio")}</p>
                          )}
                          <p className="text-md-left pt-2 colorGrisApp lletraPetitaApp" style={{ fontSize: "small" }}>
                            {this.props.t("registro_anexos_nodisponibles")}
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>

                  {solicitaExponeContainer}
                </div>
              </div>
            </div>

            <div
              className="col-md-12 border-0 float-left p-0"
              id="botoTornarDiscapacidad"
              style={{ marginTop: "20px" }}
            >
              <button
                type="button"
                data-toggle="modal"
                onClick={(e) => {
                  //this.props.tornarDeDetallRegistreFunc
                  //() => {

                  tornarDeDetallRegistreFunc(e);
                  /*
                  window.location.href = sessionStorage.getItem("pagTornar");
                  sessionStorage.setItem(
                    "pagTornar",
                    sessionStorage.getItem("contextPath")
                  );
                  */
                }}
                className="botoSuport botoTornauApp"
                tabIndex={520}
                aria-labelledby="botoTornarDiscapacidad"
              >
                {this.props.t("tornar")}
              </button>
            </div>
          </>
        );
      } else {
        console.log("DetallRegistre:: Render :: Registre Buit...");

        content = (
          <>
            <div></div>
          </>
        );
      }
    }

    console.log("DetallRegistre:: Renderreturn");

    if (this.state.error) {
      return (
        <div className="infoNoMenu" style={{ marginTop: "10px" }}>
          <div className="alert alert-danger" role="alert">
            {this.state.error}
          </div>
        </div>
      );
    }

    return (
      <div className="infoNoMenu" style={{ marginTop: "10px" }}>
        {content}
      </div>
    );
  }
}

export default DetallRegistre;
