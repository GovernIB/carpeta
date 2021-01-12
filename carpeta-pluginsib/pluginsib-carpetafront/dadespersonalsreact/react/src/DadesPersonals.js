import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from './i18n';
/**
 *  @author anadal
 */ 
class DadesPersonals extends Component {


    constructor(props) {
       super(props);
       //this.setState({ refrescar: 0 });
       //this.changedLanguage = this.changedLanguage.bind(this);
    }
/*
   componentDidMount() {
	   
	   i18n.on('languageChanged', this.changedLanguage.bind(this));

   }
   
   
   changedLanguage(lng) {
	   console.log('DadesPersonals  Idioma canviat a ' + lng );
   }
*/

	render() {

		console.log('  RENDER !!!!!');
		
		const {t} = this.props;
		
		const dades = this.props.dades;

        const usuariNom = dades.usuariNom;
        const usuariLlinatge1 =dades.usuariLlinatge1;
        const usuariLlinatge2 = dades.usuariLlinatge2;
        const usuariDNI = dades.usuariDNI;
        const usuariMetode = dades.usuariMetode;
        
        const usuariNomComplet = usuariNom + ' ' + usuariLlinatge1 + ' ' + usuariLlinatge2;

        return (
            
                <div className="infoNoMenu">
                    <h2><p className="titol h2">{t('dadespersonalsTitol')} {usuariNomComplet}</p></h2>

                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15">{t('dadespersonalsDescripcio')} </p>

                        <div className="card">
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"><p className="titol h5" >{t('dadespersonalsNom')}</p><p
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
         
        );   


	}
}

export default withTranslation()(DadesPersonals);