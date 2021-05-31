import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class NivellAutenticacio extends Component {

    render() {

        const {t} = this.props;

        const styleItalic = {fontStyle: 'italic'};
        var nivell = sessionStorage.getItem("usuariNivell");

        var contingut;

        var esRepresentant = sessionStorage.getItem('representantExisteix');

        console.log( "NIVELLAutenticacio::esRepresentant => " + esRepresentant);

        if (esRepresentant == 'true') {

            console.log( "NIVELLAutenticacio::esRepresentant 2222 => " + esRepresentant);
            contingut = t('representant') + ": " + sessionStorage.getItem('representantNom') 
                      + " " + sessionStorage.getItem('representantLlinatge1') 
                      + " " + sessionStorage.getItem('representantLlinatge2');
        } else {
            console.log( "NIVELLAutenticacio::esRepresentant 333333333 => " + esRepresentant);
            contingut = t('nivellAutenticacio') + ":" + t('nivellAutenticacio'+ nivell);
        }


        return (
            <span id="nivellAutenticacio" className="imc--autenticacio">
                <span className="separdorAuten pl-2">-</span>
                <span className="pl-2 nivellAut h4">{contingut}</span>
            </span>
        );
    }
}

export default withTranslation()(NivellAutenticacio);