import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import ExpirarSessio from "./ExpirarSessio";
import DocumentTitle from "react-document-title";


class AvisLegal extends Component {

    componentDidMount() {
        $('[tabIndex=1]').focus();
    }

    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        var langActual = sessionStorage.getItem("langActual");

        var link1 = "https://catalegdades.caib.cat/"+langActual;
        var enllas1 = <a href={link1} target="_blank" rel="noopener noreferrer" tabIndex="505" aria-labelledby="dadesobertes">{link1}</a>

        var link2 = "https://www.caib.es/seucaib/"+langActual+"/200/persones/";
        var enllas2 = <a href={link2} target="_blank" rel="noopener noreferrer" tabIndex="506" aria-labelledby="seuElectronica">{t('avisLegalPolitica9')}</a>

        var link3 = "https://developers.google.com/analytics/devguides/collection/analyticsjs/cookie-usage?hl="+langActual+"-419";
        var enllas3 = <a href={link3} target="_blank" rel="noopener noreferrer" tabIndex="509" aria-labelledby="analitiques">{t('avisLegalCookies15')}</a>

        var link4 = "https://policies.google.com/privacy?hl="+langActual;
        var enllas4 = <a href={link4} target="_blank" rel="noopener noreferrer" tabIndex="510" aria-labelledby="analitiques">{t('avisLegalCookies17')}</a>

        var link5 = "https://support.google.com/chrome/answer/95647?hl="+langActual;
        var enllas5 = <a href={link5} target="_blank" rel="noopener noreferrer" tabIndex="511" aria-labelledby="desactivarGaletes">Google Chrome</a>

        var link6 = "http://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-que-los-sitios-we";
        var enllas6 = <a href={link6} target="_blank" rel="noopener noreferrer" tabIndex="512" aria-labelledby="desactivarGaletes">Mozilla Firefox</a>

        var link7 = "http://windows.microsoft.com/"+langActual+"-"+langActual+"/windows-vista/block-or-allow-cookies";
        var enllas7 = <a href={link7} target="_blank" rel="noopener noreferrer" tabIndex="513" aria-labelledby="desactivarGaletes">Internet Explorer</a>

        var link8 = "http://support.apple.com/"+langActual+"-"+langActual+"/HT1677";
        var enllas8 = <a href={link8} target="_blank" rel="noopener noreferrer" tabIndex="514" aria-labelledby="desactivarGaletes">Safari</a>

        var link9 = "http://support.google.com/chrome/answer/2392971?hl="+langActual;
        var enllas9 = <a href={link9} target="_blank" rel="noopener noreferrer" tabIndex="515" aria-labelledby="desactivarGaletes">Chrome (Android)</a>

        var link10 = "http://help.opera.com/Windows/11.50/"+langActual+"-"+langActual.toUpperCase()+"/cookies.html";
        var enllas10 = <a href={link10} target="_blank" rel="noopener noreferrer" tabIndex="516" aria-labelledby="desactivarGaletes">Opera</a>


