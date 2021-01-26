import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from './i18n';
/**
 *  @author anadal
 */
class DadesPersonals extends Component {


    constructor(props) {
        super(props);
        console.log('  CONSTRUCTOR DADES PERSONALS !!!!!');
    }


    render() {

        console.log('  RENDER DADES PERSONALS!!!!!');

        const { t } = this.props;

        const dades = this.props.dades;

        const usuariNom = dades.usuariNom;
        const usuariLlinatge1 = dades.usuariLlinatge1;
        const usuariLlinatge2 = dades.usuariLlinatge2;
        const usuariDNI = dades.usuariDNI;
        const usuariMetode = dades.usuariMetode;

        const usuariNomComplet = usuariNom + ' ' + usuariLlinatge1 + ' ' + usuariLlinatge2;


        console.log('  usuariNomComplet = ' + usuariNomComplet);

        return (
            <div className="infoNoMenu">
                <div className="col-md-12 border-0 float-left p-0">
                    <div className="card">
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item"><label className="lh15" >{t('dadespersonalsNom')}: &nbsp;</label><label
                                className="titol h5">{usuariNom}</label></li>
                            <li className="list-group-item"><label
                                className="lh15">{t('dadespersonalsLlinatge1')}: &nbsp;</label><label
                                    className="titol h5">{usuariLlinatge1}</label></li>
                            <li className="list-group-item"><label
                                className="lh15">{t('dadespersonalsLlinatge2')}: &nbsp;</label><label
                                    className="titol h5">{usuariLlinatge2}</label></li>
                            <li className="list-group-item"><label className="lh15">{t('dadespersonalsDni')}: &nbsp;</label><label
                                className="titol h5">{usuariDNI}</label></li>
                            <li className="list-group-item"><label className="lh15">{t('dadespersonalsMetode')}: &nbsp;</label>
                                <label className="titol h5">{usuariMetode}</label></li>
                        </ul>
                    </div>

                </div>

            </div>

        );


    }
}

export default withTranslation()(DadesPersonals);