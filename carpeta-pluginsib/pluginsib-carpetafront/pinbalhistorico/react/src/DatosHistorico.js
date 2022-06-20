import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

import listaProvinciasMunicipios from './data/localidadesBalearesFiltrados';

/**
 *  @author jagarcia
 */

class DatosHistorico extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            municipio: null,
            anyos: 1
        };

        this.handleMunicipio = this.handleMunicipio.bind(this);
        this.handleAnyos = this.handleAnyos.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    handleAnyos(event){
        this.setState({anyos: event.target.value});
    }

    handleMunicipio(event) { 
        this.setState({municipio: event.target.value});
    }

    handleSubmit(event) {

        event.preventDefault();

        const { t } = this.props;

        const url2 = this.props.pathtoservei;

        document.getElementById("formulario").classList.add("d-none");
        document.getElementById("carregant").classList.remove("d-none");

        const municipiIsValid = (this.state.municipio) ? true : false;

        if(!municipiIsValid){
            const errorMsg = {"error":t('pinbalHistoricoValidaMunicipi')}
            this.setState({
                ...this.state,
                isLoaded: true,
                data: errorMsg
            });
            document.getElementById("carregant").classList.add("d-none");
            document.getElementById("formulario").classList.remove("d-none");
            return false;
        }

        // Si no és un valor númeric, inserim el valor per defecte 1.
        const numeroAnyos = (isNaN(Number(this.state.anyos))) ? 1 : this.state.anyos;

        axios.post(url2, 'municipio='+this.state.municipio+'&anyos='+numeroAnyos).then(res => {

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data,
            });

            document.getElementById("carregant").classList.add("d-none");

        }).catch(function (error) {

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

            document.getElementById("carregant").classList.add("d-none");
        });

        return false;
    };


    

    render() {

        const isLoaded = this.state.isLoaded;

        const { t, localitats } = this.props;

        let content;
        let contentApp;

        const municipis = (localitats) ? JSON.parse(Buffer.from(this.props.localitats, 'base64')) : listaProvinciasMunicipios.localidades;

        if (!isLoaded) {
           
             content =  
                 <form id="formulario" onSubmit={this.handleSubmit} method="GET">
                    <div className="form-group">
                        <label for="codigoMunicipio">{t('pinbalHistoricoMunicipioLabel')}</label>
                        <div className="col-md-4 p-0 col-sm-6" style={{width:'98%'}}>
                            <select name="codigoMunicipio" id="codigoMunicipio" className="form-control"  value={this.state.municipio} onChange={this.handleMunicipio}>
                                <option value="">{t('pinbalHistoricoSelecciona')}</option>
                                {
                                    municipis.map( (item) => React.createElement('option', {value: item.codigo}, item.nombre))
                                }
                            </select>
                        </div>
                    </div>
                    <div className="form-group">
                        <label for="numeroAnyos">{t('pinbalHistoricoAnyosLabel')}</label>
                        <div className="col-md-4 p-0 col-sm-6" style={{width:'98%'}}>
                            <input type="number" id="numeroAnyos" className="form-control"  value={this.state.anyos} onChange={this.handleAnyos} />
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary carpeta-btn">{t('pinbalHistoricoConsultaBtn')}</button>
                </form>;

        } else {
        
            const data = this.state.data;

            if (data.error) {  
                content = 
                    <>
                        <div className="alert alert-danger" role="alert">{data.error}</div>
                        <form id="formulario" onSubmit={this.handleSubmit} method="GET">
                            <div className="form-group">
                                <label for="codigoMunicipio">{t('pinbalHistoricoMunicipioLabel')}</label>
                                <div className="col-md-4 p-0 col-sm-6" style={{width:'98%'}}>
                                    <select name="codigoMunicipio" id="codigoMunicipio" className="form-control"  value={this.state.municipio} onChange={this.handleMunicipio}>
                                        <option value="">{t('pinbalHistoricoSelecciona')}</option>
                                        {
                                            municipis.map( (item) => React.createElement('option', {value: item.codigo}, item.nombre))
                                        }
                                    </select>
                                </div>
                            </div>
                            <div className="form-group">
                                <label for="numeroAnyos">{t('pinbalHistoricoAnyosLabel')}</label>
                                <div className="col-md-4 p-0 col-sm-6" style={{width:'98%'}}>
                                    <input type="number" id="numeroAnyos" className="form-control"  value={this.state.anyos} onChange={this.handleAnyos} />
                                </div>
                            </div>
                            <button type="submit" className="btn btn-primary carpeta-btn">{t('pinbalHistoricoConsultaBtn')}</button>
                        </form>
                    </>
            } else {
				
                let alerta;

                if ( data.codigo == '0003'){

                    let periodosHistoricoContent = '';                    
                    data.historico.forEach(
                        (item) => {
                            periodosHistoricoContent += `
                                <div class="domicilio" style="border: 1px solid #ccc; padding: 10px 20px; border-radius: 10px; margin-bottom: 10px;">
                                    <strong>${t('pinbalHistoricoDesde')} ${item.desde} ${t('pinbalHistoricoHasta')} ${item.hasta}</strong>
                                    <hr>
                                    ${ item.motivoInscripcion[0] && `<dl class="row">
                                    <div>
                                        <dt class="col-sm-3">${t('pinbalHistoricoMotivoInscripcion')}</dt>
                                        <dd class="col-sm-7">${item.motivoInscripcion[0].descripcion}</dd>
                                    </div></dl>`}
                                    ${ item.motivoBaja[0] && `<dl class="row">
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoMotivoBaja')}</dt>
                                            <dd class="col-sm-7">${item.motivoBaja[0].descripcion}</dd>
                                        </div></dl>`}
                                    <dl class="row">
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoDistrito')}</dt>
                                            <dd class="col-sm-7">${item.claveHojaPadronal[0].distrito}</dd>
                                        </div>
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoSeccion')}</dt>
                                            <dd class="col-sm-7">${item.claveHojaPadronal[0].seccion}</dd>
                                        </div>
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoHoja')}</dt>
                                            <dd class="col-sm-7">${item.claveHojaPadronal[0].hoja}</dd>
                                        </div>

                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoProvincia')}</dt>
                                            <dd class="col-sm-7">${item.provinciaRespuesta[0].nombre}</dd>
                                        </div>
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoMunicipio')}</dt>
                                            <dd class="col-sm-7">${item.municipioRespuesta[0].nombre}</dd>
                                        </div>
                                        ${ item.entColectiva[0].nombre && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoEntColectiva')}</dt>
                                            <dd class="col-sm-7">${item.entColectiva[0].nombre}</dd>
                                        </div>` }
                                        ${ item.entSingular[0].nombre && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoEntSingular')}</dt>
                                            <dd class="col-sm-7">${item.entSingular[0].nombre}</dd>
                                        </div>` }
                                        ${ item.nucleo[0].nombre && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoNucleo')}</dt>
                                            <dd class="col-sm-7">${item.nucleo[0].nombre}</dd>
                                        </div>` }
                                      
                                        <div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoVia')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].via[0].tipo} ${item.direccion[0].via[0].nombre}</dd>
                                        </div>
                                        ${ item.direccion[0].numero && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoNumero')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].numero}</dd>
                                        </div>` }
                                        ${ item.direccion[0].kmt && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoKmt')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].kmt}</dd>
                                        </div>` }
                                        ${ item.direccion[0].bloque && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoBloque')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].bloque}</dd>
                                        </div>` } 
                                        ${ item.direccion[0].portal && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoPortal')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].portal}</dd>
                                        </div>` }
                                        ${ item.direccion[0].escalera && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoEscalera')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].escalera}</dd>
                                        </div>` }
                                        ${ item.direccion[0].planta && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoPlanta')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].planta}</dd>
                                        </div>`}
                                        ${ item.direccion[0].puerta && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoPuerta')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].puerta}</dd>
                                        </div>` }
                                        ${ item.direccion[0].codigoPostal && `<div>
                                            <dt class="col-sm-3">${t('pinbalHistoricoCodPostal')}</dt>
                                            <dd class="col-sm-7">${item.direccion[0].codigoPostal}</dd>
                                        </div>` }
                                    </dl>
                                </div>`
                        }
                    );

                    alerta = <>
                        <div className="alert alert-success" role="alert">
                            {t('pinbalHistoricoFecha')} {data.fecha} : {t('pinbalHistoricoCodigo'+data.codigo)}
                        </div>
                        <br/>
                        <div className="col-md-12 border-0 float-left p-0">

                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoNom')}</dt>
                                    <dd className="col-sm-7">{data.nombre}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoApellido1')}</dt>
                                    <dd className="col-sm-7">{data.apellido1}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoApellido2')}</dt>
                                    <dd className="col-sm-7">{data.apellido2}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoFechaNacimiento')}</dt>
                                    <dd className="col-sm-7">{data.fechaNacimiento}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoTipoDocumentacion')}</dt>
                                    <dd className="col-sm-7">{data.tipoDocumentacion}</dd>
                                </div>
                                <div>
                                    <dt className="col-sm-3">{t('pinbalHistoricoDocumentacion')}</dt>
                                    <dd className="col-sm-7">{data.documentacion}</dd>
                                </div>
                            </dl>

                        </div>
                    </>
                    ; 
                } else {
                    alerta = <div className="alert alert-warning" role="alert">
                        {t('pinbalHistoricoFecha')} {data.fecha} : {t('pinbalHistoricoCodigo'+data.codigo)}
                    </div>;
                }

				content = <div className="ocultarMobil">
                    {alerta}
                    <hr/>
                    {periodosHistoricoContent && <div className="domicilis" dangerouslySetInnerHTML={{__html: periodosHistoricoContent}}></div>}
                    {data.fechaExpedicion && <dl className="row">
                        <div>
                            <dt className="col-sm-3">{t('pinbalHistoricoExpedicion')}</dt>
                            <dd className="col-sm-7">{data.fechaExpedicion}</dd>
                        </div>
                    </dl>}
				</div>;

                contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto" tabIndex={510}>
                    <div className="col-sm-1 float-left">
                        <span className="oi oi-bell iconaFormApp" title={t('pinbalHistoricoConsulta')} style={{verticalAlign: 'sub'}}/>
                    </div>
                    <div className="col-sm-10 float-right">
                        {alerta}
                        {periodosHistoricoContent && <div className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}} dangerouslySetInnerHTML={{__html: periodosHistoricoContent}}></div>}
                        {data.fechaExpedicion && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalHistoricoExpedicion')}:</b> {data.fechaExpedicion}</p>}
                    </div>
                </div>;

            }
        }

        
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
                                <div  id="carregant" className="loader-container centrat d-none">
                                    <div className="loader"/>
                                </div>
                                <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: '20px' }}>
                                    <button type="button" data-toggle="modal" onClick={() => {
                                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                                    }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarDadesP">{t('tornar')}</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
            );
            
    }
}

export default withTranslation()(DatosHistorico);