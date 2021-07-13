import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import ExpirarSessio from "./ExpirarSessio";
import DocumentTitle from "react-document-title";

class Accessibilitat extends Component {

    componentDidMount() {
        $('[tabIndex=1]').focus();
    }

    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        //var motlla = <*Breadcrumb items={breadcrumbPaths.Accessibilitat} autenticat={autenticat}/>;

        return (
            <div className="container-contenido" tabIndex="501">

                <DocumentTitle title={t('menuAccessibilitat') + " - " + t('menuTitol')} />

                {/*motlla*/}
                {autenticat === '1' &&
                    <ExpirarSessio/>
                }
                <div className="infoNoMenu">
                    <h2 className="titol h2">{t('accessibilitatTitol')}</h2>

                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15 subtitolSuperior" id="compromis">{t('accessibilitatDeclaracio1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="502" aria-labelledby="compromis">{t('accessibilitatDecret')}</a> {t('accessibilitatDeclaracio2')}
                        </p>
                        <p className="lh15 subtitolInterior" id="aplica">{t('accessibilitatDeclaracio3')} <a href="https://www.caib.es/carpeta/"
                                                                                target="_blank"
                                                                                rel="noopener noreferrer" tabIndex="503" aria-labelledby="aplica">https://www.caib.es/carpeta/</a> {t('accessibilitatDeclaracio4')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatCompliment')}</h3>
                        <p className="lh15 subtitolInterior" id="parcialment">{t('accessibilitatCompliment1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="504" aria-labelledby="parcialment">{t('accessibilitatDecret')}</a>{t('accessibilitatCompliment2')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatNoAccessible')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatNoAccessible1')}</p>
                        <p className="lh15 subtitolInterior" id="mancaConformitat">{t('accessibilitatNoAccessible2')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="505" aria-labelledby="mancaConformitat">{t('accessibilitatDecret')}</a>.</p>
                        <div className="mb-3">
                            <ul className="lh15 pl-5 pt-1">
                                <li>{t('accessibilitatNoAccessible3')}</li>
                                <li>{t('accessibilitatNoAccessible4')}</li>
                                <li>{t('accessibilitatNoAccessible5')}</li>
                            </ul>
                        </div>

                        <h3 className="apartat titol h3">{t('accessibilitatPreparacio')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatPreparacio1')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatPreparacio2')}</p>

                        <h2 className="titol h2">{t('accessibilitatObservacions')}</h2>
                        <p className="lh15 subtitolInterior" id="millorant">{t('accessibilitatObservacions1')} <a
                            href="https://www.caib.es/sistrafront/sistrafront/inicio?language=ca&modelo=PD0018ENCW&version=1"
                            target="_blank" rel="noopener noreferrer" tabIndex="506" aria-labelledby="millorant">{t('accessibilitatObservacions5')}</a>.</p>
                        <p className="lh15 subtitolInterior" id="dgmad">{t('accessibilitatObservacions2')} <a href="http://dgtic.caib.es/"
                                                                                  target="_blank"
                                                                                  rel="noopener noreferrer" tabIndex="507" aria-labelledby="dgmad">{t('accessibilitatObservacions3')}</a> {t('accessibilitatObservacions4')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatProcediment')}</h3>
                        <p className="lh15 subtitolInterior" id="reclamacio">{t('accessibilitatProcediment1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="508" aria-labelledby="reclamacio">{t('accessibilitatDecret')}</a> {t('accessibilitatProcediment2')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatOpcional')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional1')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional2')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional3')}</p>
                        <p className="lh15 subtitolInterior" id="wai"><a className="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance"
                                               target="_blank" rel="noopener noreferrer" tabIndex="509" aria-labelledby="wai">
                            <img src="/carpetafront/src/assets/images/wcag2AA.png" title={t('accessibilitatAccesWAI')} alt={t('accessibilitatAccesWAI')} />
                        </a></p>
                        <p className="lh15 subtitolInferior">{t('accessibilitatOpcional4')}</p>

                    </div>

                </div>
            </div>
        );
    }
}

export default withTranslation()(Accessibilitat);
