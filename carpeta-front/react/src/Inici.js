import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import LlistatDePlugins from './LlistatDePlugins';
import IniciPublic from './IniciPublic';
import ExpirarSessio from "./ExpirarSessio";


/**
 * @author anadal Migracio A Routes 
 */
class Inici extends Component {

	componentWillMount() {
		var canviarLang = sessionStorage.getItem("langActual");
		i18n.changeLanguage(canviarLang);
	}


	render() {

		//var autenticat = this.props.autenticat;
		var autenticat = sessionStorage.getItem('autenticat');

		clearTimeout(sessionStorage.getItem('idTimeOut'));

		// console.log("entitat inici: " + sessionStorage.getItem('entitat'));

		return (

			<div className="container-contenido espaiContingut" id="contingutCentral">
				<ExpirarSessio />
				{
					{
						'0': <IniciPublic />,						
						'1': <LlistatDePlugins seccioID='0' />
					}[autenticat]
				}
			</div>

		);

	}
}

export default withTranslation()(Inici);
