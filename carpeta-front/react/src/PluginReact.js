import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import { withRouter } from "react-router";
import Breadcrumb from "./Breadcrumb";
import * as breadcrumbPathsAut from "./utils/breadcrumbPathsAut";

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

            const pluginContext = this.props.pluginContext;
            var urlBase = sessionStorage.getItem('contextPath');
            var url = urlBase + "/pluginfront/showreactplugin/" + pluginContext + "/" + i18n.language;

            //  $(document).ready(function () {

            // Per provocar el render !!!!
                this.setState({loaded: true });

                sessionStorage.setItem('idioma', i18n.language);
                $('#contentplugin').load(url);
            //}
            
            
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

        console.log(" PLUGIN REACT RENDER 222 pluginContext " +  this.props.pluginContext + "!!!!!");
        console.log(" PLUGIN REACT RENDER 333 seccioContext " +  this.props.seccioContext + "!!!!!");

		const pluginContext = this.props.pluginContext;; 
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