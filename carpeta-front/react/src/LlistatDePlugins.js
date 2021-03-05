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
           var url = baseURL + `/webui/fullinfo/` + seccioID;
           axios.get(url).then(res => {
               var fulldata = res.data;
               this.setState({  plugins: fulldata.veureplugins, 
                                nomEntitat: fulldata.nomEntitat,
                                menupseudoplugin: fulldata.menupseudoplugin,
                                seccions: fulldata.seccions,
                                seccio: fulldata.seccio                                
                                });
           });
        }

    }


    infoHtml(missatge, pluginID) {

        alert(missatge);
        if (this.props.history) {
            var seccioID = this.props.seccioID ? this.props.seccioID : 0;
            var baseSeccio;
            if (seccioID === 0) {
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
            if (seccioID === 0) {
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


        seccionsS = seccions.map((s, i) => (
            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
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
            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" >
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


        plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map((s, i) => (
            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} to={{pathname: baseSeccio + "/pluginhtml/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));

        plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginhtml/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));
        plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginhtml/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));
        plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginhtml/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));


        plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map((s, i) => (
            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} to={{pathname: baseSeccio + "/pluginreact/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));
        plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginreact/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));
        plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginreact/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter" style={styleDesc}>{s.descripcio}</span>
                </Link>
            </div>
        ));
        plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map((s, i) => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                <Link className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`} to={{pathname: baseSeccio + "/pluginreact/" + s.pluginID, nomPagina: "plugin" }} >
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3 alignCenter " style={styleDesc}>{s.descripcio}</span>
                </Link>
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