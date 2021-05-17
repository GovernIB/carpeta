import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import {withRouter} from "react-router";

class PluginHtml extends Component {

    constructor() {
        super();
        this.state = {
            contingut: '',
            loading: true
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN PluginHTML A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {
        var autenticat = sessionStorage.getItem('autenticat');
    }

    render() {

        var isPublic = this.props.isPublic;
        console.log(" PLUGIN HTML: isPublic? " + isPublic);
        var autenticat = sessionStorage.getItem('autenticat');
        if (autenticat === '0' && !isPublic) {
            console.log("S'HA INTENTAT MOSTRAR UN PLUGIN SENSE ESTAR AUTENTICAT");

            var urlBase = sessionStorage.getItem('contextPath');
            

            // ANAM A AUTENTICACIO
            var encURI = window.btoa(window.location.href);
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {  };
            xhttp.open("GET", urlBase + "/fa/" + encURI, true);
            xhttp.send();

            //this.props.history.push("/");

            var loc = new URL(window.location.href);
            window.location.href = ('prelogin?urlbase=' + encodeURIComponent(loc.protocol + '//' + loc.host));

            return '';
        }

        console.log("PLUGIN HTML 222222  !!!!!!!");

        const { t } = this.props;
        const pluginContext = this.props.match.params.pluginContext;
        console.log("PLUGIN HTML Context =" + pluginContext);

        const pluginParameter = this.props.match.params.pluginParameter;
        console.log("PLUGIN HTML Parameter = ]" + pluginParameter + "[");


        var data = new FormData();
        var codiPlugin;

        var urlBase = sessionStorage.getItem('contextPath');
        var url;
        if (pluginParameter) {
           url = urlBase + "/pluginfront/showplugin/" + pluginContext + "/" + i18n.language + "/p/" + pluginParameter;
        } else {
           url = urlBase + "/pluginfront/showplugin/" + pluginContext + "/" + i18n.language;
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        xhr.onload = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    codiPlugin = this.responseText;
                    document.getElementById("substituir").innerHTML = codiPlugin;
                } else {
                    //window.location.href = window.location.href;
                }
            }
        };
        xhr.send(data);


        var lastSize = 0;

        function checkIframeSize() {

            var iframe = document.getElementById('myiframe');


            if (!iframe) {
                // console.log("Sortim de IFRAME (frame val null)");
                return;
            }

            setTimeout(checkIframeSize, 1000);

            var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
            // var iframeDocument;
            // if(iframe.contentDocument != null) {
            //     iframeDocument = iframe.contentDocument;
            // } else {
            //     iframeDocument = iframe.contentWindow.document;
            // }
            var h1 = $(iframeDocument.body).height();
            var h2 = iframeDocument.body.scrollHeight;
            var h = h1;
            if (h2 != null) {
                h = Math.max(h1, h2);
            }
            // var h = Math.max(h1, h2);
            var log = false;
            var d = new Date();

            if (h !== lastSize) {
                h = h + 100;
                lastSize = h;
                document.getElementById('myiframe').style.height = h + "px";
                lastSize = $(iframeDocument.body).height();
                if (iframeDocument.body != null) {
                    h = Math.max($(iframeDocument.body).height(), iframeDocument.body.scrollHeight);
                }
                lastSize = Math.max($(iframeDocument.body).height(), iframeDocument.body.scrollHeight);
            }
        }

        $(document).ready(function () {
            setTimeout(checkIframeSize, 2000);
            sessionStorage.setItem('idioma', i18n.language);
        });

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        return (
            <div>
                <ExpirarSessio />
                <div id="substituir" />
            </div>
        );

    }
}

export default withTranslation()(withRouter(PluginHtml));