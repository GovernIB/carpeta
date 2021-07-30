import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

/**
 *  @author jagarcia
 */

class DatosFamiliaNumerosa extends Component {
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

        let content;

        if (!isLoaded) {
           
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
        
            const data = this.state.data;
            if (data.error) {  
                content = <div className="alert alert-danger" role="alert">{data.error}</div>;
            } else {

                const { t } = this.props;

                if ( data.codigo == '0'){
                    content = 
                    <>
                        <div className="alert alert-success" role="alert">
                            {t('pinbalFamiliaFecha')} {data.fecha} : {t('pinbalFamiliaCodigo'+data.codigo)}
                        </div>
                        <dl className="row">
                            <div>
                                <dt className="col-sm-3">{t('pinbalFamiliaNumero')}</dt>
                                <dd className="col-sm-7">{data.tituloNumeroTitulo}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaCategoria')}</dt>
                                <dd className="col-sm-7">{t('pinbalFamiliaCategoria'+data.tituloCategoria)}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaExpedicion')}</dt>
                                <dd className="col-sm-7">{data.tituloExpedicion}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaCaducidad')}</dt>
                                <dd className="col-sm-7">{data.tituloCaducidad}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaHijos')}</dt>
                                <dd className="col-sm-7">{data.tituloNumeroHijos}</dd>
                            </div>
                            <div class="mt-3 mb-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaBeneficiarios')}</dt>
                                <dd className="col-sm-7">{data.beneficiarios}</dd>
                            </div>
                        </dl>           

                        <dl className="row" style={{ borderTop: '1px solid #ececec' }}>
                            <div>
                                <dt className="col-sm-3">{t('pinbalFamiliaDni')}</dt>
                                <dd className="col-sm-7">{data.dni}</dd>
                            </div>    
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaNom')}</dt>
                                <dd className="col-sm-7">{data.nombre}</dd>
                            </div>    
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaApellido1')}</dt>
                                <dd className="col-sm-7">{data.apellido1}</dd>
                            </div>    
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaApellido2')}</dt>
                                <dd className="col-sm-7">{data.apellido2}</dd>
                            </div>    
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalFamiliaTitular')}</dt>
                                <dd className="col-sm-7">{t('pinbalFamiliaTitular'+data.tituloTitular)}</dd>
                            </div> 
                        </dl>             
                    </>;
                } else {
                    content = 
                    <>
                        <div className="alert alert-warning" role="alert">
                            {t('pinbalFamiliaFecha')} {data.fecha} : {t('pinbalFamiliaCodigo'+data.codigo)}
                        </div>
                    </>;
                }
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
                        </div>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DatosFamiliaNumerosa);