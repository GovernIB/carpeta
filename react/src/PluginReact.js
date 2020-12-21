import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";

class PluginReact extends Component {

	render() {

		const {t} = this.props;
		const pluginID = this.props.pluginID;

		var urlBase = window.location.href;
		var url = urlBase + "pluginfront/showreactplugin/" + pluginID + "/" + i18n.language;

		$(document).ready(function () {
			sessionStorage.setItem('idioma', i18n.language);
			$('#contentplugin').load(url);
		});

		return (
<<<<<<< HEAD
			<div id="contentplugin"></div>
=======
			<div>
				<ExpirarSessio />
				<div id="substituir"></div>
			</div>
>>>>>>> 9c05eb9a914458809035337ff86b72f7bb2315d5
		);

	}
}

export default withTranslation()(PluginReact);