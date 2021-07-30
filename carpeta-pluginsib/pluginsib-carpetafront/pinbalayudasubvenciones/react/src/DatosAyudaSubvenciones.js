import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';

/**
 *  @author jagarcia
 */

class DatosAyudaSubvenciones extends Component {
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

            // console.log(" AXIOS OK OK OK OK OK", res.data);

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
				
                let alerta;

                if ( data.codigo == '0'){
                    alerta = <div className="alert alert-success" role="alert">
                        {t('pinbalSubvencionesFecha')} {data.fecha} : {t('pinbalSubvencionesCodigo'+data.codigo)}
                    </div>;
                } else if( data.codigo == '1'){
                    alerta = <div className="alert alert-danger" role="alert">
                        {t('pinbalSubvencionesFecha')} {data.fecha} : {t('pinbalSubvencionesCodigo'+data.codigo)}
                    </div>;
                } else {
                    alerta = <div className="alert alert-warning" role="alert">
                        {t('pinbalSubvencionesFecha')} {data.fecha} : {t('pinbalSubvencionesCodigo'+data.codigo)}
                    </div>;
                }

				content = <div>
                    {alerta}
                    <div className="contenedorInfoPersonal mt-2">
                        <dl className="row">
                            <div>
                                <dt className="col-sm-3">{t('pinbalSubvencionesDni')}</dt>
                                <dd className="col-sm-7">{data.dni}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalSubvencionesNom')}</dt>
                                <dd className="col-sm-7">{data.nombre}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalSubvencionesApellido1')}</dt>
                                <dd className="col-sm-7">{data.apellido1}</dd>
                            </div>
                            <div class="mt-3">
                                <dt className="col-sm-3">{t('pinbalSubvencionesApellido2')}</dt>
                                <dd className="col-sm-7">{data.apellido2}</dd>
                            </div>
                        </dl>
                    </div>
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
                        </div>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DatosAyudaSubvenciones);