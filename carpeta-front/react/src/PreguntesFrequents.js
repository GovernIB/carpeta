import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import DocumentTitle from "react-document-title";
import Faq from "react-faq-component";
import ExpirarSessio from "./ExpirarSessio";

/**
 *
 * @author jpernia
 */
class PreguntesFrequents extends Component {

    constructor() {
        super();
        this.state = {
            faqs: [], // faqs
            error: null
        }
        this.canviIdioma = this.canviIdioma.bind(this);
        i18n.on('languageChanged', this.canviIdioma);
    }


    canviIdioma(lng) {
        this.componentDidMount();
    }

    componentDidMount() {

        $( "#faqList" ).keyup(function() {
            $(".closed").removeClass('closed').addClass('expanded').attr('aria-expanded', true).attr('aria-hidden', true);
            $(".row-content").css({height: 'auto', visibility: 'visible', opacity: 'initial'}).attr('aria-expanded', true).attr('aria-hidden', false);
        });

        const baseURL = sessionStorage.getItem('contextPath');
        const url = baseURL + "/webui/faq";
        axios.get(url)
            .then(res => {
                this.setState({
                    faqs: res.data
                })
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        faqs:false,
                        error: errorPantalla
                    });
                } else{
                    this.setState({
                        faqs:false,
                        error: JSON.stringify(error)
                    });
                }

            });

    }

    error(missatge) {
        alert(missatge);
    }


    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');

        clearTimeout(sessionStorage.getItem('idTimeOut'));

        let botoTornarEnrera = '';

        if (this.props.seccioContext !== '0') {
            botoTornarEnrera = <div className="col-md-12 border-0 float-left pt-0 pr-0 pb-0 pl-1"
                                    id="botoTornarFaq"
                                    style={{marginTop: '40px'}}>
                <button type="button" data-toggle="modal"
                        onClick={() => window.location.href = sessionStorage.getItem("pagTornar")}
                        className="botoSuport botoTornauApp" tabIndex="550" aria-labelledby="botoTornarFaq">{t('tornar')}</button>
            </div>;
        }


        let missatgeError = "";
        let faqDataArray = {
            rows: []
        };

        let styles;
        let config;

        if (this.state.error) {

            missatgeError = <div className="alert alert-danger" role="alert">{t(this.state.error)}</div>;

        } else {

            this.state.faqs.map((faq, i) => {
                faqDataArray.rows[i] = {
                    ['title']: faq.title,
                    ['content']: faq.content
                };
            });

            styles = {
                bgColor: '#F8F8F8',
                titleTextColor: '#c30045',
                rowTitleColor: '#c30045',
                rowContentColor: '#666',
                // arrowColor: "red",
            };

            config = {
                // animate: true,
                // arrowIcon: "V",
                // tabFocus: true
            };

        }

        let titolHeader = t('faqTitol');
        let subtitolHeader = t('faqSubtitol');


        return (

            <div className="row mr-0 ml-0">

                <DocumentTitle title={t('menuPreguntesFrecuents') + " - " + t('menuTitol')} />

                {autenticat === '1' &&
                <ExpirarSessio/>
                }

                <div className="infoNoMenu">

                    <h2 className="titol h2">{titolHeader}</h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">

                        <p className="lh15">{subtitolHeader}</p>

                        <div className="card-body imc--llista--capses pl-0 pt-1">

                            <div className="row mb-0 ml-0" id="faqList" tabIndex="501">

                                {!this.state.error &&
                                    <Faq
                                        data={faqDataArray}
                                        styles={styles}
                                        config={config}
                                    />
                                }

                                {this.state.error &&
                                    {missatgeError}
                                }

                            </div>

                        </div>

                    </div>

                </div>

                {botoTornarEnrera}

            </div>

        );
    }

}

export default withTranslation()(PreguntesFrequents);