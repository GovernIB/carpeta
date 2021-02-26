import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";

import { Link } from "react-router-dom";
import { withRouter } from "react-router";
/**
 * 
 * @author anadal Migració a ROUTER
 */
class LlistatDePlugins extends Component {

    constructor() {
        super();
        this.state = {
            plugins: [],
            menupseudoplugin: [],
            seccions: [],
            seccio: null
        }
        this.canviIdioma = this.canviIdioma.bind(this);
        i18n.on('languageChanged', this.canviIdioma);
    }

    canviIdioma(lng) {
        console.log(" CANVI IDIOMA EN LLISTAT DE PLUGINS A ]" + lng+ "[")
        this.componentDidMount();
    }

    componentDidMount() {

        const seccioID = this.props.seccioID ? this.props.seccioID : 0;

        console.log(" NOU LLISTAT AAAAAA - SECCIO " + seccioID);

        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '1') {

            var baseURL = sessionStorage.getItem('contextPath');
            // 0 == Nivell Arell
            var url = baseURL + `/pluginfront/veureplugins/` + seccioID;
            axios.get(url).then(res => {
                const plugins = res.data;
                this.setState({ plugins });
            })

            var url2 = baseURL + "/webui/nomEntitat";
            axios.get(url2).then(res => {
                this.setState({ nomEntitat: res.data })
            });
            // 0 == Nivell Arell    
            var url4 = baseURL + `/webui/menupseudoplugin/` + seccioID;
            axios.get(url4).then(res => {
                this.setState({ menupseudoplugin: res.data })
            });
            // 0 == Nivell Arell        
            var url5 = baseURL + `/webui/seccions/` + seccioID;
            axios.get(url5).then(res => {
                this.setState({ seccions: res.data })
            });

            if (seccioID != 0) {
                var url6 = baseURL + `/webui/seccio/` + seccioID;
                axios.get(url6).then(res => {
                    this.setState({ seccio: res.data })
                });
            }
        }

    }


    infoHtml(missatge, pluginID) {

        alert(missatge);
        if (this.props.history) {
            var seccioID = this.props.seccioID ? this.props.seccioID : 0;
            var baseSeccio;
            if (seccioID == 0) {
                baseSeccio = '';
            } else {
                baseSeccio = '/seccio/' + seccioID;
            }
            this.props.history.push(baseSeccio + "/pluginhtml/" + pluginID);
        } else {
            console.log("LLISTAT PLUGINS INFOHTML => NO PUC LLEGIR HISTORY !!!!!");
        };

    }

    infoReact(missatge, pluginID) {
        alert(missatge);

        if (this.props.history) {
            var seccioID = this.props.seccioID ? this.props.seccioID : 0;
            var baseSeccio;
            if (seccioID == 0) {
                baseSeccio = '';
            } else {
                baseSeccio = '/seccio/' + seccioID;
            }
            this.props.history.push(baseSeccio + "/pluginreact/" + pluginID);
        } else {
            console.log("LLISTAT PLUGINS INFOREACT => NO PUC LLEGIR HISTORY !!!!!");
        };

    }

    error(missatge) {
        alert(missatge);
    }


    render() {

        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '0') {
            console.log("S'HA INTENTAT MOSTRAR UNA SECCIO PERO NOOOO ESTAM AUTENTICATS");
            this.props.history.push("/");
            return '';
        }

        const { t } = this.props;

        let entitatNom = this.state.nomEntitat;

        //var urlBase = window.location.href;
        var urlBase = sessionStorage.getItem('contextPath');

        const seccions = this.state.seccions;

        const plugins = this.state.plugins;

        const menupseudoplugin = this.state.menupseudoplugin;

        var seccionsS;
        var menuPseudoPlugin;
        var plugHtml;
        var plugHtmlInfo;
        var plugHtmlWarning;
        var plugHtmlError;
        var plugReact;
        var plugReactInfo;
        var plugReactWarning;
        var plugReactError;

        var baseSeccio;
        var seccioID = this.props.seccioID;
        if (seccioID == 0) {
            baseSeccio = '';
        } else {
            baseSeccio = '/seccio/' + seccioID;
        }

        
        const styleDesc =  { fontSize: '80%', color: '#666', textAlign: 'center' } ;


        seccionsS = seccions.map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <Link to={baseSeccio + "/seccio/" + s.seccioID} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}  >
                    <span className="card-title titol pl-1 h3">
                        <img src={s.iconaID} alt="" title="" className="imc-icona" />
                    </span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));

        menuPseudoPlugin = menupseudoplugin.map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.label} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                    onClick={() => window.open(s.url)} >
                    <span className="card-title titol pl-1 h3"><img
                        src={s.urllogo}
                        alt={s.nom} title={s.label}
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.label}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.labelDescription}</span>
                </button>
            </div>
        ));



        plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                {/*

                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                        onClick={event => window.location.href = "javascript:newwPluginnHtml('contingut', '1', '" + s.pluginID + "');"}> */}
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} to={baseSeccio + "/pluginhtml/" + s.pluginID} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
                {/*</button>*/}
            </div>
        ));

        plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.infoHtml(s.missatge, s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.infoHtml(s.missatge, s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));


        plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                {/*<button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={event => window.location.href = "javascript:newwPluginnReact('contingut', '1', '" + s.pluginID + "');"}> */}
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} to={baseSeccio + "/pluginreact/" + s.pluginID} >

                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
                {/*</button>*/}
            </div>
        ));
        plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.infoReact(s.missatge, s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.infoReact(s.missatge, s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                    onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter " style={styleDesc}>{s.descripcio}</span>
                </button>
            </div>
        ));

        var titolHeader;
        var subtitolHeader;
        if (this.props.seccioID == 0) {
            titolHeader = t('paginaIniciTitolPrivat') + entitatNom;
            subtitolHeader = t('paginaIniciIntroduccioPrivat');
        } else {
            const seccio = this.state.seccio;
            if (seccio == null) {
                subtitolHeader = "...";
                titolHeader = "...";
            } else {
                titolHeader = seccio.nom;
                subtitolHeader = seccio.descripcio;
            }
        }

        return (

            <div className="row mr-0 ml-0">

                <div className="infoNoMenu">

                    <h2><p className="titol h2">{titolHeader}</p></h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">


                        <p className="lh15">{subtitolHeader}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">
                                {seccionsS}
                                {plugHtml}
                                {plugHtmlInfo}
                                {plugHtmlWarning}
                                {plugHtmlError}
                                {plugReact}
                                {plugReactInfo}
                                {plugReactWarning}
                                {plugReactError}
                                {menuPseudoPlugin}

                            </div>

                        </div>

                    </div>

                </div>
            </div>

        );
    }

}

export default withTranslation()(withRouter(LlistatDePlugins));