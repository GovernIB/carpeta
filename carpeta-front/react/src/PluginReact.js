import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import {withRouter} from "react-router";

/**
 * @author jpernia
 * @author anadal
 * 
 */
class PluginReact extends Component {


    constructor(props) {
        super(props);

        this.state = {
            loaded: false,
            pluginContext: null            
        };

        this.reloadReactPlugin = this.reloadReactPlugin.bind(this);
    }

    componentDidMount() {

        console.log('PLUGIN REACT  Entra a componentDidMount()');

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

            
            this.reloadReactPlugin();
            
        }
    }


    reloadReactPlugin() {

        const pluginContext = this.props.pluginContext;

        console.log('PLUGIN REACT  Entra a reloadReactPlugin()');

        var urlBase = sessionStorage.getItem('contextPath');
        var url = urlBase + "/pluginfront/showreactplugin/" + pluginContext + "/" + i18n.language;

        // Per provocar el render !!!!
        this.setState({loaded: true, pluginContext: pluginContext });

        sessionStorage.setItem('idioma', i18n.language);
        $('#contentpluginreact').load(url);


        if (window['changeLanguagePlugin']) {
            console.log('2 changeLanguagePlugin => EXISTEIX !!!!');
            window['changeLanguagePlugin'](i18n.language);
        } else {
            console.log('2 changeLanguagePlugin => NOOOO EXISTEIX !!!!');
        }

    }


	render() {

        var autenticat = sessionStorage.getItem('autenticat');
        if (autenticat === '0') {
            console.log("S'HA INTENTAT MOSTRAR UN PLUGIN PERO NOOOO ESTAM AUTENTICATS");
            this.props.history.push("/");
            return '';
        }


        console.log(" PLUGIN REACT RENDER 111 pluginContext state " +  this.state.pluginContext + "!!!!!");
        console.log(" PLUGIN REACT RENDER 222       pluginContext " +  this.props.pluginContext + "!!!!!");
        console.log(" PLUGIN REACT RENDER 333 seccioContext " +  this.props.seccioContext + "!!!!!");


        if (this.props.pluginContext != this.state.pluginContext) {

            console.log(" PLUGIN REACT RENDER plugin context DIFERENTS !!!!");

            setTimeout( () =>  this.reloadReactPlugin(), 500);
            
        }

        clearTimeout(sessionStorage.getItem('idTimeOut'));

		return (
            <div>
                <div id="contentpluginreact"></div>
                <ExpirarSessio />
            </div>
		);

	}
}

export default withTranslation()(withRouter(PluginReact));