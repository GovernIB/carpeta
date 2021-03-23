import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class NivellAutenticacio extends Component {

    render() {

        const {t} = this.props;

        const styleItalic = {fontStyle: 'italic'};
        var nivell = sessionStorage.getItem("usuariNivell");

        return (
            <span id="nivellAutenticacio" className="imc--autenticacio">
                <span className="separdorAuten pl-2">-</span>
                <span className="pl-2 nivellAut h4">{t('nivellAutenticacio')}: {t('nivellAutenticacio'+ nivell)}</span>
            </span>
        );
    }
}

export default withTranslation()(NivellAutenticacio);