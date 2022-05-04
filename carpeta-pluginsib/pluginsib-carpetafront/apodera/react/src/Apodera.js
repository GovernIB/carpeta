import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';
import Table from 'react-bootstrap/Table';

/**
 *  @author jpernia
 */

class Apodera extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            dataApoderaments: null,
            dataPoderdant: null,
            total_items: 0,
            error: null
        };

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.nomEstat = this.nomEstat.bind(this);
        this.descripcioEstat = this.descripcioEstat.bind(this);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    nomEstat(estat) {
        let estatNom = i18n.t('apoderaEstadoDesconocido');
        if (estat != null) {
            switch (estat) {
                case "1":
                    estatNom = i18n.t('apoderaEstat1');
                    break;
                case "2":
                    estatNom = i18n.t('apoderaEstat2');
                    break;
                case "3":
                    estatNom = i18n.t('apoderaEstat3');
                    break;
                case "4":
                    estatNom = i18n.t('apoderaEstat4');
                    break;
                case "5":
                    estatNom = i18n.t('apoderaEstat5');
                    break;
                case "6":
                    estatNom = i18n.t('apoderaEstat6');
                    break;
            }
        }
        return estatNom;
    }

    descripcioEstat(estat) {
        let descripcioNom = i18n.t('apoderaEstadoDesconocido');
        if (estat != null) {
            switch (estat) {
                case "1":
                    descripcioNom = i18n.t('apoderaDescripcioEstat1');
                    break;
                case "2":
                    descripcioNom = i18n.t('apoderaDescripcioEstat2');
                    break;
                case "3":
                    descripcioNom = i18n.t('apoderaDescripcioEstat3');
                    break;
                case "4":
                    descripcioNom = i18n.t('apoderaDescripcioEstat4');
                    break;
                case "5":
                    descripcioNom = i18n.t('apoderaDescripcioEstat5');
                    break;
                case "6":
                    descripcioNom = i18n.t('apoderaDescripcioEstat6');
                    break;
            }
        }
        return descripcioNom;
    }

    componentDidMount() {

        const url = this.props.pathtoservei;
        // console.log("url: " + url);
        axios.get(url).then(res => {
            // console.log(" AXIOS OK OK OK OK OK", res.data);
            this.setState({
                ...this.state,
                isLoaded: true,
                dataApoderaments: res.data.apoderaments,
                dataPoderdant: res.data.poderdant,
                total_items: res.data.totalRegistres,
                error: null
            });
        }).catch(function (error) {
            console.log("ERROR: " + JSON.stringify(error));
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                this.setState({
                    dataApoderaments: null,
                    dataPoderdant: null,
                    total_items: 0,
                    error: "error500plugin",
                    isLoaded: true
                });
            } else{
                this.setState({
                    dataApoderaments: null,
                    dataPoderdant: null,
                    total_items: 0,
                    error: JSON.stringify(error).toString(),
                    isLoaded: true
                });
            }

        });

    }

    render() {

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;
        let resultat;
        let taulaApodera;
        var tamanyTaula = { width: '99%'};
        var tamanyData = { width: '120px !important'};

        // console.log("error a RENDERRRRRRRRRRR: " + this.state.error);

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;

            taulaApodera = "";
            resultat = "";

        } else {

            content = "";

            if(this.state.dataPoderdant.personaFisica) {
                let nomPoderdant;
                let llinatge1Poderdant;
                let llinatge2Poderdant;
                if(this.state.dataPoderdant.personaFisica.nombre !== undefined){
                    nomPoderdant = this.state.dataPoderdant.personaFisica.nombre;
                }else{
                    nomPoderdant = "";
                }
                if(this.state.dataPoderdant.personaFisica.apellido1 !== undefined){
                    llinatge1Poderdant = this.state.dataPoderdant.personaFisica.apellido1;
                }else{
                    llinatge1Poderdant = "";
                }
                if(this.state.dataPoderdant.personaFisica.apellido2 !== undefined){
                    llinatge2Poderdant = this.state.dataPoderdant.personaFisica.apellido2;
                }else{
                    llinatge2Poderdant = "";
                }
                resultat = t('apoderaApoderamentsTotals1') + this.state.total_items + t('apoderaApoderamentsTotals2') + t('apoderaApoderamentsTotals3') + nomPoderdant +
                    " " + llinatge1Poderdant + " " + llinatge2Poderdant + " (" + this.state.dataPoderdant.personaFisica.nifNie + ").";
            }else{
                let raoSocialPoderdant;
                if(this.state.dataPoderdant.personaJuridica.razonSocial !== undefined){
                    raoSocialPoderdant = this.state.dataPoderdant.personaJuridica.razonSocial;
                }else{
                    raoSocialPoderdant = "";
                }
                resultat = t('apoderaApoderamentsTotals1') + this.state.total_items + t('apoderaApoderamentsTotals2') + t('apoderaApoderamentsTotals4') + raoSocialPoderdant + " (" + this.state.dataPoderdant.personaJuridica.nif + ").";
            }

            if(this.state.dataApoderaments && typeof (this.state.total_items) !== undefined && typeof (this.state.dataApoderaments) !== undefined && this.state.total_items !== 0) {

                taulaApodera = <>
                    <div>
                        <Table id="tableId" responsive striped bordered hover style={tamanyTaula}>
                            <thead className="table-success">
                            <tr>
                                <th style={tamanyData}>{t('apoderaTipus')}</th>
                                <th>{t('apoderaEstat')}</th>
                                <th>{t('apoderaApoderado')}</th>
                                <th>{t('apoderaVigencia')}</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.dataApoderaments.map(({tipus, estat, apoderado, vigencia}) => {
                                return <>
                                        <tr className="" tabIndex="511">
                                            <td>{tipus}</td>
                                            {/*<td>{this.nomEstat(estat)}</td>*/}
                                            <td>{estat}</td>
                                            <td>{apoderado}</td>
                                            <td>{vigencia}</td>
                                        </tr>
                                        {/*<tr className="" tabIndex="540" style={{display: 'none'}}>*/}
                                        {/*    <td colSpan="4">{this.descripcioEstat()}</td>*/}
                                        {/*</tr>*/}
                                       </>
                            })}
                            </tbody>
                        </Table>
                    </div>
                </>

            } else if(this.state.total_items === 0 && this.state.dataApoderaments !== null) {
                taulaApodera = <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                                  role="alert">{t('apoderaBuid')}</div>
            }

        }

        return (
            <div className="infoNoMenu">
                <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}
                            {content}
                        </div>
                    </div>
                </div>
                <div className="float-left" style={{width: '97%', position: 'relative'}}>
                    {this.state.total_items !== 0 && <div className="contenedorInfoPersonal mt-2 pb-2">
                        {resultat}
                    </div>}
                    {this.state.isLoaded && taulaApodera }
                </div>
                <div className="col-md-12 border-0 float-left p-0" id="botoTornarApodera" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarApodera">{t('apoderaTornar')}</button>
                </div>
            </div>
        );

    }
}

export default withTranslation()(Apodera);