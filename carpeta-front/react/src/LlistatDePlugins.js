import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import * as Constants from './Constants';

/**
 * 
 * @author anadal Migració a ROUTER
 */
class LlistatDePlugins extends Component {

    constructor() {
        super();
        this.state = {
            items: [], // plugins, pseudoplugin i seccions
            nomEntitat: "",
            seccio: "",
            error: null
        }
        this.canviIdioma = this.canviIdioma.bind(this);
        i18n.on('languageChanged', this.canviIdioma);
    }

    canviIdioma(lng) {
        // console.log(" CANVI IDIOMA EN LLISTAT DE PLUGINS A ]" + lng+ "[")
        this.componentDidMount();
    }

    componentDidMount() {

        const seccioContext = this.props.seccioContext ? this.props.seccioContext : 0;

        let autenticat = sessionStorage.getItem('autenticat');
        // console.log(" LlistatDePlugins - SECCIO " + seccioContext + "(AUTH: ]" + autenticat+ "[)");
        
        if (autenticat === '1') {

           const baseURL = sessionStorage.getItem('contextPath');
           const url = baseURL + `/webui/fullinfosortedauth/` + seccioContext;
           axios.get(url)
               .then(res => {
                   const fulldata = res.data;
                   this.setState({ items: fulldata.items, nomEntitat: fulldata.nomEntitat })
                })
               .catch(error => {
                   console.log(JSON.stringify(error));
                   if (error.response) {
                       console.log("error.response.data: " + error.response.data);
                       console.log("error.response.status: " + error.response.status);
                       console.log("error.response.headers: " + error.response.headers);
                   }
                   this.setState({
                       items:false,
                       nomEntitat: [],
                       error: JSON.stringify(error)
                   });
               });
        } else {
            console.error("S'ha cridat a LListat de Plugins sense estar AUTHENTICAT !!!!!");
        }

    }


    infoHtml(missatge, pluginContext) {

        alert(missatge);
        if (this.props.history) {
            const seccioContext = this.props.seccioContext ? this.props.seccioContext : 0;
            const baseSeccio = (seccioContext === 0) ? '' : Constants.SECCIO_PATH + seccioContext;
            this.props.history.push(baseSeccio + Constants.PLUGINHTML_PATH + pluginContext);
        } else {
            console.log("LLISTAT PLUGINS INFOHTML => NO ES POT LLEGIR HISTORY !!!!!");
        }

    }

    infoReact(missatge, pluginContext) {
        alert(missatge);

        if (this.props.history) {
            const seccioContext = this.props.seccioContext ? this.props.seccioContext : 0;
            const baseSeccio = (seccioContext === 0) ? '' : SECCIO_PATH + seccioContext;
            this.props.history.push(baseSeccio + Constants.PLUGINREACT_PATH + pluginContext);
        } else {
            console.log("LLISTAT PLUGINS INFOHTML => NO ES POT LLEGIR HISTORY !!!!!");
        }

    }

    error(missatge) {
        alert(missatge);
    }


    render() {

        const autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '0') {
            console.log("S'HA INTENTAT MOSTRAR UNA SECCIO SENSE ESTAR AUTENTICATS");
            this.props.history.push("/");
            return '';
        }

        const { t } = this.props;

        let entitatNom = this.state.nomEntitat;

        let urlBase = sessionStorage.getItem('contextPath')

        const baseSeccio = (this.props.seccioContext == 0) ? '' : Constants.SECCIO_PATH + this.props.seccioContext;
        const styleDesc =  { fontSize: '85%', color: '#666', textAlign: 'center' } ;

        let allItems = [];

        if (this.state.error) {
            allItems = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            this.state.items.forEach((s, i) => {
                const gravetatCssClass = (s.gravetat > 0) ? `alert${s.gravetat}` : '';
                switch (s.tipus) {

                    case 0: // Plugin react
                        allItems.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                                <Link
                                    className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${gravetatCssClass}`}
                                    to={{
                                        pathname: baseSeccio + Constants.PLUGINREACT_PATH + s.context,
                                        nomPagina: "plugin"
                                    }} title={s.missatge}>
                                    <span className="card-title titol pl-1 h3"><img src={urlBase + s.urllogo}
                                                                                    alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <span className="titolPlugin titol h3">{s.nom}</span>
                                    <span className="card-text mb-3 mt-3 alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;
                    case 1: // Plugin html
                        allItems.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0" key={i}>
                                <Link
                                    className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 ${gravetatCssClass}`}
                                    to={{
                                        pathname: baseSeccio + Constants.PLUGINHTML_PATH + s.context,
                                        nomPagina: "plugin"
                                    }} title={s.missatge}>
                                    <span className="card-title titol pl-1 h3"><img src={urlBase + s.urllogo}
                                                                                    alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <span className="titolPlugin titol h3">{s.nom}</span>
                                    <span className="card-text mb-3 mt-3 alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;

                    case 3: // Seccio
                        allItems.push(
                            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                                <Link to={baseSeccio + Constants.SECCIO_PATH + s.context}
                                      className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}>
                                    <span className="card-title titol pl-1 h3"><img src={s.urllogo} alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <span className="titolPlugin  titol h3">{s.nom}</span>
                                    <span className="card-text mb-3 mt-3 alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;

                    case 4: // PseudoPlugin
                        allItems.push(
                            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                                        onClick={() => window.open(s.url)}>
                                    <span className="card-title titol pl-1 h3"><img src={s.urllogo} alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <span className="titolPlugin  titol h3">{s.nom}</span>
                                    <span className="card-text mb-3 mt-3 alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </button>
                            </div>
                        );
                        break;
                }
            });
        }

        let titolHeader;
        let subtitolHeader;
        if (this.props.seccioContext === 0) {
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
                                {allItems}
                            </div>

                        </div>

                    </div>

                </div>
            </div>

        );
    }

}

export default withTranslation()(withRouter(LlistatDePlugins));