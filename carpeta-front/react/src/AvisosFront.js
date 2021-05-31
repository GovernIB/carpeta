import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

/** 
 * @autor anadal  Avisos del Front carrega avisos públics i privats #373 
 */
class AvisosFront extends Component {

    constructor() {
        super();
        this.state = {
            avisos: [],
            error: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN AVISOS FRONT A ]" + lng+ "[");
        this.componentDidMount();
    }

    componentDidMount() {

        var baseURL = sessionStorage.getItem('contextPath');
        var auth = sessionStorage.getItem('autenticat');

        if (auth === '0') {
            var url = baseURL + "/webui/avisosfrontpublic";
            axios.get(url)
                .then(res => {
                    this.setState({ avisos: res.data })
                })
                .catch(error => {
                    console.log(JSON.stringify(error));
                    if (error.response) {
                        console.log("error.response.data: " + error.response.data);
                        console.log("error.response.status: " + error.response.status);
                        console.log("error.response.headers: " + error.response.headers);
                    }
                    this.setState({
                        avisos: "",
                        error: JSON.stringify(error)
                    });
                });

        } else {
            var url2 = baseURL + "/webui/avisosfrontprivat";
            axios.get(url2)
                .then(res => {
                    this.setState({ avisos: res.data })
                })
                .catch(error => {
                    console.log(JSON.stringify(error));
                    if (error.response) {
                        console.log("error.response.data: " + error.response.data);
                        console.log("error.response.status: " + error.response.status);
                        console.log("error.response.headers: " + error.response.headers);
                    }
                    this.setState({
                        avisos: "",
                        error: JSON.stringify(error)
                    });
                });
        }
    }


    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');

        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (autenticat === '0') {
                if (!this.state.avisos.length) {
                    content = "";
                } else {
                    content = this.state.avisos.map((s, i) => (
                        <div key={i} className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">
                            {s.label}
                            <button type="button" className="close" data-dismiss="alert" aria-label={t('tancar')} aria-describedby={t('tancarAvis')}>
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    ))
                }
            } else {
                if (!this.state.avisos.length) {
                    content = "";
                } else {
                    content = this.state.avisos.map((s, i) => (
                        <div key={i} className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">
                            {s.label}
                            <button type="button" className="close" data-dismiss="alert" aria-label={t('tancar')} aria-describedby={t('tancarAvis')}>
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    ))
                }
            }
        }

        var error = sessionStorage.getItem('errorLogin');
        var avisError = "";
        if (error.length) {
            avisError = <div className="alert avis3 alert-dismissible fade show" role="alert">
                {error}
                <button type="button" className="close" data-dismiss="alert" aria-label={t('tancar')} aria-describedby={t('tancarAvis')}>
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>;
        }

        return (
            <div>
                {content}
                {avisError}
            </div>
        );
    }
}

export default withTranslation()(AvisosFront);
