import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";

class PluginReact extends Component {


   componentDidMount() {
     i18n.on('languageChanged', function(lng) { 
            console.log('PLUGIN REACT  Idioma canviat a ' + lng );
          
            if (window['changeLanguageDadesCiutadaReact']) {
                console.log('changeLanguageDadesCiutadaReact => EXISTEIX !!!!');
            } else {
                console.log('changeLanguageDadesCiutadaReact => NOOOO EXISTEIX !!!!');
            }
              
         }
     );
   }


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
            <div>
			<div id="contentplugin"></div>

			<div>
				<ExpirarSessio />
				<div id="substituir"></div>
			</div>
            </div>

		);

	}
}

export default withTranslation()(PluginReact);