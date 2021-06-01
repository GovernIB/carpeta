import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";

/**
 * @author anadal Valors per defecte o html de l'entitat
 */

class IniciPublic extends Component {

    constructor(){
        super();
        this.state = {
            nomEntitat: null,
            codiHtml: null,
            error: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN IniciPublic A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/infoEntitat";

        axios.get(url)
            .then(res => {
                var infoEntitat = res.data;
                this.setState({ nomEntitat: infoEntitat.nom, codiHtml: infoEntitat.html  });
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    nomEntitat: null,
                    codiHtml: null,
                    error: JSON.stringify(error)
                });
            });
    }



    render() {

        var htmlCode;

        var baseURL = sessionStorage.getItem('contextPath');

        if (this.state.error) {
            htmlCode = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (this.state.codiHtml) {
                // console.log("  SIIII DEFINTI CODI HTML PER ENTITAT");
                htmlCode = <div dangerouslySetInnerHTML={{__html: this.state.codiHtml}}/>
            } else {

                // console.log("  NO S'HA DEFINTI CODI HTML PER ENTITAT");

                const {t} = this.props;
                let entitatNom = this.state.nomEntitat;
                //var contextPath = sessionStorage.getItem('contextPath');

                htmlCode = <div className="row mr-0 ml-0">
                    <div className="infoNoMenu">
                        <h2><p className="titol h2">{t('paginaIniciTitol')} {entitatNom}</p></h2>

                        <div className="col-md-5 border-0 float-left p-0">
                            <h3 className="apartat"><p className="titol h5 margen-top-clave"><span
                                className="oi oi-person"/>{t('paginaIniciQuefer')}</p></h3>
                            <ul className="lh15 pl-5 pt-3">
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer1')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer2')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer3')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer4')}</li>
                            </ul>

                            <h3 className="apartat"><p className="titol h5 margen-top-clave"><span
                                className="oi oi-account-login"/>{t('paginaIniciAcces')}</p></h3>

                            <p className="lh15">{t('paginaIniciClave')}</p>

                            <div className="row">
                                <div className="pt-3 col-5">
                                    <img src={baseURL + "/src/assets/images/solicitar_clave_acceso_dgt.png"} alt={t('paginaIniciLogoClave')} title={t('paginaIniciLogoClave')} />
                                </div>
                                <div className="pt-3 col-5">
                                    <ul className="lh15 pt-3 opcionesAcceso">
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave1')}</li>
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave2')}</li>
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave3')}</li>
                                    </ul>
                                </div>

                            </div>

                            <br className="clearBoth"/>
                        </div>

                        <div className="col-md-5 border-0 columna2Inici">

                            <p className="margen-top-clave pb-3">
                                <button className="btn btn-primary carpeta-btn botoAccedirCarpeta"
                                      onClick={() => {
                                          var loc = new URL(window.location.href);
                                          window.location.href = ('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host))
                                      }} tabIndex="2" aria-labelledby={t('paginaIniciBotoAccedir')} aria-describedby={t('accedirBoto') + t('paginaIniciBotoAccedir')}>
                                    <span className="oi oi-account-login" title="" aria-hidden="true"/> {t('paginaIniciBotoAccedir')}
                                </button>
                            </p>

                            <h3 className="apartat"><p className="titol h5">{t('tramitacioAnonimaTitol')}</p></h3>
                            <p className="lh15 pb-3"><a href={baseURL + '/#/publicmoduls/reprendretramit'} id="tramitacioModalBtn" tabIndex="3" aria-labelledby={t('tramitacioEnllaz')} aria-describedby={t('accedirEnllas') + t('tramitacioEnllaz')}><span
                                className="oi oi-external-link"/>{t('tramitacioEnllaz')}</a></p>

                            <h3 className="apartat"><p className="titol h5">{t('paginaIniciProblemes')}</p></h3>
                            <p className="lh15">{t('paginaIniciAjuda')}</p>
                            <ul className="lh15 pl-5 pt-3">
                                <li><span className="oi oi-arrow-right"/> {t('paginaIniciAjuda1')} <a
                                    href="http://clave.gob.es/clave_Home/clave.html"
                                    title={t('paginaIniciAjudaClaveText')}
                                    target="_blank"
                                    rel="noopener noreferrer" tabIndex="4" aria-labelledby={t('paginaIniciAjudaClave')} aria-describedby={t('accedirEnllas') + t('paginaIniciAjudaClave')}>{t('paginaIniciAjudaClave')}</a>
                                </li>
                                <li><span className="oi oi-arrow-right"/> {t('paginaIniciAjuda2')}</li>
                                <li><span className="oi oi-arrow-right"/> {t('paginaIniciAjuda3')} <a
                                    href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos"
                                    title={t('paginaIniciAjudaBustiaText')}
                                    target="_blank" rel="noopener noreferrer" tabIndex="5" aria-labelledby={t('paginaIniciAjudaBustia')} aria-describedby={t('accedirEnllas') + t('paginaIniciAjudaBustia')}>{t('paginaIniciAjudaBustia')}</a></li>
                            </ul>

                        </div>

                    </div>
                </div>
            }

        }
        

        return (
            <div className="container-contenido homePage">
                {htmlCode}
            </div>
        );

    }
}

export default withTranslation()(IniciPublic);