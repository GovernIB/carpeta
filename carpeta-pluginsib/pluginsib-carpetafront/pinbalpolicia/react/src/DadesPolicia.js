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

        var url2 = this.props.pathtodocumentidentitat;
        axios.get(url2).then(res => {

            // console.log(" AXIOS OK OK OK OK OK");

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data
            });
        }).catch(function (error) {

            // console.log(" AXIOS ERROR ERROR ERROR ");

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
        // console.log("RENDER POLICIA  URL on atacar " + this.props.pathtodocumentidentitat);
        

        let content;

        if (!isLoaded) {
           
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
        
            const data = this.state.data;
            if (data.error) {  
                content = <div class="alert alert-danger" role="alert">{data.error}</div>;
            } else {
                const { t } = this.props;
                content = <ul className="list-group list-group-flush">
                <li className="list-group-item">
                    <label className="lh15" >{t('dadespersonalsNom')}:&nbsp;</label>
                    <label className="titol h5">{data.datosTitular.nombre}</label>
                </li>                                
                <li className="list-group-item"><label
                    className="lh15">{t('dadespersonalsLlinatge1')}:&nbsp;</label><label
                        className="titol h5">{data.datosTitular.apellido1}</label></li>
                <li className="list-group-item"><label
                    className="lh15">{t('dadespersonalsLlinatge2')}:&nbsp;</label><label
                        className="titol h5">{data.datosTitular.apellido2}</label></li>
                <li className="list-group-item"><label className="lh15">{t('nacionalidad')}:&nbsp;</label><label
                    className="titol h5">{data.datosTitular.nacionalidad}</label></li>
                <li className="list-group-item"><label className="lh15">{t('sexo')}:&nbsp;</label>
                    <label className="titol h5">{data.datosTitular.sexo}</label></li>
                <li className="list-group-item"><label className="lh15">{t('datosNacimiento_fecha')}:&nbsp;</label><label
                    className="titol h5">{data.datosTitular.datosNacimiento.fecha}</label></li>
                <li className="list-group-item"><label className="lh15">{t('datosNacimiento_localidad')}:&nbsp;</label><label
                    className="titol h5">{data.datosTitular.datosNacimiento.localidad}</label></li>
                    <li className="list-group-item"><label className="lh15">{t('datosNacimiento_provincia')}:&nbsp;</label><label
                    className="titol h5">{data.datosTitular.datosNacimiento.provincia}</label></li>
                <li className="list-group-item"><label className="lh15">{t('nombrePadre')}:&nbsp;</label>
                    <label className="titol h5">{data.datosTitular.nombrePadre}</label></li>
                <li className="list-group-item"><label className="lh15">{t('nombreMadre')}:&nbsp;</label><label
                    className="titol h5">{data.datosTitular.nombreMadre}</label></li>
                <li className="list-group-item"><label className="lh15">{t('fechaCaducidad')}:&nbsp;</label>
                    <label className="titol h5">{data.datosTitular.fechaCaducidad}</label></li>
            </ul>;
            }
        }

        
        return (
            <div className="infoNoMenu">
                <h2><p className="titol h2">{this.props.titles[i18n.language]}</p></h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            <div className="card">
                                {content}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DadesPolicia);