import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";
import { withRouter } from "react-router";
import Breadcrumb from "./Breadcrumb";
import * as breadcrumbPathsAut from "./utils/breadcrumbPathsAut";

class PluginHtml extends Component {

    constructor() {
        super();
        this.state = {
            contingut: '',
            loading: true
        }
    }

    /*
    componentWillMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + `/pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const pluginID = this.props.match.params.pluginId;
            console.log("PLUGIN HTML ID =" + pluginID + "  !!!!!!!");
            this.setState({ contingut:res.data, loading: false });
        });
    }
    */

    componentWillReceiveProps() {
        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '1') {
            this.setState({ loading: true });
            this.fakeRequest().then(() => {
                const el = document.querySelector(".loader-container");
                if (el) {
                    document.querySelector("#carregant").classList.add('loaderOcult');
                    this.setState({ loading: false });
                }
            });
        }

    }

    componentDidMount() {
        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '1') {
            this.fakeRequest().then(() => {
                const el = document.querySelector(".loader-container");
                if (el) {
                    document.querySelector("#carregant").classList.add('loaderOcult');
                    this.setState({ loading: false });
                }
            });
        }
    }

    fakeRequest() {
        return new Promise(resolve => setTimeout(() => resolve(), 1000));
    };


    render() {

        var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '0') {
            console.log("S'HA INTENTAT MOSTRAR UN PLUGIN PERO NOOOO ESTAM AUTENTICATS");
            this.props.history.push("/");
            return '';
        }

        if (this.state.loading) {
            document.querySelector("#carregant").classList.remove('loaderOcult');
            console.log("PLUGIN HTML CARREGANT !!!!!!!");
            return null;
        }

        console.log("PLUGIN HTML 111111  !!!!!!!");
        document.querySelector("#continguts").classList.add('espaiContingut');

        console.log("PLUGIN HTML 222222  !!!!!!!");

        const { t } = this.props;
        const pluginID = this.props.match.params.pluginId;
        console.log("PLUGIN HTML ID =" + pluginID + "  !!!!!!!");


        var data = new FormData();
        var codiPlugin;

        var urlBase = sessionStorage.getItem('contextPath');
        var url = urlBase + "/pluginfront/showplugin/" + pluginID + "/" + i18n.language;

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
                console.log("Sortim de IFRAME (frame val null)");
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
                // lastSize = Math.max($(iframeDocument.body).height(), iframeDocument.body.scrollHeight);
            }
        }

        $(document).ready(function () {
            setTimeout(checkIframeSize, 2000);
            sessionStorage.setItem('idioma', i18n.language);
        });

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        var motlla = <Breadcrumb items={breadcrumbPathsAut.Plugin} autenticat={autenticat}/>


        return (
            <div>
                {motlla}
                <ExpirarSessio />
                <div id="substituir" />
            </div>
        );

    }
}

export default withTranslation()(withRouter(PluginHtml));