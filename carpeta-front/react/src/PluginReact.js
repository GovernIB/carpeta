import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import {withRouter} from "react-router";
import PropTypes from 'prop-types';

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
        $('[tabIndex=1]').focus();

        // console.log('PLUGIN REACT  Entra a componentDidMount()');

        var autenticat = sessionStorage.getItem('autenticat');
        if (autenticat === '1') {

            i18n.on('languageChanged', function(lng) { 
                    // console.log('PLUGIN REACT  Idioma canviat a ' + lng );
                    
                    if (window['changeLanguagePlugin']) {
                        // console.log('changeLanguagePlugin => EXISTEIX !!!!');
                        window['changeLanguagePlugin'](lng);
                    } else {
                        // console.log('changeLanguagePlugin => NOOOO EXISTEIX !!!!');
                    }

                }
            );

            
            this.reloadReactPlugin();
            
        }
    }


    reloadReactPlugin() {

        const pluginContext = this.props.pluginContext;

        // console.log('PLUGIN REACT  Entra a reloadReactPlugin()');

        var urlBase = sessionStorage.getItem('contextPath');
        var url = urlBase + "/pluginfront/showreactplugin/" + pluginContext + "/" + i18n.language;

        // Per provocar el render !!!!
        this.setState({loaded: true, pluginContext: pluginContext });

        sessionStorage.setItem('idioma', i18n.language);
        $('#contentpluginreact').load(url);


        if (window['changeLanguagePlugin']) {
            // console.log('2 changeLanguagePlugin => EXISTEIX !!!!');
            window['changeLanguagePlugin'](i18n.language);
        } else {
            // console.log('2 changeLanguagePlugin => NOOOO EXISTEIX !!!!');
        }

    }


	render() {

        var isPublic = this.props.isPublic;

        var autenticat = sessionStorage.getItem('autenticat');
        if (autenticat === '0' && !isPublic) {
        
            console.log("S'HA INTENTAT MOSTRAR UN PLUGIN SENSE ESTAR AUTENTICAT");

            var urlBase = sessionStorage.getItem('contextPath');

            // ANAM A AUTENTICACIO
            var encURI = window.btoa(window.location.href);
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
            };
            xhttp.open("GET", urlBase + "/fa/" + encURI, true);
            xhttp.send();

            //this.props.history.push("/");

            

            var loc = new URL(window.location.href);
            window.location.href = ('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host));

            return '';
        }


        // console.log(" PLUGIN REACT RENDER pluginContext state " +  this.state.pluginContext + "!");
        console.log(" PLUGIN REACT RENDER pluginContext " +  this.props.pluginContext + "!");
        // console.log(" PLUGIN REACT RENDER seccioContext " +  this.props.seccioContext + "!");


        if (this.props.pluginContext != this.state.pluginContext) {

            // console.log(" PLUGIN REACT RENDER plugin context DIFERENTS !!!!");

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


PluginReact.propTypes = {
    seccioContext : PropTypes.string.isRequired,
    pluginContext: PropTypes.string.isRequired,
    pluginParameter: PropTypes.string,
    isPublic: PropTypes.bool
};

export default withTranslation()(withRouter(PluginReact));