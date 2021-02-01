import React, {Component,Suspense} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import ExpirarSessio from "./ExpirarSessio";

class PluginHtml extends Component {

    constructor() {
        super();
        this.state = {loading: true}
    }

    componentWillReceiveProps() {
        this.setState({ loading: true });
        this.fakeRequest().then(() => {
            const el = document.querySelector(".loader-container");
            if (el) {
                document.querySelector("#carregant").classList.add('loaderOcult');
                this.setState({ loading: false });
            }
        });
    }

    componentDidMount() {
        this.fakeRequest().then(() => {
            const el = document.querySelector(".loader-container");
            if (el) {
                document.querySelector("#carregant").classList.add('loaderOcult');
                this.setState({ loading: false });
            }
        });
    }

    fakeRequest() {
        return new Promise(resolve => setTimeout(() => resolve(), 1000));
    };


    render() {

        if (this.state.loading) {
            document.querySelector("#carregant").classList.remove('loaderOcult');
            return null;
        }

        const {t} = this.props;
        const pluginID = this.props.pluginID;

        var data = new FormData();
        var codiPlugin;

        var urlBase = window.location.href;
        var url = urlBase + "pluginfront/showplugin/" + pluginID + "/" + i18n.language;

        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        xhr.onload = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    codiPlugin = this.responseText;
                    document.getElementById("substituir").innerHTML = codiPlugin;
                } else {
                    window.location.href = window.location.href;
                }
            }
        };
        xhr.send(data);

        var lastSize = 0;

        function checkIframeSize() {

            setTimeout(checkIframeSize, 1000);

            var iframe = document.getElementById('myiframe');
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
            if(h2 != null) {
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
                if(iframeDocument.body != null) {
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


        return (
            <div>
                <ExpirarSessio />
                <div id="substituir" />
            </div>
        );

    }
}

export default withTranslation()(PluginHtml);