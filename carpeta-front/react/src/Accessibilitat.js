import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import ExpirarSessio from "./ExpirarSessio";

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
            <div className="container-contenido" tabIndex="1">
                {/*motlla*/}
                {autenticat === '1' &&
                    <ExpirarSessio/>
                }
                <div className="infoNoMenu">
                    <h2 className="titol h2">{t('accessibilitatTitol')}</h2>

                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15 subtitolSuperior">{t('accessibilitatDeclaracio1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="2" aria-labelledby={t('accessibilitatDecret')} aria-describedby={t('accedirEnllas') + t('accessibilitatDecret')}>{t('accessibilitatDecret')}</a> {t('accessibilitatDeclaracio2')}
                        </p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatDeclaracio3')} <a href="https://www.caib.es/carpeta/"
                                                                                target="_blank"
                                                                                rel="noopener noreferrer" tabIndex="3" aria-labelledby="https://www.caib.es/carpeta/" aria-describedby={t('accedirEnllas') + "https://www.caib.es/carpeta/"}>https://www.caib.es/carpeta/</a> {t('accessibilitatDeclaracio4')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatCompliment')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatCompliment1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="4" aria-labelledby={t('accessibilitatDecret')} aria-describedby={t('accedirEnllas') + t('accessibilitatDecret')}>{t('accessibilitatDecret')}</a>{t('accessibilitatCompliment2')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatNoAccessible')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatNoAccessible1')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatNoAccessible2')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="5" aria-labelledby={t('accessibilitatDecret')} aria-describedby={t('accedirEnllas') + t('accessibilitatDecret')}>{t('accessibilitatDecret')}</a>.</p>
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

                        <p className="titol h3">{t('accessibilitatObservacions')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatObservacions1')} <a
                            href="https://www.caib.es/sistrafront/sistrafront/inicio?language=ca&modelo=PD0018ENCW&version=1"
                            target="_blank" rel="noopener noreferrer" tabIndex="6" aria-labelledby={t('contacteAccess')} aria-describedby={t('accedirEnllas') + t('contacteAccess')}>{t('accessibilitatObservacions5')}</a>.</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatObservacions2')} <a href="http://dgtic.caib.es/"
                                                                                  target="_blank"
                                                                                  rel="noopener noreferrer" tabIndex="7" aria-labelledby={t('accessibilitatObservacions3')} aria-describedby={t('accedirEnllas') + t('accessibilitatObservacions3')}>{t('accessibilitatObservacions3')}</a> {t('accessibilitatObservacions4')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatProcediment')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatProcediment1')} <a
                            href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank"
                            rel="noopener noreferrer" tabIndex="8" aria-labelledby={t('accessibilitatDecret')} aria-describedby={t('accedirEnllas') + t('accessibilitatDecret')}>{t('accessibilitatDecret')}</a> {t('accessibilitatProcediment2')}
                        </p>

                        <h3 className="apartat titol h3">{t('accessibilitatOpcional')}</h3>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional1')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional2')}</p>
                        <p className="lh15 subtitolInterior">{t('accessibilitatOpcional3')}</p>
                        <p className="lh15 subtitolInterior"><a className="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance"
                                               target="_blank" rel="noopener noreferrer" tabIndex="9" aria-label={t('accessibilitatWAI')} aria-describedby={t('accessibilitatAccesWAI')}>
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
