import React from 'react';
import { WithTranslation, withTranslation } from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

import listaProvinciasMunicipios from './data/localidadesBalearesFiltrados';

/**
 *  @author jagarcia
 */

interface DatosConvivenciaProps extends WithTranslation {
    pathtoservei: string;
    localitats: string;
    titles: any;
    subtitles: any;
}

interface DatosConvivenciaState {
    isLoaded: boolean;
    data: any;
    municipio: string | null;
}


class DatosConvivencia extends React.Component<DatosConvivenciaProps, DatosConvivenciaState> {

    constructor(props: DatosConvivenciaProps) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            municipio: null
        };

        this.handleMunicipio = this.handleMunicipio.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleMunicipio(event: any) {
        this.setState({ municipio: event.target.value });
    }

    handleSubmit(event: any) {

        event.preventDefault();

        const { t } = this.props;

        const url2 = this.props.pathtoservei;

        let formularioVar: HTMLElement | null = document.getElementById("formulario");
        if (formularioVar) {
            formularioVar.classList.add("d-none");
        }

        let carregantVar: HTMLElement | null = document.getElementById("carregant");
        if (carregantVar) {
            carregantVar.classList.remove("d-none");
        }

        const municipiIsValid = (this.state.municipio) ? true : false;

        if (!municipiIsValid) {
            const errorMsg = { "error": t('pinbalConvivenciaValidaMunicipi') }
            this.setState({
                ...this.state,
                isLoaded: true,
                data: errorMsg
            });
            if (carregantVar)
                carregantVar.classList.add("d-none");

            if (formularioVar)
                formularioVar.classList.remove("d-none");
            return false;
        }

        axios.post(url2, 'municipio=' + this.state.municipio).then(res => {

            this.setState({
                ...this.state,
                isLoaded: true,
                data: res.data,
            });

            if (carregantVar)
                carregantVar.classList.add("d-none");

        }).catch(error => {

            console.log(JSON.stringify(error));
            const restdata = { "error": JSON.stringify(error) };
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            this.setState({
                ...this.state,
                isLoaded: true,
                data: restdata
            });
            if(carregantVar)
                carregantVar.classList.add("d-none");
        });

        return false;
    };


    render() {

        const isLoaded = this.state.isLoaded;

        const { t, localitats } = this.props;

        let content;
        let contentApp;

        const buf: Buffer = Buffer.from(this.props.localitats, 'base64');
        const municipis = (localitats) ? JSON.parse(buf.toString()) : listaProvinciasMunicipios.localidades;

        if (!isLoaded) {

            content =
                <form id="formulario" onSubmit={this.handleSubmit} method="GET">
                    <div className="form-group">
                        <label htmlFor="codigoMunicipio">{t('pinbalConvivenciaMunicipioLabel')}</label>
                        <div className="col-md-4 p-0 col-sm-6" style={{ width: '90%' }}>
                            <select name="codigoMunicipio" id="codigoMunicipio" className="form-control" value={this.state.municipio ? this.state.municipio : ""} onChange={this.handleMunicipio}>
                                <option value="">{t('pinbalConvivenciaSelecciona')}</option>
                                {
                                    municipis.map((item: any) => React.createElement('option', { value: item.codigo }, item.nombre))
                                }
                            </select>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary carpeta-btn">{t('pinbalconvivenciaConsultaBtn')}</button>
                </form>

        } else {

            const data = this.state.data;

            if (data.error) {
                content = <>
                    <div className="alert alert-danger" role="alert">{data.error}</div>
                    <form id="formulario" onSubmit={this.handleSubmit} method="GET">
                        <div className="form-group">
                            <label htmlFor="codigoMunicipio">{t('pinbalConvivenciaMunicipioLabel')}</label>
                            <div className="col-md-4 p-0 col-sm-6" style={{ width: '90%' }}>
                                <select name="codigoMunicipio" id="codigoMunicipio" className="form-control" value={this.state.municipio ? this.state.municipio : ""} onChange={this.handleMunicipio}>
                                    <option value="">{t('pinbalConvivenciaSelecciona')}</option>
                                    {
                                        municipis.map((item: any) => React.createElement('option', { value: item.codigo }, item.nombre))
                                    }
                                </select>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-primary carpeta-btn">{t('pinbalconvivenciaConsultaBtn')}</button>
                    </form>
                </>
            } else {

                let alerta;
                let alertaApp;

                if (data.codigo == '0003') {

                    let periodosInscripcionContent = '';
                    data.personas.forEach(
                        (item: any) => {
                            let periodosContent = '';
                            if (item.periodosInscripcion.length > 0) {
                                periodosContent = '<ul style="margin-left:30px;">';
                                item.periodosInscripcion.forEach(
                                    (elemento: any) => periodosContent += `<li>${t('pinbalConvivenciaDesde')} ${elemento.desde} ${elemento.descripcion}</li>`
                                );
                                periodosContent += '</ul>';
                            }
                            periodosInscripcionContent += `<div class="persona" style="border: 1px solid #ccc; padding: 10px 20px; border-radius: 10px; margin-bottom: 10px;">
                                <p><strong>${item.nombre} ${item.apellido1} ${item.apellido2}</strong></p>
                                <p>${item.tipodocumentacion}: ${item.documentacion}</p>
                                <p>${t('pinbalConvivenciaFechaNacimiento')}: ${item.fechanacimiento}</p>
                                ${periodosContent}
                            </div>`
                        }
                    );

                    alerta = <>
                        <div className="alert alert-success" role="alert">
                            {t('pinbalConvivenciaFecha')} {data.fecha} : {t('pinbalConvivenciaCodigo' + data.codigo)}
                        </div>
                        <br />
                        <div className="col-md-12 border-0 float-left p-0">
                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaDistrito')}</dt>
                                    <dd className="col-sm-7">{data.distrito}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaSeccion')}</dt>
                                    <dd className="col-sm-7">{data.seccion}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaHoja')}</dt>
                                    <dd className="col-sm-7">{data.hoja}</dd>
                                </div>
                            </dl>
                            <hr />
                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaVia')}</dt>
                                    <dd className="col-sm-7">{data.tipoVia} {data.via}</dd>
                                </div>
                                {data.numero && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaNumero')}</dt>
                                    <dd className="col-sm-7">{data.numero}</dd>
                                </div>}
                                {data.kmt && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaKmt')}</dt>
                                    <dd className="col-sm-7">{data.kmt}</dd>
                                </div>}
                                {data.bloque && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaBloque')}</dt>
                                    <dd className="col-sm-7">{data.bloque}</dd>
                                </div>}
                                {data.portal && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaPortal')}</dt>
                                    <dd className="col-sm-7">{data.portal}</dd>
                                </div>}
                                {data.escalera && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaEscalera')}</dt>
                                    <dd className="col-sm-7">{data.escalera}</dd>
                                </div>}
                                {data.planta && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaPlanta')}</dt>
                                    <dd className="col-sm-7">{data.planta}</dd>
                                </div>}
                                {data.puerta && <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaPuerta')}</dt>
                                    <dd className="col-sm-7">{data.puerta}</dd>
                                </div>}
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaCodPostal')}</dt>
                                    <dd className="col-sm-7">{data.codPostal}</dd>
                                </div>
                            </dl>
                            <hr />
                            {periodosInscripcionContent && <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaPersonas')}</dt>
                                    <dd className="col-sm-7"><ul dangerouslySetInnerHTML={{ __html: periodosInscripcionContent }}></ul></dd>
                                </div>
                            </dl>}
                            <hr />
                            {data.fechaExpedicion && <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t('pinbalConvivenciaExpedicion')}</dt>
                                    <dd className="col-sm-7">{data.fechaExpedicion}</dd>
                                </div>
                            </dl>}
                        </div>
                    </>

                    alertaApp = <>
                        <div className="alert alert-success" role="alert">
                            {t('pinbalConvivenciaFecha')} {data.fecha} : {t('pinbalConvivenciaCodigo' + data.codigo)}
                        </div>
                        <br />
                        <div className="col-md-12 border-0 float-left p-0">
                            <div className="row">
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaDistrito')}:</b> {data.distrito}</p>
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaSeccion')}:</b> {data.seccion}</p>
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaHoja')}:</b> {data.hoja}</p>
                            </div>
                            <hr />
                            <div className="row">
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaVia')}:</b> {data.tipoVia} {data.via}</p>
                                {data.numero && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaNumero')}:</b> {data.numero}</p>}
                                {data.kmt && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaKmt')}:</b> {data.kmt}</p>}
                                {data.bloque && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaBloque')}:</b> {data.bloque}</p>}
                                {data.portal && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaPortal')}:</b> {data.portal}</p>}
                                {data.escalera && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaEscalera')}:</b> {data.escalera}</p>}
                                {data.planta && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaPlanta')}:</b> {data.planta}</p>}
                                {data.puerta && <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaPuerta')}:</b> {data.puerta}</p>}
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaCodPostal')}:</b> {data.codPostal}</p>
                            </div>
                            <hr />
                            {periodosInscripcionContent && <div className="row">
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaPersonas')}:</b> <ul dangerouslySetInnerHTML={{ __html: periodosInscripcionContent }}></ul></p>
                            </div>}
                            <hr />
                            {data.fechaExpedicion && <div className="row">
                                <p className="card-text pl-1 mt-0" style={{ color: 'rgb(102, 102, 102)' }}><b>{t('pinbalConvivenciaExpedicion')}:</b> {data.fechaExpedicion}</p>
                            </div>}
                        </div>
                    </>

                } else {
                    alerta = <div className="alert alert-warning" role="alert">
                        {t('pinbalConvivenciaFecha')} {data.fecha} : {t('pinbalConvivenciaCodigo' + data.codigo)}
                    </div>;

                    alertaApp = alerta;
                }

                content = <div className="ocultarMobil">
                    {alerta}
                </div>;

                contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto" tabIndex={510}>
                    <div className="col-sm-1 float-left">
                        <span className="oi oi-bell iconaFormApp" title={t('pinbalConvivenciaConsulta')} style={{ verticalAlign: 'sub' }} />
                    </div>
                    <div className="col-sm-10 float-right">
                        {alertaApp}
                    </div>
                </div>;

            }
        }

        let contextPath: string|null = sessionStorage.getItem("contextPath");
        let pagTornar: string|null = sessionStorage.getItem("pagTornar");

        return (<>
            <div className="titolPaginaApp visioMobil">
                {this.props.titles[i18n.language]}
            </div>
            <div className="infoNoMenu">
                <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {content}
                            {contentApp}
                            <div id="carregant" className="loader-container centrat d-none">
                                <div className="loader" />
                            </div>
                            <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: '20px' }}>
                                <button type="button" data-toggle="modal" onClick={() => {
                                    window.location.href = pagTornar ? pagTornar : ""; sessionStorage.setItem("pagTornar", contextPath ? contextPath: "")
                                }} className="botoSuport botoTornauApp" tabIndex={520} aria-labelledby="botoTornarDadesP">{t('pinbalConvivenciaTornar')}</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
        );

    }
}

export default withTranslation()(DatosConvivencia);