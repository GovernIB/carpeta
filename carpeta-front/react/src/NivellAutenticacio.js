import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class NivellAutenticacio extends Component {

    render() {

        const {t} = this.props;

        const styleItalic = {fontStyle: 'italic'};
        var nivell = sessionStorage.getItem("usuariNivell");

        return (            
            <span style={styleItalic} className="pl-3 nivellAut h4">{t('nivellAutenticacio')}: {t('nivellAutenticacio'+ nivell)}</span>
        );
    }
}

export default withTranslation()(NivellAutenticacio);