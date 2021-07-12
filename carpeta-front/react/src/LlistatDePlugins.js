import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import * as Constants from './Constants';
import DocumentTitle from "react-document-title";

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
                   this.setState({ 
                       items: fulldata.items, 
                       nomEntitat: fulldata.nomEntitat, 
                       seccio: (fulldata.hasOwnProperty('seccio')) ? fulldata.seccio : null 
                    })
                })
               .catch(error => {
                   console.log(JSON.stringify(error));
                   if (error.response) {
                       console.log("error.response.data: " + error.response.data);
                       console.log("error.response.status: " + error.response.status);
                       console.log("error.response.headers: " + error.response.headers);
                   }
                   if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                       this.setState({
                           items:false,
                           nomEntitat: [],
                           error: "error500plugin"
                       });
                   } else{
                       this.setState({
                           items:false,
                           nomEntitat: [],
                           error: JSON.stringify(error)
                       });
                   }

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
        const styleDesc =  { fontSize: '84%', color: '#666', textAlign: 'center' } ;

        let allItems = [];
        let enllasos = [];

        if (this.state.error) {
            allItems = <div className="alert alert-danger" role="alert">{t(this.state.error)}</div>;
        } else {

            this.state.items.forEach((s, i) => {
                const gravetatCssClass = (s.gravetat > 0) ? `alert${s.gravetat}` : '';
                switch (s.tipus) {

                    case 0: // Plugin react
                    case 1: // Plugin html
                        allItems.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 col-xs- pl-0" key={i}>
                                <Link
                                    className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${gravetatCssClass}`}
                                    to={{
                                        pathname: baseSeccio + ((s.tipus == 0) ? Constants.PLUGINREACT_PATH :Constants.PLUGINHTML_PATH)+ s.context,
                                        nomPagina: "plugin"
                                    }} title={s.missatge} tabIndex={502+i} aria-labelledby={s.nom} aria-describedby={t('accedirPlugin') + s.nom}>
                                    <span className="card-title titol pl-1 h3"><img src={urlBase + s.urllogo}
                                                                                    alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <h3 className="apartat titolPlugin titol h3">{s.nom}</h3>
                                    <span className="card-text alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;
                        /*
                    case 1: // Plugin html
                        allItems.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0" key={i}>
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
                                    <span className="card-text alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;
*/

                    case 3: // Seccio
                        allItems.push(
                            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0">
                                <Link to={baseSeccio + Constants.SECCIO_PATH + s.context}
                                      className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} tabIndex={502+i} aria-labelledby={s.nom} aria-describedby={t('accedirSeccio') + s.nom}>
                                    <span className="card-title titol pl-1 h3"><img src={s.urllogo} alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <h3 className="apartat titolPlugin titol h3">{s.nom}</h3>
                                    <span className="card-text alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </Link>
                            </div>
                        );
                        break;

                    case 4: // PseudoPlugin
                        allItems.push(
                            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0">
                                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                                        onClick={() => window.open(s.url)} tabIndex={502+i} aria-labelledby={s.nom} aria-describedby={t('accedirPlugin') + s.nom}>
                                    <span className="card-title titol pl-1 h3"><img src={s.urllogo} alt={s.nom} title=""
                                                                                    className="imc-icona"/></span>
                                    <h3 className="apartat titolPlugin titol h3">{s.nom}</h3>
                                    <span className="card-text alignCenter"
                                          style={styleDesc}>{s.descripcio}</span>
                                </button>
                            </div>
                        );
                        break;

                    case 7: // Enllaz "En un clic"
                        enllasos.push(<li key={i} className="col-md-5 float-left">
                            <a href={s.url} title={s.nom} target="_blank" tabIndex={565+i} aria-labelledby={s.nom} aria-describedby={t('accedirEnllas') + s.nom}>
                                <span>{s.nom}</span>
                            </a>
                        </li>);
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

        let titolEnunclic="";
        let enllasosEnunclic="";
        if(enllasos.length > 0){
            titolEnunclic = <div className="h2 mt-4">{t('iniciPrivatEnllasos')}</div>;
            enllasosEnunclic = <div className="col-md-12 border-0 pl-0 pr-0 llistaEnllasos">
                <p className="lh15 subtitol">{t('iniciPrivatDescEnllas')}</p>
                <ul className="lh15 pl-4 pt-3 subtitolInterior senseEstilLlista">
                    {enllasos}
                </ul>
            </div>;
        }

        return (

            <div className="row mr-0 ml-0">

                <DocumentTitle title={i18n.t('pipellaAutenticat') + " - " + t('menuTitol')} />

                <div className="infoNoMenu">

                    <h2 className="titol h2">{titolHeader}</h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">

                        <p className="lh15 subtitol">{subtitolHeader}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">
                                {allItems}
                            </div>

                        </div>

                    </div>

                    {titolEnunclic}
                    {enllasosEnunclic}

                </div>
            </div>

        );
    }

}

export default withTranslation()(withRouter(LlistatDePlugins));