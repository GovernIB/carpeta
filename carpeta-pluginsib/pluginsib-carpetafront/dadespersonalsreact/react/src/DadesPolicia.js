
import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import logoespera from './espera.gif';
import axios from "axios";

/**
 *  @author anadal
 */

class DadesPolicia extends Component {
    constructor(props) {
        super(props);

        this.onShowPipella = this.onShowPipella.bind(this);

        this.canviaEstat = this.canviaEstat.bind(this);

        this.state = {
            isLoaded: false,
            data: null
        };
    }


    canviaEstat() {
        //const restdata = { "error": "", "datosTitular": { "nombre": "LAIA", "apellido1": "FUSTER", "apellido2": "FERRE", "nacionalidad": "ESPAÑA-ESP", "sexo": "M", "datosNacimiento": { "fecha": "19800515", "localidad": "EIVISSA", "provincia": "ILLES BALEARS" }, "nombrePadre": "ANTONIO", "nombreMadre": "ALICIA", "fechaCaducidad": "20180118" } };
        
        var url2 = this.props.pathtodocumentidentitat;
        axios.get(url2).then(res => {

            console.log(" AXIOS OK OK OK OK OK");

            this.setState({
                ...this.state, 
                isLoaded: true,
                data: res.data
            });
        }).catch(function (error) {

            console.log(" AXIOS ERROR ERROR ERROR ");

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


    componentDidMount() {
        console.log("POLICIA DID MOUNT " + this.props.pathtodocumentidentitat);
        this.props.setClick(this.onShowPipella);
    }


    onShowPipella() {
        console.log("POLICIA  Mètode onShowPipella cridat " + this.props.pathtodocumentidentitat);
        const { isLoaded, data } = this.state;

        if (isLoaded) {
            console.log("No carregam res. JA ESTA CARREGAT !!!");
        } else {
            console.log("FENT CRIDADA REST. ESPERA 5 segons !!!");
            setTimeout(this.canviaEstat, 2000);
        }
    }


    render() {
        const { isLoaded, data } = this.state;
        console.log("RENDER POLICIA  URL on atacar " + this.props.pathtodocumentidentitat);
        let resultat;
        if (isLoaded) {
            
            if (data.error) {
                
                return ( <div class="alert alert-danger" role="alert">{data.error}</div>);
            }
        } else {
            
            //return <img src={logoespera} alt="Esperar ..." />;
            
            return <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        }
        
        const { t } = this.props;
        return (
            <div className="infoNoMenu">
                <div className="col-md-12 border-0 float-left p-0">
                    <div className="card">
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item"><label className="lh15" >{t('dadespersonalsNom')}:&nbsp;</label><label
                                className="titol h5">{data.datosTitular.nombre}</label></li>                                
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
                        </ul>
                    </div>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(DadesPolicia);