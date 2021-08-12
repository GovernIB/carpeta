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
                            {t('pinbalDiscapacidadFecha')} {data.fecha} : {t('pinbalDiscapacidadCodigo'+data.codigo)}
                        </div>
                        <dl className="row">
                            <div>
                                <dt className="col-sm-3">{t('pinbalDiscapacidadExpediente')}</dt>
                                <dd className="col-sm-7">{data.expediente}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadTipo')}</dt>
                                <dd className="col-sm-7">{data.tiposDiscapacidad}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadGrado')}</dt>
                                <dd className="col-sm-7">{data.gradoDiscapacidad}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadEfectos')}</dt>
                                <dd className="col-sm-7">{data.fechaEfectos}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadRevision')}</dt>
                                <dd className="col-sm-7">{data.fechaRevision}</dd>
                            </div>
                            <div class="mt-3 mb-3">
                                <dt className="col-sm-3">{t('pinbalDiscapacidadValidez')}</dt>
                                <dd className="col-sm-7">{data.validezPermanente}</dd>
                            </div>        
                        </dl>             
                    </>;
                } else {
                    content = 
                    <>
                        <div className="alert alert-warning" role="alert">
                            {t('pinbalDiscapacidadFecha')} {data.fecha} : {t('pinbalDiscapacidadCodigo'+data.codigo)}
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

export default withTranslation()(DatosDiscapacidad);