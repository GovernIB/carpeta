import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import ExpirarSessio from "./ExpirarSessio";

class DadesPersonals extends Component {

    render() {

        const {t} = this.props;

        const usuariNomComplet = sessionStorage.getItem('usuariNomComplet');
        const usuariNom = sessionStorage.getItem('usuariNom');
        const usuariLlinatge1 = sessionStorage.getItem('usuariLlinatge1');
        const usuariLlinatge2 = sessionStorage.getItem('usuariLlinatge2');
        const usuariDNI = sessionStorage.getItem('usuariDNI');
        const usuariMetode = sessionStorage.getItem('usuariMetode');

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        return (
            <div className="container-contenido">
                <ExpirarSessio />
                <div className="infoNoMenu">
                    <p className="titol h2">{t('dadespersonalsTitol')} {usuariNomComplet}</p>

                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15">{t('dadespersonalsDescripcio')} </p>

                        <div className="card">
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"><p className="titol h5">{t('dadespersonalsNom')}</p><p
                                    className="lh15">{usuariNom}</p></li>
                                <li className="list-group-item"><p
                                    className="titol h5">{t('dadespersonalsLlinatge1')}</p><p
                                    className="lh15">{usuariLlinatge1}</p></li>
                                <li className="list-group-item"><p
                                    className="titol h5">{t('dadespersonalsLlinatge2')}</p><p
                                    className="lh15">{usuariLlinatge2}</p></li>
                                <li className="list-group-item"><p className="titol h5">{t('dadespersonalsDni')}</p><p
                                    className="lh15">{usuariDNI}</p></li>
                                <li className="list-group-item"><p className="titol h5">{t('dadespersonalsMetode')}</p>
                                    <p className="lh15">{usuariMetode}</p></li>
                            </ul>
                        </div>

                    </div>

                </div>
            </div>
        );
    }
}

export default withTranslation()(DadesPersonals);
