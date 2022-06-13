import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import DocumentTitle from "react-document-title";
import LogoLateral from "./LogoLateral";

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
        var amplePantalla = screen.width;
        if(amplePantalla < 576) {
            document.getElementById("headerBarra").style.backgroundColor = "#ffffff";
        }

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
        var htmlCodeApp;

        var baseURL = sessionStorage.getItem('contextPath');
        let entitatNom = this.state.nomEntitat;
        sessionStorage.setItem('entitatNom', entitatNom);

        if (this.state.error) {
            htmlCode = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
            htmlCodeApp = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (this.state.codiHtml) {
                // console.log("  SIIII DEFINTI CODI HTML PER ENTITAT");
                htmlCode = <div dangerouslySetInnerHTML={{__html: this.state.codiHtml}}/>
                htmlCodeApp = <div dangerouslySetInnerHTML={{__html: this.state.codiHtml}}/>
            } else {

                // console.log("  NO S'HA DEFINTI CODI HTML PER ENTITAT");

                const {t} = this.props;
                //var contextPath = sessionStorage.getItem('contextPath');

                htmlCode = <div className="row mr-0 ml-0">
                    <div className="infoNoMenu">
                        <h2 className="titol h2">{entitatNom}</h2>

                        <div className="col-md-5 border-0 float-left p-0">
                            <h3 className="apartat titol h5 margen-top-clave"><span
                                className="oi oi-person"/>{t('paginaIniciQuefer')}</h3>
                            <ul className="lh15 pl-5 pt-3 subtitolSuperior">
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer1')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer2')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer3')}</li>
                                <li><span className="oi oi-arrow-right"/>{t('paginaIniciQuefer4')}</li>
                            </ul>

                            <h3 className="apartat titol h5 margen-top-clave"><span
                                className="oi oi-account-login"/>{t('paginaIniciAcces')}</h3>

                            <p className="lh15 subtitolInterior" id="queEsClave">{t('paginaIniciClave')}</p>

                            <div className="row">
                                <div className="pt-3 col-5">
                                    <a className="mr-auto" href="http://clave.gob.es/clave_Home/clave.html"
                                       target="_blank" rel="noopener noreferrer" tabIndex="502" aria-label={t('paginaIniciAjudaClaveText')} aria-describedby="queEsClave">
                                        <img src={baseURL + "/src/assets/images/solicitar_clave_acceso_dgt.png"} alt={t('paginaIniciLogoClave')} title={t('paginaIniciLogoClave')} />
                                    </a>
                                </div>
                                <div className="pt-3 col-5">
                                    <ul className="lh15 pt-3 opcionesAcceso subtitolInterior">
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave1')}</li>
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave2')}</li>
                                        <li><span className="oi oi-arrow-right"/>{t('paginaIniciClave3')}</li>
                                    </ul>
                                </div>

                            </div>

                            <br className="clearBoth"/>
                        </div>

                        <div className="col-md-5 border-0 columna2Inici">

                            <p className="margen-top-clave pb-3" id="accedirCarpetaPrivat">
                                <button className="btn btn-primary carpeta-btn botoAccedirCarpeta" title={t('accedirBoto') + t('paginaIniciBotoAccedir')}
                                      onClick={() => {
                                          var loc = new URL(window.location.href);
                                          
                                          var theUrl = baseURL + '/public/doLogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host);
                                          // Es des d'APP?
                                          var loginCode = sessionStorage.getItem('loginCode');
                                          if (loginCode) {
                                              theUrl = theUrl + '&loginCode=' + loginCode;
                                          }
                                          //alert('Obrim DOLOGIN => ' + theUrl);
                                          window.location.href = theUrl
                                      }} tabIndex="503" aria-labelledby="accedirCarpetaPrivat">
                                    <span className="oi oi-account-login" title="" aria-hidden="true"/> {t('paginaIniciBotoAccedir')}
                                </button>
                            </p>

                            <h3 className="apartat titol h5">{t('tramitacioAnonimaTitol')}</h3>
                            <p className="lh15 pb-3 subtitolSuperior" id="reprendreTramitacio"><a href={baseURL + '/#/publicmodul/reprendretramit'} id="tramitacioModalBtn" tabIndex="504" aria-labelledby="reprendreTramitacio"><span
                                className="oi oi-external-link"/>{t('tramitacioEnllaz')}</a></p>

                            <h3 className="apartat titol h5">{t('paginaIniciProblemes')}</h3>
                            <p className="lh15 subtitolInterior">{t('paginaIniciAjuda')}</p>
                            <ul className="lh15 pl-5 pt-3 subtitolInterior">
                                <li><span className="oi oi-arrow-right" id="accClave"/> {t('paginaIniciAjuda1')} <a
                                    href="http://clave.gob.es/clave_Home/clave.html"
                                    title={t('paginaIniciAjudaClaveText')}
                                    target="_blank"
                                    rel="noopener noreferrer" tabIndex="505" aria-labelledby="accClave">{t('paginaIniciAjudaClave')}</a>
                                </li>
                                <li><span className="oi oi-arrow-right"/> {t('paginaIniciAjuda2')}</li>
                                <li><span className="oi oi-arrow-right" id="bustiaAtencio"/> {t('paginaIniciAjuda3')} <a
                                    href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos"
                                    title={t('paginaIniciAjudaBustiaText')}
                                    target="_blank" rel="noopener noreferrer" tabIndex="506" aria-labelledby="bustiaAtencio">{t('paginaIniciAjudaBustia')}</a></li>
                            </ul>

                        </div>

                    </div>
                </div>

                htmlCodeApp = <>
                    <div className="">
                        <a href="http://www.caib.es" className="imc--goib" title={i18n.t('menuLateralGovern')} target="_blank" tabIndex="2" aria-label={i18n.t('menuLateralGovern')} aria-describedby="entitatLogo">
                            <img src={baseURL + "/src/assets/images/goib-05.png"} title="" alt={i18n.t('menuLateralGovern')} className="imgCenter" width="100px"/>
                        </a>
                    </div>

                    <h1 className="titolAppInici">{i18n.t('menuTitol')}</h1>

                    <p className="margen-top-clave" id="accedirCarpetaPrivat2" style={{paddingBottom: '2em!important'}}>
                        <button className="btn btn-primary carpeta-btn botoAccedirCarpeta" title={i18n.t('accedirBoto') + i18n.t('paginaIniciBotoAccedirMobil')}
                                onClick={() => {
                                    var loc = new URL(window.location.href);

                                    var theUrl = baseURL + '/public/doLogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host);
                                    // Es des d'APP?
                                    var loginCode = sessionStorage.getItem('loginCode');
                                    if (loginCode) {
                                        theUrl = theUrl + '&loginCode=' + loginCode;
                                    }
                                    //alert('Obrim DOLOGIN => ' + theUrl);
                                    window.location.href = theUrl
                                }} tabIndex="503" aria-labelledby="accedirCarpetaPrivat2" style={{ padding: '1em 3.5em 1em 3.5em', borderRadius: '10px', fontSize: '1.2em'}}>
                            <span className="oi oi-account-login" title="" aria-hidden="true"/> {i18n.t('paginaIniciBotoAccedirMobil')}
                        </button>
                    </p>

                    <div className="anonimApp">
                        <h2 className="apartat h5 text-verd font-weight-bold textTramitacioApp">{i18n.t('tramitacioAnonimaTitol')}</h2>
                        <p className="lh15 pb-3 subtitolSuperior textTramitacioApp" id="reprendreTramitacio"><a href={baseURL + '/#/publicmodul/reprendretramit'} id="tramitacioModalBtn" tabIndex="504" aria-labelledby="reprendreTramitacio"><span
                            className="oi oi-external-link" style={{paddingRight: '20px'}}/>{i18n.t('tramitacioEnllazApp')}</a></p>
                    </div>
                </>
            }

        }
        

        return (
            // <div className="container-contenido homePage">
            //
            //     <DocumentTitle title={i18n.t('menuTitol') + " - " + entitatNom} />
            //
            //     {htmlCode}
            // </div>

            <>
                <div className="container-contenido homePage ocultarMobil">

                    <DocumentTitle title={i18n.t('menuTitol') + " - " + entitatNom} />

                    {htmlCode}
                </div>


                <div className="visioMobil centrarTop">

                    {htmlCodeApp}

                </div>
            </>
        );

    }
}

export default withTranslation()(IniciPublic);