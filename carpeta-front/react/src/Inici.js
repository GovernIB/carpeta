import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
// import ReactGA from 'react-ga';
import LlistatDePlugins from './LlistatDePlugins';
import IniciPublic from './IniciPublic';
import ExpirarSessio from "./ExpirarSessio";

class Inici extends Component {

	componentWillMount() {
		var canviarLang = sessionStorage.getItem("langActual");
		i18n.changeLanguage(canviarLang);
	}

	// componentDidMount() {
	// 	var aut = sessionStorage.getItem('autenticat');
	// 	if (aut === '1') {
	// 		console.log("1:" + aut);
	// 		document.getElementById('contingutCentral').classList.add('espaiContingut');
	// 	}else{
	// 		console.log("2:" + aut);
	// 		document.getElementById('contingutCentral').classList.remove('espaiContingut');
	// 	}
	// 	// ReactGA.pageview(window.location.pathname + window.location.search);
	// }

	render() {

		var autenticat = this.props.autenticat;

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
