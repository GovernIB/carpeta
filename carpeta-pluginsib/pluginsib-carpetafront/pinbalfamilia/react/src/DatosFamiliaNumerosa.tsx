import React, { Component } from "react";
import { withTranslation, WithTranslation } from "react-i18next";
import axios from "axios";
import i18n from "./i18n";

/**
 *  @author jagarcia
 */

interface PinbalFamiliaProps extends WithTranslation {
    pathtoservei: string;
    titles: any;
    subtitles: any;
}

type PinbalFamiliaState = {
    isLoaded: boolean;
    data: any;
};

class DatosFamiliaNumerosa extends React.Component<
    PinbalFamiliaProps,
    PinbalFamiliaState
> {
    constructor(props: PinbalFamiliaProps) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
        };

        this.carregaDades = this.carregaDades.bind(this);


    }

    componentDidMount(){
        this.carregaDades();
    }

    carregaDades() {
        const url2 = this.props.pathtoservei;
        axios
            .get(url2)
            .then((res) => {
                this.setState({
                    ...this.state,
                    isLoaded: true,
                    data: res.data,
                });
            })
            .catch((error) => {
                console.log(JSON.stringify(error));
                const restdata = { error: JSON.stringify(error) };
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    ...this.state,
                    isLoaded: true,
                    data: restdata,
                });
            });
    }

    render() {
        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;
        let contentApp;

        if (!isLoaded) {
            content = (
                <div id="carregant" className="loader-container centrat ">
                    <div className="loader" />
                </div>
            );
        } else {
            const data = this.state.data;
            if (data.error) {
                content = (
                    <div className="alert alert-danger" role="alert">
                        {data.error}
                    </div>
                );
            } else {
                let alerta;

                if (data.codigo === "0") {
                    alerta = (
                        <div className="alert alert-success" role="alert">
                            {t("pinbalFamiliaFecha")} {data.fecha} :{" "}
                            {t("pinbalFamiliaCodigo" + data.codigo)}
                        </div>
                    );

                    content = (
                        <div className="ocultarMobil">
                            {alerta}
                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t("pinbalFamiliaNumero")}</dt>
                                    <dd className="col-sm-7">{data.tituloNumeroTitulo}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaCategoria")}</dt>
                                    <dd className="col-sm-7">
                                        {t("pinbalFamiliaCategoria" + data.tituloCategoria)}
                                    </dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaExpedicion")}</dt>
                                    <dd className="col-sm-7">{data.tituloExpedicion}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaCaducidad")}</dt>
                                    <dd className="col-sm-7">{data.tituloCaducidad}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaHijos")}</dt>
                                    <dd className="col-sm-7">{data.tituloNumeroHijos}</dd>
                                </div>
                                <div className="mt-3 mb-3">
                                    <dt className="col-sm-3">
                                        {t("pinbalFamiliaBeneficiarios")}
                                    </dt>
                                    <dd className="col-sm-7">{data.beneficiarios}</dd>
                                </div>
                            </dl>

                            <dl className="row" style={{ borderTop: "1px solid #ececec" }}>
                                <div>
                                    <dt className="col-sm-3">{t("pinbalFamiliaDni")}</dt>
                                    <dd className="col-sm-7">{data.dni}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaNom")}</dt>
                                    <dd className="col-sm-7">{data.nombre}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaApellido1")}</dt>
                                    <dd className="col-sm-7">{data.apellido1}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaApellido2")}</dt>
                                    <dd className="col-sm-7">{data.apellido2}</dd>
                                </div>
                                <div className="mt-3">
                                    <dt className="col-sm-3">{t("pinbalFamiliaTitular")}</dt>
                                    <dd className="col-sm-7">
                                        {t("pinbalFamiliaTitular" + data.tituloTitular)}
                                    </dd>
                                </div>
                            </dl>
                        </div>
                    );

                    contentApp = (
                        <div
                            className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto"
                            tabIndex={510}
                        >
                            <div className="col-sm-1 float-left">
                                <span
                                    className="oi oi-bell iconaFormApp"
                                    title={t("pinbalFamiliaConsulta")}
                                    style={{ verticalAlign: "sub" }}
                                />
                            </div>
                            <div className="col-sm-10 float-right">
                                {alerta}
                                {data.expediente && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadExpediente")}:</b>{" "}
                                        {data.expediente}
                                    </p>
                                )}
                                {data.tiposDiscapacidad && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadTipo")}:</b>{" "}
                                        {data.tiposDiscapacidad}
                                    </p>
                                )}
                                {data.gradoDiscapacidad && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadGrado")}:</b>{" "}
                                        {data.gradoDiscapacidad}
                                    </p>
                                )}
                                {data.fechaEfectos && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadEfectos")}:</b> {data.fechaEfectos}
                                    </p>
                                )}
                                {data.fechaRevision && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadRevision")}:</b>{" "}
                                        {data.fechaRevision}
                                    </p>
                                )}
                                {data.validezPermanente && (
                                    <p
                                        className="card-text pl-1 mt-0"
                                        style={{ color: "rgb(102, 102, 102)" }}
                                    >
                                        <b>{t("pinbalDiscapacidadValidez")}:</b>{" "}
                                        {data.validezPermanente}
                                    </p>
                                )}
                            </div>
                        </div>
                    );
                } else {
                    content = (
                        <div className="alert alert-warning" role="alert">
                            {t("pinbalFamiliaFecha")} {data.fecha} :{" "}
                            {t("pinbalFamiliaCodigo" + data.codigo)}
                        </div>
                    );

                    contentApp = (
                        <div
                            className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto"
                            tabIndex={510}
                        >
                            <div className="col-sm-1 float-left">
                                <span
                                    className="oi oi-bell iconaFormApp"
                                    title={t("pinbalFamiliaConsulta")}
                                    style={{ verticalAlign: "sub" }}
                                />
                            </div>
                            <div className="col-sm-10 float-right">
                                <div className="alert alert-warning" role="alert">
                                    {t("pinbalFamiliaFecha")} {data.fecha} :{" "}
                                    {t("pinbalFamiliaCodigo" + data.codigo)}
                                </div>
                            </div>
                        </div>
                    );
                }
            }
        }

        return (
            <>
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
                                {content}
                                {contentApp}
                            </div>
                        </div>
                    </div>
                    <div
                        className="col-md-12 border-0 float-left p-0"
                        id="botoTornarFamilia"
                        style={{ marginTop: "20px" }}
                    >
                        <button
                            type="button"
                            data-toggle="modal"
                            onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar")??"";
                                sessionStorage.setItem(
                                    "pagTornar",
                                    sessionStorage.getItem("contextPath")??""
                                );
                            }}
                            className="botoSuport botoTornauApp"
                            tabIndex={520}
                            aria-labelledby="botoTornarFamilia"
                        >
                            {t("pinbalFamiliaTornar")}
                        </button>
                    </div>
                </div>
            </>
        );
    }
}

export default withTranslation()(DatosFamiliaNumerosa);
