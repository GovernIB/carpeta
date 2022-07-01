import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

/**
 *  @author anadal
 */

class DadesPolicia extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null
        };
    }

    componentDidMount() {
        // console.log("POLICIA DID MOUNT " + this.props.pathtodocumentidentitat);

        const url2 = this.props.pathtodocumentidentitat;
        axios.get(url2).then(res => {

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data
            });
        }).catch(function (error) {

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
        
    }


    

    render() {
        const isLoaded = this.state.isLoaded;

        const { t } = this.props;
    
        let content;
        let contentApp;

        if (!isLoaded) {
           
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
        
            const data = this.state.data;
            if (data.error) {  
                content = <div class="alert alert-danger" role="alert">{data.error}</div>;
            } else {

                var llinatge1;
                var llinatge2;
                if (data.datosTitular.apellido1 == '') {
                    llinatge1="";
                    llinatge2="";
                } else {
                   llinatge1 = <div class="mt-3">
                        <dt className="col-sm-3">{t('dadespersonalsLlinatge1')}</dt>
                        <dd className="col-sm-7">{data.datosTitular.apellido1}</dd>
                    </div>;
                   
                    llinatge2 = <div class="mt-3">
                        <dt className="col-sm-3">{t('dadespersonalsLlinatge2')}</dt>
                        <dd className="col-sm-7">{data.datosTitular.apellido2}</dd>
                    </div>;
                }



                content = <dl className="row ocultarMobil">
                            <div>
                                <dt className="col-sm-3">{t('dadespersonalsNom')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.nombre}</dd>
                            </div>
                            {llinatge1}
                            {llinatge2}
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('nacionalidad')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.nacionalidad}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('sexo')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.sexo}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('datosNacimiento_fecha')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.datosNacimiento[0].fecha}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('datosNacimiento_localidad')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.datosNacimiento[0].localidad}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('datosNacimiento_provincia')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.datosNacimiento[0].provincia}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('nombrePadre')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.nombrePadre}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('nombreMadre')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.nombreMadre}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('fechaCaducidad')}</dt>
                                <dd className="col-sm-7">{data.datosTitular.fechaCaducidad}</dd>
                            </div>
                    </dl>;

                contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil columnReprendre mt-5" tabIndex={510}>
                    <div className="col-sm-1 float-left">
                        <span className="oi oi-bell iconaFormApp" title={t('pinbalPoliciaConsulta')} style={{verticalAlign: 'sub'}}/>
                    </div>
                    <div className="col-sm-10 float-right">
                        {data.datosTitular.nombre && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('dadespersonalsNom')}: </b>{data.datosTitular.nombre} {data.datosTitular.apellido1} {data.datosTitular.apellido2}</p>}
                        {data.datosTitular.nacionalidad && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('nacionalidad')}: </b>{data.datosTitular.nacionalidad}</p>}
                        {data.datosTitular.sexo && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('sexo')}: </b>{data.datosTitular.sexo}</p>}
                        {data.datosTitular.datosNacimiento[0].fecha && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('datosNacimiento_fecha')}: </b>{data.datosTitular.datosNacimiento[0].fecha}</p>}
                        {data.datosTitular.datosNacimiento[0].localidad && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('datosNacimiento_localidad')}: </b>{data.datosTitular.datosNacimiento[0].localidad}</p>}
                        {data.datosTitular.datosNacimiento[0].provincia && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('datosNacimiento_provincia')}: </b>{data.datosTitular.datosNacimiento[0].provincia}</p>}
                        {data.datosTitular.nombrePadre && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('nombrePadre')}: </b>{data.datosTitular.nombrePadre}</p>}
                        {data.datosTitular.nombreMadre && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('nombreMadre')}: </b>{data.datosTitular.nombreMadre}</p>}
                        {data.datosTitular.fechaCaducidad && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('fechaCaducidad')}: </b>{data.datosTitular.fechaCaducidad}</p>}
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
                                </div>
                            </div>
                        </div>
                        <div className="col-md-12 border-0 float-left p-0" id="botoTornarPolicia" style={{ marginTop: '20px' }}>
                            <button type="button" data-toggle="modal" onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                            }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarPolicia">{t('tornar')}</button>
                        </div>
                    </div>
                </>
            );
            
    }
}

export default withTranslation()(DadesPolicia);