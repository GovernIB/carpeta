import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import LlistatDePlugins from './LlistatDePlugins';
import IniciPublic from './IniciPublic';
import ExpirarSessio from "./ExpirarSessio";
import { withRouter } from "react-router";



/**
 * @author anadal Migracio A Routes 
 */
class Inici extends Component {

	componentDidMount() {
		var canviarLang = sessionStorage.getItem("langActual");
		i18n.changeLanguage(canviarLang);
	}


	render() {


		var autenticat = sessionStorage.getItem('autenticat');

		console.log("INICI :: AUTH = " + autenticat);




		clearTimeout(sessionStorage.getItem('idTimeOut'));

		// console.log("entitat inici: " + sessionStorage.getItem('entitat'));

		return (

			<div className="container-contenido" id="contingutCentral">
				{autenticat === '1' &&
					<ExpirarSessio/>
				}
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

export default withTranslation()(withRouter(Inici));
