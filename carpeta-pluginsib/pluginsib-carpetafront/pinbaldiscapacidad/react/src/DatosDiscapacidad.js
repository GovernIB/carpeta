import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

/**
 *  @author jagarcia
 */

class DatosDiscapacidad extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null
        };
    }

    async componentDidMount() {

        const url2 = this.props.pathtoservei;
        await axios.get(url2).then(res => {

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data
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
                content = <div className="alert alert-danger" role="alert">{data.error}</div>;
            } else {

                let alerta;

                if ( data.codigo === '0'){

                    alerta = <div className="alert alert-success" role="alert">
                        {t('pinbalDiscapacidadFecha')} {data.fecha} : {t('pinbalDiscapacidadCodigo'+data.codigo)}
                    </div>

                    content = <div className="ocultarMobil">
                        {alerta}
                        <dl className="row">
                            <div>
                                <dt className="col-sm-3">{t('pinbalDiscapacidadExpediente')}</dt>
                                <dd className="col-sm-7">{data.expediente}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadTipo')}</dt>
                                <dd className="col-sm-7">{data.tiposDiscapacidad}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadGrado')}</dt>
                                <dd className="col-sm-7">{data.gradoDiscapacidad}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadEfectos')}</dt>
                                <dd className="col-sm-7">{data.fechaEfectos}</dd>
                            </div>
                            <div className="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadRevision')}</dt>
                                <dd className="col-sm-7">{data.fechaRevision}</dd>
                            </div>
                            <div className="mt-3 mb-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadValidez')}</dt>
                                <dd className="col-sm-7">{data.validezPermanente}</dd>
                            </div>        
                        </dl>             
                    </div>

                    contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto" tabIndex={510}>
                        <div className="col-sm-1 float-left">
                            <span className="oi oi-bell iconaFormApp" title={t('pinbalDiscapacidadConsulta')} style={{verticalAlign: 'sub'}}/>
                        </div>
                        <div className="col-sm-10 float-right">
                            {alerta}
                            {data.expediente && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadExpediente')}:</b> {data.expediente}</p>}
                            {data.tiposDiscapacidad && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadTipo')}:</b> {data.tiposDiscapacidad}</p>}
                            {data.gradoDiscapacidad && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadGrado')}:</b> {data.gradoDiscapacidad}</p>}
                            {data.fechaEfectos && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadEfectos')}:</b> {data.fechaEfectos}</p>}
                            {data.fechaRevision && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadRevision')}:</b> {data.fechaRevision}</p>}
                            {data.validezPermanente && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}><b>{t('pinbalDiscapacidadValidez')}:</b> {data.validezPermanente}</p>}
                        </div>
                    </div>;

                } else {
                    content = <div className="alert alert-warning ocultarMobil" role="alert">
                            {t('pinbalDiscapacidadFecha')} {data.fecha} : {t('pinbalDiscapacidadCodigo'+data.codigo)}
                        </div>

                    contentApp = <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil wAuto" tabIndex={510}>
                        <div className="col-sm-1 float-left">
                            <span className="oi oi-bell iconaFormApp" title={t('pinbalDiscapacidadConsulta')} style={{verticalAlign: 'sub'}}/>
                        </div>
                        <div className="col-sm-10 float-right">
                            <div className="alert alert-warning" role="alert">
                                {t('pinbalDiscapacidadFecha')} {data.fecha} : {t('pinbalDiscapacidadCodigo'+data.codigo)}
                            </div>
                        </div>
                    </div>;

                }
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
                <div className="col-md-12 border-0 float-left p-0" id="botoTornarDiscapacidad" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarDiscapacidad">{t('pinbalDiscapacidadTornar')}</button>
                </div>
            </div>
            </>
            );
            
    }
}

export default withTranslation()(DatosDiscapacidad);