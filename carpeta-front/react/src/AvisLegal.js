import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import ExpirarSessio from "./ExpirarSessio";
import DocumentTitle from "react-document-title";
import i18n from "i18next";
import axios from "axios";


class AvisLegal extends Component {

    constructor(){
        super();
        this.state = {
            codiHtml: null,
            error: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    componentDidMount() {
        var amplePantalla = screen.width;
        if(amplePantalla < 576) {
            document.getElementById("headerBarra").style.backgroundColor = sessionStorage.getItem("colorBarra");
        }
        $('[tabIndex=1]').focus();

        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/infoEntitat";

        axios.get(url)
            .then(res => {
                var infoEntitat = res.data;
                this.setState({ codiHtml: infoEntitat.htmlAvislegal });
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    codiHtml: null,
                    error: JSON.stringify(error)
                });
            });
    }

    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');
        var htmlCode;

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        var langActual = sessionStorage.getItem("langActual");

        if (this.state.error) {
            htmlCode = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {
            if (this.state.codiHtml) {
                htmlCode = <div dangerouslySetInnerHTML={{__html: this.state.codiHtml}}/>
            } else {
                htmlCode = <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15 subtitolSuperior">{t('avisLegalBuid')}</p>
                </div>;
            }
        }


        return (<>
                <div className="titolPaginaApp visioMobil">
                    {t('avisLegalTitol')}
                </div>
                <div className="container-contenido" tabIndex="501">

                    <DocumentTitle title={t('peuAvis') + " - " + t('menuTitol')} />

                    {autenticat === '1' &&
                    <ExpirarSessio/>
                    }
                    <div className="infoNoMenu">
                        <h2 className="titol h2">{t('avisLegalInformacio')}</h2>

                        {htmlCode}

                        <div className="col-md-12 border-0 float-left p-0" id="botoTornarAvisL" style={{ marginTop: '20px' }}>
                            <button type="button" data-toggle="modal" onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar")
                            }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarAvisL">{t('tornar')}</button>
                        </div>

                    </div>
                </div>
            </>
        );
    }
}

export default withTranslation()(AvisLegal);
