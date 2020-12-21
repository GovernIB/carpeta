import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';

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
			<div id="contentplugin"></div>
		);

	}
}

export default withTranslation()(PluginReact);