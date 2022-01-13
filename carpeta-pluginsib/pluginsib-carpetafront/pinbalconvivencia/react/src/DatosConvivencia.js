import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

import listaProvinciasMunicipios from './data/localidadesBalearesFiltrados';

/**
 *  @author jagarcia
 */

class DatosConvivencia extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            municipio: null
        };

        this.handleMunicipio = this.handleMunicipio.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleMunicipio(event) { 
        this.setState({municipio: event.target.value});
    }

    handleSubmit(event) {

        event.preventDefault();

        const url2 = this.props.pathtoservei;
        const datos = {
            municipio: this.state.municipio
        }

        console.log(datos);

        axios.post(url2, {datos} ).then(res => {

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data,
            });
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
        });

        return false;
    };
    

    render() {

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;

        if (!isLoaded) {
           
             content = 
                    <>
                        <form id="formulario" onSubmit={this.handleSubmit} method="GET">
                            <div className="form-group">
                                <label for="codigoMunicipio">{t('pinbalConvivenciaMunicipioLabel')}</label>
                                <div class="col-md-4 p-0 col-sm-6" style={{width:'90%'}}>
                                    <select name="codigoMunicipio" id="codigoMunicipio" className="form-control"  value={this.state.municipio} onChange={this.handleMunicipio}>
                                        {
                                            listaProvinciasMunicipios.localidades.map( (item) => React.createElement('option', {value: parseInt(item.codmunicipio, 10) }, item.nombremunicipio))
                                        }
                                    </select>
                                </div>
                            </div>
                            <button type="submit" className="btn btn-primary">{t('pinbalconvivenciaConsultaBtn')}</button>
                        </form>
                    </>;

        } else {
        
            const data = this.state.data;
            if (data.error) {  
                content = <div className="alert alert-danger" role="alert">{data.error}</div>;
            } else {
				
                let alerta;

                if ( data.codigo == '0003'){

                    let periodosInscripcionContent; 
                    let periodosInscripcionArray;

                    Object.entries(data.periodosInscripcion).forEach( ([clave,valor]) => periodosInscripcionArray.push(<li>{clave} {valor}</li>) );

                    console.log(periodosInscripcionArray);
                    

                    alerta = <>
                        <div className="alert alert-success" role="alert">
                            {t('pinbalConvivenciaFecha')} {data.fecha} : {t('pinbalConvivenciaCodigo'+data.codigo)}
                        </div>
                        <br/>
                        <div className="col-md-12 border-0 float-left p-0">
                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">Distrito</dt>
                                    <dd className="col-sm-7">{data.distrito}</dd>
                                </div>
                                <div class="mt-3">
                                    <dt className="col-sm-3">Sección</dt>
                                    <dd className="col-sm-7">{data.seccion}</dd>
                                </div>
                                <div class="mt-3">
                                    <dt className="col-sm-3">Hoja</dt>
                                    <dd className="col-sm-7">{data.hoja}</dd>
                                </div>
                            </dl>
                            <hr/>
                            <dl className="row">
                                <div>
                                    <dt className="col-sm-3">Via</dt>
                                    <dd className="col-sm-7">{data.via}</dd>
                                </div>
                                {data.numero && <div>
                                    <dt className="col-sm-3">Número</dt>
                                    <dd className="col-sm-7">{data.numero}</dd>
                                </div>}
                                {data.kmt && <div>
                                    <dt className="col-sm-3">Kmt</dt>
                                    <dd className="col-sm-7">{data.kmt}</dd>
                                </div>}
                                {data.bloque && <div>
                                    <dt className="col-sm-3">Bloque</dt>
                                    <dd className="col-sm-7">{data.bloque}</dd>
                                </div>}
                                {data.portal && <div>
                                    <dt className="col-sm-3">Portal</dt>
                                    <dd className="col-sm-7">{data.portal}</dd>
                                </div>}
                                {data.escalera && <div>
                                    <dt className="col-sm-3">Escalera</dt>
                                    <dd className="col-sm-7">{data.escalera}</dd>
                                </div>}
                                {data.planta && <div>
                                    <dt className="col-sm-3">Planta</dt>
                                    <dd className="col-sm-7">{data.planta}</dd>
                                </div>}
                                {data.puerta && <div>
                                    <dt className="col-sm-3">Puerta</dt>
                                    <dd className="col-sm-7">{data.puerta}</dd>
                                </div>}
                                <div>
                                    <dt className="col-sm-3">Código Postal</dt>
                                    <dd className="col-sm-7">{data.codPostal}</dd>
                                </div>
                            </dl>
                            <hr/>
                            {periodosInscripcionContent && <dl className="row">
                                <div>
                                    <dt className="col-sm-3">Periodos</dt>
                                    <dd className="col-sm-7"><ul>{periodosInscripcionContent}</ul></dd>
                                </div>
                            </dl>}
                        </div>
                    </>
                    ; 
                } else {
                    alerta = <div className="alert alert-warning" role="alert">
                        {t('pinbalConvivenciaFecha')} {data.fecha} : {t('pinbalConvivenciaCodigo'+data.codigo)}
                    </div>;
                }

				content = <div>
                    {alerta}
				</div>;

            }
        }

        
        return (
            <div className="infoNoMenu">
                <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {content}
                            <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: '20px' }}>
                                <button type="button" data-toggle="modal" onClick={() => {
                                    window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                                }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDadesP">{t('tornar')}</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DatosConvivencia);