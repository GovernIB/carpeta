import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";

class PluginReact extends Component {

	render() {

		const {t} = this.props;
		const pluginID = this.props.pluginID;

		var urlBase = window.location.href;
		var url = urlBase + "pluginfront/showplugin/" + pluginID + "/" + i18n.language;

		$(document).ready(function () {
			sessionStorage.setItem('idioma', i18n.language);
			$('#content').load(url);
		});

		return (
			<div>
				<ExpirarSessio />
				<div id="substituir"></div>
			</div>
		);

	}
}

export default withTranslation()(PluginReact);