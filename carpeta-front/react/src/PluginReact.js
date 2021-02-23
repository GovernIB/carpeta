import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import { withRouter } from "react-router";

class PluginReact extends Component {


    constructor(props) {
        super(props);

        this.state = {
            loaded: false            
        };
    }

    componentDidMount() {

        var autenticat = sessionStorage.getItem('autenticat');
        if (autenticat === '1') {

            i18n.on('languageChanged', function(lng) { 
                    console.log('PLUGIN REACT  Idioma canviat a ' + lng );
                    
                    if (window['changeLanguagePlugin']) {
                        console.log('changeLanguagePlugin => EXISTEIX !!!!');
                        window['changeLanguagePlugin'](lng);
                    } else {
                        console.log('changeLanguagePlugin => NOOOO EXISTEIX !!!!');
                    }

                }
            );
        }
    }


	render() {

        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '0') {
            console.log("S'HA INTENTAT MOSTRAR UN PLUGIN PERO NOOOO ESTAM AUTENTICATS");
            this.props.history.push("/");
            return '';
        }

		const {t} = this.props;

        console.log(" PLUGIN REACT RENDER 222 pluginID " +  this.props.pluginID + "!!!!!");
        console.log(" PLUGIN REACT RENDER 333 seccioID " +  this.props.seccioID + "!!!!!");

		const pluginID = this.props.pluginID;; //this.props.match.params.pluginId; //

        if (!this.state.loaded) {

            console.log("CARREGA INICIAL DEL PLUGIN !!!!!");

            var urlBase = sessionStorage.getItem('contextPath');
            var url = urlBase + "/pluginfront/showreactplugin/" + pluginID + "/" + i18n.language;

            $(document).ready(function () {
                sessionStorage.setItem('idioma', i18n.language);
                $('#contentplugin').load(url);
            });
            
            this.setState({loaded: true });
        } else {
            console.log("PLUGIN JA CARREGAT !!!!!");
        }

        clearTimeout(sessionStorage.getItem('idTimeOut'));

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

export default withTranslation()(withRouter(PluginReact));