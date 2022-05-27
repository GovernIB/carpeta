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
            error: null,
            urlApodera: null
        };

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.nomEstat = this.nomEstat.bind(this);
        this.descripcioEstat = this.descripcioEstat.bind(this);
        this.mostrarMesInfo = this.mostrarMesInfo.bind(this);

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

    mostrarMesInfo(row) {
        if(document.getElementById(row).style.display === "none" ) {
            document.getElementById(row).style.display = "table-row";
        } else if( document.getElementById(row).style.display === "table-row" ) {
            document.getElementById(row).style.display = "none";
        }
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

        axios.get(url).then(res => {
            this.setState({
                ...this.state,
                isLoaded: true,
                dataApoderaments: res.data.apoderaments,
                dataPoderdant: res.data.poderdant,
                total_items: res.data.totalRegistres,
                error: null,
                urlApodera: res.data.urlApodera
            });
        }).catch(error => {
            // console.log("ERROR: " + JSON.stringify(error));
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                this.setState({
                    isLoaded: true,
                    dataApoderaments: null,
                    dataPoderdant: null,
                    total_items: 0,
                    error: "error500plugin",
                    urlApodera: null
                });
            } else{
                this.setState({
                    dataApoderaments: null,
                    dataPoderdant: null,
                    total_items: 0,
                    error: JSON.stringify(error).toString(),
                    isLoaded: true,
                    urlApodera: null
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
        let cardApoderaments = [];

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;

            taulaApodera = "";
            resultat = "";

        } else {

            if (this.state.error) {
                content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
            } else {

                content = "";

                if (this.state.dataPoderdant.personaFisica) {
                    let nomPoderdant;
                    let llinatge1Poderdant;
                    let llinatge2Poderdant;
                    if (this.state.dataPoderdant.personaFisica.nombre !== undefined) {
                        nomPoderdant = this.state.dataPoderdant.personaFisica.nombre;
                    } else {
                        nomPoderdant = "";
                    }
                    if (this.state.dataPoderdant.personaFisica.apellido1 !== undefined) {
                        llinatge1Poderdant = this.state.dataPoderdant.personaFisica.apellido1;
                    } else {
                        llinatge1Poderdant = "";
                    }
                    if (this.state.dataPoderdant.personaFisica.apellido2 !== undefined) {
                        llinatge2Poderdant = this.state.dataPoderdant.personaFisica.apellido2;
                    } else {
                        llinatge2Poderdant = "";
                    }
                    resultat = t('apoderaApoderamentsTotals1') + this.state.total_items + t('apoderaApoderamentsTotals2') + t('apoderaApoderamentsTotals3') + nomPoderdant +
                        " " + llinatge1Poderdant + " " + llinatge2Poderdant + " (" + this.state.dataPoderdant.personaFisica.nifNie + ").";
                } else {
                    let raoSocialPoderdant;
                    if (this.state.dataPoderdant.personaJuridica.razonSocial !== undefined) {
                        raoSocialPoderdant = this.state.dataPoderdant.personaJuridica.razonSocial;
                    } else {
                        raoSocialPoderdant = "";
                    }
                    resultat = t('apoderaApoderamentsTotals1') + this.state.total_items + t('apoderaApoderamentsTotals2') + t('apoderaApoderamentsTotals4') + raoSocialPoderdant + " (" + this.state.dataPoderdant.personaJuridica.nif + ").";
                }

                if (this.state.dataApoderaments && typeof (this.state.total_items) !== undefined && typeof (this.state.dataApoderaments) !== undefined && this.state.total_items !== 0) {

                    taulaApodera = <>
                        <div>
                            <Table id="tableId" responsive striped bordered hover style={tamanyTaula} className="ocultarMobil">
                                <thead className="table-success">
                                <tr>
                                    <th style={tamanyData}>{t('apoderaTipus')}</th>
                                    <th>{t('apoderaEstat')}</th>
                                    <th>{t('apoderaApoderado')}</th>
                                    <th>{t('apoderaVigencia')}</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.state.dataApoderaments.map(({   tipus,
                                                                      subtipus,
                                                                      estat,
                                                                      apoderado,
                                                                      vigencia,
                                                                      procediment,
                                                                      organismes
                                                                  }, i) => {

                                    let valorAmbit;
                                    if (tipus === '1') {
                                        if (subtipus === '0') {
                                            valorAmbit = t('apoderaTipo.' + tipus + subtipus);
                                        } else {
                                            valorAmbit = t('apoderaTipo.desconegut');
                                        }
                                    } else if (tipus === '2') {
                                        if (subtipus === '0' || subtipus === '1' || subtipus === '2' || subtipus === '3' || subtipus === '4' || subtipus === '5' ||
                                            subtipus === '11' || subtipus === '12' || subtipus === '13' || subtipus === '14' || subtipus === '15' ||
                                            subtipus === '21' || subtipus === '22' || subtipus === '23' || subtipus === '31' || subtipus === '32') {
                                            valorAmbit = t('apoderaTipo.' + tipus + subtipus);
                                        } else {
                                            valorAmbit = t('apoderaTipo.desconegut');
                                        }
                                    } else if (tipus === '3') {
                                        if (subtipus === '0' || subtipus === '1' || subtipus === '11' || subtipus === '12' || subtipus === '13' || subtipus === '14' ||
                                            subtipus === '21' || subtipus === '22' || subtipus === '23' || subtipus === '31' || subtipus === '32') {
                                            valorAmbit = t('apoderaTipo.' + tipus + subtipus);
                                        } else {
                                            valorAmbit = t('apoderaTipo.desconegut');
                                        }
                                    } else {
                                        valorAmbit = t('apoderaTipo.desconegut');
                                    }

                                    let llistaOrganismes=[];
                                    {organismes.forEach((s, i) => {
                                        if(s.codOrganismo) {
                                            llistaOrganismes.push(<p>- {s.codOrganismo}: {s.denomOrganismo}</p>)
                                        }else{
                                            llistaOrganismes.push(<p>- {s.denomOrganismo}</p>)
                                        }
                                    })}

                                    return <>
                                        <tr className="clickable-row" tabIndex={511 + i*2 - 1}
                                            onClick={() => this.mostrarMesInfo('row' + i)} onKeyPress={() => this.mostrarMesInfo('row' + i)}>
                                            <td>{valorAmbit}</td>
                                            {/*<td>{this.nomEstat(estat)}</td>*/}
                                            <td>{this.nomEstat(estat)}</td>
                                            <td>{apoderado}</td>
                                            <td>{vigencia}</td>
                                        </tr>
                                        <tr style={{display: 'none'}} id={'row' + i}>
                                            <td colSpan={4}>
                                                <div style={{float: 'left', width: '70%'}}>
                                                    <p><b>{t('apoderaAmbit')}</b>: {valorAmbit} </p>
                                                    <p>
                                                        <b>{t('apoderaEstatActual')}</b>: {this.nomEstat(estat)} - {this.descripcioEstat(estat)}
                                                    </p>
                                                    {organismes && <p><b>{t('apoderaOrganismes')}</b>: {llistaOrganismes}</p>}
                                                    {procediment && <p><b>{t('apoderaProcediment')}</b>: {procediment}</p>}
                                                </div>
                                                <div style={{float: 'right', width: 'auto'}} id="accedirApodera">
                                                    <p>
                                                        <b>{t('apoderaTipus')}</b>: {tipus} - <b>{t('apoderaSubtipus')}</b>: {subtipus}
                                                    </p>
                                                    <button className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                                            title={t('apoderaAccedirApoderament')}
                                                            tabIndex={511 + i*2}
                                                            aria-labelledby="accedirApodera"
                                                            onClick={() => {
                                                                window.open(this.state.urlApodera, "_blank")
                                                            }}>
                                                        <span className="oi oi-external-link" title=""
                                                              aria-hidden="true"/> {t('apoderaBotoApoderament')}
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>


                                    </>
                                })}
                                </tbody>
                            </Table>
                        </div>
                    </>

                    this.state.dataApoderaments.map(({   tipus,
                                                         subtipus,
                                                         estat,
                                                         apoderado,
                                                         vigencia,
                                                         procediment,
                                                         organismes
                                                     }, i) => {

                        let valorAmbit2;
                        if (tipus === '1') {
                            if (subtipus === '0') {
                                valorAmbit2 = t('apoderaTipo.' + tipus + subtipus);
                            } else {
                                valorAmbit2 = t('apoderaTipo.desconegut');
                            }
                        } else if (tipus === '2') {
                            if (subtipus === '0' || subtipus === '1' || subtipus === '2' || subtipus === '3' ||
                                subtipus === '4' || subtipus === '5' || subtipus === '11' || subtipus === '12' ||
                                subtipus === '13' || subtipus === '14' || subtipus === '15' || subtipus === '21' ||
                                subtipus === '22' || subtipus === '23' || subtipus === '31' || subtipus === '32') {
                                valorAmbit2 = t('apoderaTipo.' + tipus + subtipus);
                            } else {
                                valorAmbit2 = t('apoderaTipo.desconegut');
                            }
                        } else if (tipus === '3') {
                            if (subtipus === '0' || subtipus === '1' || subtipus === '11' || subtipus === '12' ||
                                subtipus === '13' || subtipus === '14' || subtipus === '21' || subtipus === '22' ||
                                subtipus === '23' || subtipus === '31' || subtipus === '32') {
                                valorAmbit2 = t('apoderaTipo.' + tipus + subtipus);
                            } else {
                                valorAmbit2 = t('apoderaTipo.desconegut');
                            }
                        } else {
                            valorAmbit2 = t('apoderaTipo.desconegut');
                        }

                        let llistaOrganismes2=[];
                        {organismes.forEach((s, i) => {
                            if(s.codOrganismo) {
                                llistaOrganismes2.push(<p>- {s.codOrganismo}: {s.denomOrganismo}</p>)
                            }else{
                                llistaOrganismes2.push(<p>- {s.denomOrganismo}</p>)
                            }
                        })}

                        cardApoderaments.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                                 key={i} tabIndex={511+i} onClick={(e) =>
                                window.open(this.state.urlApodera, "_blank")}>
                                <div className="col-sm-1 float-left">
                                    <span className="oi oi-key iconaFormApp" title={t('apoderaIconaApp')} style={{verticalAlign: 'sub'}}/>
                                </div>
                                <div className="col-sm-10 float-right">
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaAmbit')}: </b>{valorAmbit2}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaEstatActual')}: </b>{this.nomEstat(estat)} - {this.descripcioEstat(estat)}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaApoderado')}: </b>{apoderado}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaVigencia')}: </b>{vigencia}</p>
                                    <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaTipus')}</b>: {tipus} - <b>{t('apoderaSubtipus')}</b>: {subtipus}</p>
                                    {organismes && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaOrganismes')}</b>: {llistaOrganismes2}</p>}
                                    {procediment && <p className="card-text pl-1 mt-0" style={{color: 'rgb(102, 102, 102)'}}>
                                        <b>{t('apoderaProcediment')}</b>: {procediment}</p>}
                                </div>
                            </div>
                        )


                    })


                } else if (this.state.total_items === 0 && this.state.dataApoderaments !== null) {
                    taulaApodera = <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                                        role="alert">{t('apoderaBuid')}</div>
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
                            </div>
                        </div>
                    </div>
                    <div className="float-left" style={{width: '97%', position: 'relative'}}>
                        {this.state.total_items !== 0 && <div className="contenedorInfoPersonal mt-2 pb-2 pad15App">
                            {resultat}
                        </div>}
                        {this.state.isLoaded && taulaApodera }
                        {this.state.isLoaded && cardApoderaments}
                    </div>
                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarApodera" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport" tabIndex="550" aria-labelledby="botoTornarApodera">{t('apoderaTornar')}</button>
                    </div>
                </div>
            </>
        );

    }
}

export default withTranslation()(Apodera);