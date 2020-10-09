import React from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';


function PluginReact ({ t , pluginID}) {

	var data = new FormData();
	var codiPlugin;
	
	var urlBase = window.location.href;
	var url = urlBase + "pluginfront/showplugin/" + pluginID + "/" + i18n.language;

	
	$(document).ready(function ()  {
		sessionStorage.setItem('idioma', i18n.language);
		$('#content').load(url);
    });

    return (
        <div id="content"></div>
    );
	
}

export default withTranslation()(PluginReact);