        return (
            <div className="container-contenido" tabIndex="501">

                <DocumentTitle title={t('peuAvis') + " - " + t('menuTitol')} />

                {autenticat === '1' &&
                <ExpirarSessio/>
                }
                <div className="infoNoMenu">
                    <h2 className="titol h2">{t('avisLegalInformacio')}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15 subtitolSuperior" id="dominis">
                            {t('avisLegalInformacio1')}
                            <a href="http://www.caib.es" target="_blank" rel="noopener noreferrer" tabIndex="502" aria-labelledby="dominis">caib.es</a>,
                            <a href="http://www.caib.cat" target="_blank" rel="noopener noreferrer" tabIndex="503" aria-labelledby="dominis"> caib.cat</a>
                            {t('avisLegalInformacio8')}
                            <a href="http://www.illesbalears.cat" target="_blank" rel="noopener noreferrer" tabIndex="504" aria-labelledby="dominis">illesbalears.cat</a>
                            {t('avisLegalInformacio2')}
                        </p>
                        <p className="lh15 subtitolInterior">{t('avisLegalInformacio3')}</p>
                        <p className="lh15 subtitolInterior">{t('avisLegalInformacio4')}</p>
                        <p className="lh15 subtitolInterior">{t('avisLegalInformacio5')}</p>
                        <p className="lh15 subtitolInterior">{t('avisLegalInformacio6')}</p>
                        <p className="lh15 subtitolInterior" id="dadesobertes">
                            {t('avisLegalInformacio7')}
                            {enllas1}.
                        </p>
                    </div>

                    <div className="col-md-12 border-0 float-left p-0 pt-2">
                        <h3 className="apartat titol h2">{t('avisLegalPolitica')}</h3>
                        <p className="lh15 subtitolInterior">
                            <b>{t('avisLegalPolitica1')}</b>
                            {t('avisLegalPolitica2')}
                            <b>{t('avisLegalPolitica3')}</b>
                            {t('avisLegalPolitica4')}
                        </p>
                        <p className="lh15 subtitolInterior">
                            <b>{t('avisLegalPolitica5')}</b>
                            {t('avisLegalPolitica6')}
                        </p>
                        <p className="lh15 subtitolInterior" id="seuElectronica">
                            <b>{t('avisLegalPolitica7')}</b>
                            {t('avisLegalPolitica8')}
                            {enllas2}
                            {t('avisLegalPolitica10')}
                        </p>
                        <p className="lh15 subtitolInterior" id="proteccioDades">
                            <b>{t('avisLegalPolitica11')}</b>
                            {t('avisLegalPolitica12')}
                            <a href="mailto:'protecciodades@dpd.caib.es'" tabIndex="507" aria-labelledby="proteccioDades">protecciodades@dpd.caib.es</a>.
                        </p>
                    </div>

                    <div className="col-md-12 border-0 float-left p-0 pt-2">
                        <h3 className="apartat titol h2">{t('avisLegalCookies1')} <em>{t('avisLegalCookies2')}</em> {t('avisLegalCookies3')}</h3>
                        <p className="lh15 subtitolInterior">
                            {t('avisLegalCookies4')}
                            <em>{t('avisLegalCookies5')}</em>
                            {t('avisLegalCookies6')}
                        </p>
                        <p className="lh15 subtitolInterior" id="aepd">
                            {t('avisLegalCookies7')}
                            <a href="https://www.aepd.es/" target="_blank" rel="noopener noreferrer" tabIndex="508" aria-labelledby="aepd">{t('avisLegalCookies8')}</a>
                            {t('avisLegalCookies9')}
                        </p>
                        <p className="lh15 subtitolInterior">
                            {t('avisLegalCookies10')}
                        </p>
                        <div className="mb-3">
                            <ul className="lh15 pl-5 pt-1">
                                <li><b>{t('avisLegalCookies11')}</b><p className="mt-0">{t('avisLegalCookies12')}</p></li>
                                <li id="analitiques"><b>{t('avisLegalCookies13')}</b><p className="mt-0">{t('avisLegalCookies14')}
                                    {enllas3}
                                    {t('avisLegalCookies16')}
                                    {enllas4}».</p>
                                </li>
                                <li><b>{t('avisLegalCookies18')}</b><p className="mt-0">{t('avisLegalCookies19')}</p></li>
                            </ul>
                        </div>
                        <p className="lh15 subtitolInterior">
                            {t('avisLegalCookies20')}
                        </p>
                        <div className="mb-3">
                            <ul className="lh15 pl-5 pt-1" id="desactivarGaletes">
                                <li>{enllas5}</li>
                                <li>{enllas6}</li>
                                <li>{enllas7}</li>
                                <li>{enllas8}</li>
                                <li>{enllas9}</li>
                                <li>{enllas10}</li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default withTranslation()(AvisLegal);
