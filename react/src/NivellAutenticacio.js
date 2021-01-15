import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class NivellAutenticacio extends Component {

    render() {

        const {t} = this.props;

        var nivell = sessionStorage.getItem("usuariNivell");

        return (
            <span className="pl-3 nivellAut">{t('nivellAutenticacio')}: {t('nivellAutenticacio'+ nivell)}</span>
        );
    }
}

export default withTranslation()(NivellAutenticacio);