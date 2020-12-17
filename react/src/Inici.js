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
	// 	ReactGA.pageview(window.location.pathname + window.location.search);
	// }

	render() {

		var autenticat = this.props.autenticat;

		return (

			<div className="container-contenido">
				<ExpirarSessio />
				{
					{
						'0': <IniciPublic />,
						'1': <LlistatDePlugins />
					}[autenticat]
				}
			</div>

		);

	}
}

export default withTranslation()(Inici);
