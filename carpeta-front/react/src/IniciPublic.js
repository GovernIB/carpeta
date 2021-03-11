import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
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
            codiHtml: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN IniciPublic A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/infoEntitat";

        axios.get(url).then(res => {

            var infoEntitat = res.data;

            this.setState({ nomEntitat: infoEntitat.nom, codiHtml: infoEntitat.html  });
        });
    }



    render() {

        var htmlCode;

        if (this.state.codiHtml) {
            console.log("  SIIII DEFINTI CODI HTML PER ENTITAT");
            htmlCode = <div dangerouslySetInnerHTML={{ __html: this.state.codiHtml }} />
        } else {

            console.log("  NO S'HA DEFINTI CODI HTML PER ENTITAT");

            const { t } = this.props;
            let entitatNom = this.state.nomEntitat;
            //var contextPath = sessionStorage.getItem('contextPath');

            htmlCode = <div className="row mr-0 ml-0">
                            <div className="infoNoMenu">
                                <h2><p className="titol h2">{t('paginaIniciTitol')} {entitatNom}</p></h2>

                                <div className="col-md-5 border-0 float-left p-0">
                                    <p className="titol h5 margen-top-clave"><span className="oi oi-person" />{t('paginaIniciQuefer')}</p>
                                    <ul className="lh15 pl-5 pt-3">
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciQuefer1')}</li>
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciQuefer2')}</li>
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciQuefer3')}</li>
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciQuefer4')}</li>
                                    </ul>

                                    <p className="titol h5 margen-top-clave"><span className="oi oi-account-login" />{t('paginaIniciAcces')}</p>

                                    <p className="lh15">{t('paginaIniciClave')}</p>
                                    <div className="pt-3 imc--logoclave" />
                                    <ul className="lh15 pl-5 pt-3 opcionesAcceso">
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciClave1')}</li>
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciClave2')}</li>
                                        <li><span className="oi oi-arrow-right" />{t('paginaIniciClave3')}</li>
                                    </ul>
                                    <br className="clearBoth" />
                                </div>

                                <div className="col-md-5 border-0 columna2Inici">

                                    <p className="margen-top-clave pb-3">
                                        <span className="btn btn-primary carpeta-btn botoAccedirCarpeta" onClick={ () => {  var loc = new URL(window.location.href);  window.location.href=('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host ) )} } role="button"><span
                                            className="oi oi-account-login" title=""
                                            aria-hidden="true" /> {t('paginaIniciBotoAccedir')}</span>
                                    </p>

                                    <p className="titol h5">{t('tramitacioAnonimaTitol')}</p>
                                    <p className="lh15 pb-3"><a href="#" id="tramitacioModalBtn" data-toggle="modal" data-target="#tramitacioModal"><span className="oi oi-external-link" />{t('tramitacioEnllaz')}</a></p>
                                    
                                    <div className="modal fade" id="tramitacioModal" tabIndex="-1" aria-hidden="true">
                                        <div className="modal-dialog">
                                            <div className="modal-content">
                                            <div className="modal-header">
                                                <div className="modal-title h5" id="tramitacioModalLabel">{t('iniciarTramitacioAnonimaTitol')}</div>
                                                <button type="button" className="close" data-dismiss="modal">
                                                <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div className="modal-body">
                                                <div className="alert alert-danger" role="alert">{t('errorTramitacioAnonima')}</div>
                                                <p>{t('descTramitacioAnonima')}</p>
                                                <input aria-label="IdTramit" type="text" id="clauAnonima" />
                                            </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-secondary" data-dismiss="modal">{t('cancelarTramitacioAnonima')}</button>
                                                <button type="button" id="iniciarTramitacioBtn" className="btn btn-primary">{t('iniciarTramitacioAnonimaBtn')}</button>
                                            </div>
                                            </div>
                                        </div>
                                    </div>


                                    <p className="titol h5">{t('paginaIniciProblemes')}</p>
                                    <p className="lh15">{t('paginaIniciAjuda')}</p>
                                    <ul className="lh15 pl-5 pt-3">
                                        <li><span className="oi oi-arrow-right" /> {t('paginaIniciAjuda1')} <a href="http://clave.gob.es/clave_Home/clave.html"
                                            title={t('paginaIniciAjudaClaveText')}
                                            target="_blank"
                                            rel="noopener noreferrer">{t('paginaIniciAjudaClave')}</a>
                                        </li>
                                        <li><span className="oi oi-arrow-right" /> {t('paginaIniciAjuda2')}</li>
                                        <li><span className="oi oi-arrow-right" /> {t('paginaIniciAjuda3')} <a
                                            href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos"
                                            title={t('paginaIniciAjudaBustiaText')}
                                            target="_blank" rel="noopener noreferrer">{t('paginaIniciAjudaBustia')}</a></li>
                                    </ul>

                                </div>

                            </div>
                        </div>
        }


        

        return (
            <div className="container-contenido homePage">
                {htmlCode}
            </div>
        );

    }
}

export default withTranslation()(IniciPublic);